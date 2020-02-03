package isa.demo.controller;

import isa.demo.dto.response.PatientDTOResponse;
import isa.demo.model.Patient;
import isa.demo.model.Person;
import isa.demo.service.AppointmentService;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {
    @Autowired
    private PersonService personService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/getAllPatients",method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatients() {
        List<Person> patientList = personService.findByType("P");
        List<PatientDTOResponse> patients = new ArrayList<>();
        for(Person p : patientList){
            patients.add(new PatientDTOResponse((Patient) p));
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @RequestMapping(value="/findPatients",method = RequestMethod.GET)
    public ResponseEntity<?> findPatient(@RequestParam String name, @RequestParam String lastname, @RequestParam String jmbg){
        List<Patient> patientList = personService.findPatients();
        List<PatientDTOResponse> patients = new ArrayList<>();
        for(Patient p : patientList){
            if(!p.getJmbg().equals(jmbg)&&!jmbg.equals(""))continue;
            if(!p.getLastName().equals(lastname)&&!lastname.equals(""))continue;
            if(!p.getFirstName().equals(name)&&!name.equals(""))continue;
            patients.add(new PatientDTOResponse(p));
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
}
