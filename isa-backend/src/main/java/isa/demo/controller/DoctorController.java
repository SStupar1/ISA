package isa.demo.controller;

import isa.demo.dto.response.DoctorDTOResponse;
import isa.demo.dto.response.SearchDoctorsAppointmentDTOResponse;
import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import isa.demo.service.AppointmentService;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {

    @Autowired
    private PersonService personService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/findDoctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctor(@RequestParam String name, @RequestParam String lastname, @RequestParam String ocena){
        List<Doctor> doctorList = personService.findDoctors();
        List<Appointment> apps = appointmentService.findAll();
        List<DoctorDTOResponse> doctors = new ArrayList<>();
        for(Doctor p : doctorList){
            if(!p.getLastName().equals(lastname)&&!lastname.equals(""))continue;
            if(!p.getFirstName().equals(name)&&!name.equals(""))continue;
            DoctorDTOResponse newDoc = new DoctorDTOResponse(p);
            for (Appointment a:apps) {
                System.out.println(newDoc.getFirstName()+" "+a.getDoctor().getFirstName());
                if(a.getDoctor().getFirstName().equals(newDoc.getFirstName()) && a.getPatient() == null){
                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = formatter.format(a.getDate());
                    newDoc.getDates().add(new SearchDoctorsAppointmentDTOResponse(strDate,a.getId()));
                }
            }
            doctors.add(newDoc);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
