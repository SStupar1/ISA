package isa.demo.controller;

import isa.demo.dto.request.ClinicDTORequest;
import isa.demo.dto.response.ClinicDTOResponse;
import isa.demo.exception.ResourceConflictException;
import isa.demo.model.Clinic;
import isa.demo.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @RequestMapping(value="/addClinic",consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> addClinic(@RequestBody ClinicDTORequest clinic){

        Clinic clinic1 = clinicService.findOneByName(clinic.getName());
        if(clinic1 != null){
            throw new ResourceConflictException(clinic1.getId(), "Clinic already exists");
        }
        clinicService.save(clinic);
        return new ResponseEntity<>(clinic, HttpStatus.CREATED);
    }
}
