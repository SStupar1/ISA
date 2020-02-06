package isa.demo.controller;

import isa.demo.dto.request.AdministratorDTORequest;
import isa.demo.dto.request.CreateDoctorDTORequest;
import isa.demo.dto.request.MakeAppointmentDTORequest;
import isa.demo.dto.response.AdministratorDTOResponse;
import isa.demo.dto.response.AppointmentRequestDTOResponse;
import isa.demo.dto.response.CreateDoctorDTOResponse;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.exception.ResourceConflictException;
import isa.demo.model.*;
import isa.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/administrator")
public class AdministratorController {
    @Autowired
    PersonService personService;

    @Autowired
    EmailService emailService;

    @Autowired
    AppointmentRequestService appointmentRequestService;

    @Autowired
    RoomService roomService;

    @Autowired
    AppointmentService appointmentService;

    /**
     *
     * Odobravanje registracije za nekog pacijenta,moze biti uradjeno samo od strane CCA
     * @param id
     * @return
     */
    @PostMapping(consumes = "application/json",value ="/approveRegistration/{id}")
    public ResponseEntity<PersonDTOResponse> updateStatusApproved(@PathVariable Long id) {

        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("ACCEPTED",person.getId());
            try {
                emailService.userAccepted(person);
            }catch( Exception e ){

            }
            return new ResponseEntity<>(new PersonDTOResponse(person), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTOResponse(),HttpStatus.NO_CONTENT);
        }
    }


    /**
     *
     * Odbijanje registracije nekog pacijenta moze biti uradjeno samo od strane CCA
     * @param id
     * @return
     */
    @PostMapping(consumes = "application/json",value = "/rejectRegistration/{id}")
    public ResponseEntity<PersonDTOResponse> updateStatusRejected(@PathVariable Long id){
        Person person = personService.findOneById(id);
        if(person != null && person.getDecriminatorValue().equals("P") && person.getStatus().equalsIgnoreCase("PENDING")){
            personService.updatePersonStatus("REJECTED",person.getId());
            try {
                emailService.userDenied(person);
            }catch( Exception e ){

            }
            return new ResponseEntity<>(new PersonDTOResponse(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PersonDTOResponse(),HttpStatus.NO_CONTENT);
        }
    }
    @RequestMapping(value = "/getPendingPatients",method = RequestMethod.GET)
    public ResponseEntity<List<PersonDTOResponse>> getPendingPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PersonDTOResponse> patients = new ArrayList<>();
        for(Person p : patientList){
            if(p.getStatus().equalsIgnoreCase("PENDING")){
                patients.add(new PersonDTOResponse(p));
            }
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
    /**
     *
     * Funkcija za registrovanje Administratora klinike
     * @param administratorDTO
     * @return
     */
    @RequestMapping(consumes = "application/json",value = "/registerAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerAdmin(@RequestBody AdministratorDTORequest administratorDTO){
        Administrator administrator1 = (Administrator) personService.findOneByUsername(administratorDTO.getUsername());
        if(administrator1 != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        Administrator person1 = (Administrator) personService.saveAdministrator(administratorDTO,"PENDING","ROLE_ADMIN");
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(person1.getId()).toUri());
        return new ResponseEntity<>(new AdministratorDTOResponse(person1), HttpStatus.CREATED);

    }

    /**
     * Funkcija za registraovanje CCA
     * @param administratorDTO
     * @return
     */
    @RequestMapping(consumes = "application/json",value = "/registerClinicCentreAdmin",method = RequestMethod.POST)
    public ResponseEntity<?> registerClinicCentreAdmin(@RequestBody AdministratorDTORequest administratorDTO){
        Person person= personService.findOneByUsername(administratorDTO.getUsername());
        if(person != null){
            throw new ResourceConflictException(administratorDTO.getId(), "Username already exists");
        }
        ClinicsAdministrator person1 = (ClinicsAdministrator) personService.saveClinicCentreAdministrator(administratorDTO,"PENDING","ROLE_ADMIN");
        return new ResponseEntity<>(new AdministratorDTOResponse(person1), HttpStatus.CREATED);

    }
    @RequestMapping(value = "/getAppointmentRequests",method = RequestMethod.GET)
    public ResponseEntity<?> getAppointmentRequests(){
        List<AppointmentRequest> requests = appointmentRequestService.findAll();
        List<AppointmentRequestDTOResponse> responses = new ArrayList<>();
        for(AppointmentRequest a : requests){
            responses.add(new AppointmentRequestDTOResponse(a.getId(),a.getDoctor_id(),a.getPatient_id(),a.getDate(),a.getAppointment_type()));
        }

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
    @RequestMapping(consumes = "application/json",value ="/makeAppointment",method = RequestMethod.POST)
    public ResponseEntity<?> makeAppoitment(@RequestBody MakeAppointmentDTORequest request){
        Appointment a = new Appointment();
        Doctor d = (Doctor) personService.findOneById(request.getDoctor());
        Patient p = (Patient) personService.findOneById(request.getPatient());
        Room r = roomService.findOneById(request.getRoom());
        appointmentRequestService.delete(request.getAppointmentRequestId());
        a.setPatient(p);
        a.setDoctor(d);
        a.setRoom(r);
        a.setStatus("ACTIVE");
        a.setType(request.getType());
        a.setDate(request.getDate());
        a.setDiscount(10);
        a.setPrice(10000);
        Appointment a1 = appointmentService.save(a);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @RequestMapping(consumes = "application/json",value = "/createDoctor",method = RequestMethod.POST)
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorDTORequest doc) {
        personService.saveDoctor(doc);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
