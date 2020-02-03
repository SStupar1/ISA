package isa.demo.controller;

import isa.demo.dto.request.AppointmentDTORequest;
import isa.demo.dto.request.ClinicDTORequest;
import isa.demo.dto.response.AppointmentDTOResponse;
import isa.demo.dto.response.ClinicDTOResponse;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.exception.ResourceConflictException;
import isa.demo.model.Appointment;
import isa.demo.model.Clinic;
import isa.demo.service.AppointmentService;
import isa.demo.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value="/addClinic",consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> addClinic(@RequestBody ClinicDTORequest clinic){

        Clinic clinic1 = clinicService.findOneByName(clinic.getName());
        if(clinic1 != null){
            throw new ResourceConflictException(clinic1.getId(), "Clinic already exists");
        }
        clinicService.save(clinic);
        return new ResponseEntity<>(clinic, HttpStatus.CREATED);
    }
    @RequestMapping(value="/findClinic",method = RequestMethod.GET)
    public ResponseEntity<?> findClinics(@RequestParam String date, @RequestParam String type){
        List<ClinicDTOResponse> clinics = new ArrayList<>();
        if(date.equals("")&&type.equals("")){
            List<Clinic> clinic = clinicService.findAll();
            for (Clinic c:clinic) {
                clinics.add(new ClinicDTOResponse(c));
            }
            return new ResponseEntity<>(clinics, HttpStatus.OK);
        }

        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            return null;
        }
        List<Appointment> appointments = appointmentService.findAppointments(type);
        System.out.println(appointments);
        List<AppointmentDTOResponse> apps = new ArrayList<>();
        for (Appointment a:appointments) {

            apps.add(new AppointmentDTOResponse(a));

        }
        for (AppointmentDTOResponse a:apps) {
            if(clinics.contains(a.getDoctor().getClinic()))
                continue;
            clinics.add(new ClinicDTOResponse(a.getDoctor().getClinic()));
        }

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }
    @RequestMapping(value="/findClinic/doctors",method = RequestMethod.GET)
    public ResponseEntity<?> findDoctors(@RequestParam String clinicName,@RequestParam String date, @RequestParam String type){
        System.out.println(clinicName);
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        try {
            date1 = formatter6.parse(date);
        }
        catch(Exception e) {
            new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        List<Appointment> appointments = appointmentService.findAppointments(type);
        List<AppointmentDTOResponse> apps = new ArrayList<>();
        List<PersonDTOResponse> doctors = new ArrayList<>();
        for (Appointment a:appointments) {
            apps.add(new AppointmentDTOResponse(a));
        }
        for (AppointmentDTOResponse a:apps) {
            System.out.println(a.getDoctor().getClinic().getName());
            if(doctors.contains(a.getDoctor().getClinic()) || !a.getDoctor().getClinic().getName().equals(clinicName))
                continue;
            doctors.add(new PersonDTOResponse(a.getDoctor()));
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
