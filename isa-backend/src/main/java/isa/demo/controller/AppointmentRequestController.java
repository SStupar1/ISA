package isa.demo.controller;

import isa.demo.dto.request.AppointmentRequestDTORequest;
import isa.demo.model.AppointmentRequest;
import isa.demo.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "api/appointmentrequest")
public class AppointmentRequestController {
    @Autowired
    private AppointmentRequestService appointmentRequestService;

    @RequestMapping(value = "/addAppointmentRequest",consumes = "application/json", method = RequestMethod.POST)


    public ResponseEntity<?> addAppointmentType(@RequestBody AppointmentRequestDTORequest request){
        int year = Integer.parseInt(request.getDate().split("-")[0]);
        int month = Integer.parseInt(request.getDate().split("-")[1]);
        int day = Integer.parseInt(request.getDate().split("-")[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        Date d = cal.getTime();
        d.setHours(0);
        d.setMinutes(0);
        d.setSeconds(0);
        AppointmentRequest a= appointmentRequestService.save(request.getDoctor(), request.getPatient(), d, request.getType());
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

}
