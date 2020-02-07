package isa.demo.controller;

import isa.demo.dto.request.AppointmentDTORequest;
import isa.demo.dto.request.CreatePredefAppointmentDTORequest;
import isa.demo.dto.request.PredefAppointmentDTORequest;
import isa.demo.dto.response.AppointmentDTOResponse;
import isa.demo.model.Appointment;
import isa.demo.model.Patient;
import isa.demo.model.Person;
import isa.demo.service.AppointmentService;
import isa.demo.service.ClinicService;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private PersonService personService;

    @RequestMapping(value="/getPredefAppointment",method = RequestMethod.GET)
    public ResponseEntity<?> getPredefAppointments(){
        List<Appointment> apps =  appointmentService.findAll();
        List<AppointmentDTOResponse> response = new ArrayList<>();
        for(Appointment a : apps){
            if(a.getPatient() == null){
                response.add(new AppointmentDTOResponse(a.getId(),a.getDoctor(),a.getDate(),a.getType(),null,a.getPrice(),a.getDiscount(),a.getRoom().getName()));
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json",value = "/reservePredef")
    public ResponseEntity<?> reservePredefApp(@RequestBody PredefAppointmentDTORequest request){
        appointmentService.reserve(request);
        return new ResponseEntity<>(request,HttpStatus.ACCEPTED);

    }
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/createPredef",method = RequestMethod.POST)
    public ResponseEntity<?> createPredef(@RequestBody CreatePredefAppointmentDTORequest req){
        appointmentService.createPredef(req);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @RequestMapping(value="/getAppointmentsForDoctor",method = RequestMethod.GET)
    public ResponseEntity<?> getAppointmentsForDoctor(@RequestParam String doctorId){
        Long id = Long.parseLong(doctorId.substring(1));
        List<Appointment> apps = appointmentService.findAllByDoctorId(id);
        List<AppointmentDTOResponse> appDto = new ArrayList<>();
        for (Appointment a:apps) {
            if(!a.getStatus().equals("") || a.getPatient() == null)
                continue;

            AppointmentDTOResponse ap = new AppointmentDTOResponse(a);

            appDto.add(ap);
        }
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/cancleAppointment",consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> cancleAppointment(@RequestBody MedicalExaminationId request) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Person p = personService.findOneByUsername(username);
        appointmentService.cancle(p,request.id);

        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);


    }
    static class MedicalExaminationId {
        public Long id;
    }
    @RequestMapping(value="/getIncomingAppointments",method = RequestMethod.GET)
    public ResponseEntity<?> getIncommingAppointments() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Person p = personService.findOneByUsername(username);
        List<AppointmentDTOResponse> response = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        String str = d.toString().substring(0,10);
        if (p instanceof Patient) {
            List<Appointment> apps = appointmentService.findAll();

            for (Appointment a : apps) {
                if (a.getPatient() != null) {

                    if (a.getPatient().getId() == p.getId()) {
                        response.add(new AppointmentDTOResponse(a.getId(), a.getDoctor(), a.getDate(), a.getType(), null, a.getPrice(), a.getDiscount(), a.getRoom().getName()));
                    }
                }

            }
        } else {
            List<Appointment> apps = appointmentService.findAll();

            for (Appointment a : apps) {
                if (a.getPatient() != null) {

                    if (a.getDoctor().getId() == p.getId() && a.getPatient() != null) {
                        response.add(new AppointmentDTOResponse(a.getId(), a.getDoctor(), a.getDate(), a.getType(), null, a.getPrice(), a.getDiscount(), a.getRoom().getName()));
                    }
                }

            }


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
