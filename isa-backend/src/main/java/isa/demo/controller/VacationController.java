package isa.demo.controller;

import isa.demo.dto.response.VacationRequestDTOResponse;
import isa.demo.model.Administrator;
import isa.demo.model.Doctor;
import isa.demo.model.Vacation;
import isa.demo.service.PersonService;
import isa.demo.service.VacationService;
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
@RequestMapping(value = "api/vacation",produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @Autowired
    private PersonService personService;

    @GetMapping(value="/getPending")
    public ResponseEntity<?> getAllClinics() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Administrator p = (Administrator) personService.findOneByUsername(username);

        List<Vacation> pending = vacationService.findPending();
        List<VacationRequestDTOResponse> requests = new ArrayList<>();

        for(Vacation v : pending){
            requests.add(new VacationRequestDTOResponse(v));

        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json", value = "/create")
    public ResponseEntity<?> createRequest(@RequestBody VacationRequest vr) {
        String dat = vr.startDate;
        int year = Integer.parseInt(dat.split("-")[0]);
        int month = Integer.parseInt(dat.split("-")[1]);
        int day = Integer.parseInt(dat.split("-")[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date d1 = cal.getTime();

        String dat2 = vr.endDate;
        int year2 = Integer.parseInt(dat2.split("-")[0]);
        int month2 = Integer.parseInt(dat2.split("-")[1]);
        int day2 = Integer.parseInt(dat2.split("-")[2]);
        cal.set(Calendar.YEAR, year2);
        cal.set(Calendar.MONTH, month2-1);
        cal.set(Calendar.DAY_OF_MONTH, day2);
        Date d2 = cal.getTime();
        Vacation v = new Vacation(d1, d2, "PENDING", personService.findOneById(vr.id));
        vacationService.save(v);
        return new ResponseEntity<>(new VacationRequestDTOResponse(v), HttpStatus.ACCEPTED);
    }

    static class VacationRequest {
        public String startDate;
        public String endDate;
        public Long id;
    }

    static class Id {
        public Long id;
    }
    @PostMapping(consumes = "application/json", value = "/decline")
    public ResponseEntity<?> declineRequest(@RequestBody Odbijanje id) {
        Vacation v = vacationService.findOneById(id.id);
        if(v.getId() != null) {
            vacationService.updateVacationStatus("DECLINED", id.id);
            v = vacationService.findOneById(id.id);
            String razlog = id.razlog;
            return new ResponseEntity<>(new VacationRequestDTOResponse(v), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new VacationRequestDTOResponse(), HttpStatus.BAD_REQUEST);
    }

    static class Odbijanje {
        public Long id;
        public String razlog;
    }
    @PostMapping(consumes = "application/json", value = "/approve")
    public ResponseEntity<?> approveRequest(@RequestBody Id id) {
        Vacation v = vacationService.findOneById(id.id);
        if(v.getId() != null) {
            vacationService.updateVacationStatus("APPROVED", id.id);
            v = vacationService.findOneById(id.id);
            return new ResponseEntity<>(new VacationRequestDTOResponse(v), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new VacationRequestDTOResponse(), HttpStatus.BAD_REQUEST);
    }
}
