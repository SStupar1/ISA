package isa.demo.controller;

import isa.demo.dto.request.AdministratorDTORequest;
import isa.demo.dto.response.AdministratorDTOResponse;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.exception.ResourceConflictException;
import isa.demo.model.Administrator;
import isa.demo.model.ClinicsAdministrator;
import isa.demo.model.Patient;
import isa.demo.model.Person;
import isa.demo.service.EmailService;
import isa.demo.service.PersonService;
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
}
