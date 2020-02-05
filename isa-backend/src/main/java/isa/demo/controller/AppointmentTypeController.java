package isa.demo.controller;

import isa.demo.dto.request.AppointmentTypeDTORequest;
import isa.demo.dto.response.AppointmentTypeDTOResponse;
import isa.demo.model.AppointmentType;
import isa.demo.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "api/appointmenttype")
public class AppointmentTypeController {
    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @RequestMapping(value = "/addAppointmentType",consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> addAppointmentType(@RequestBody AppointmentTypeDTORequest appointmentTypeDTO){
        AppointmentType appointmentType = appointmentTypeService.findOneByName(appointmentTypeDTO.getName());
        if(appointmentType!= null) {
            return new ResponseEntity<>(new AppointmentTypeDTOResponse(appointmentType), HttpStatus.BAD_REQUEST);
        }
        appointmentType = appointmentTypeService.save(appointmentTypeDTO);
        return new ResponseEntity<>(new AppointmentTypeDTOResponse(appointmentType), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllAppointmentTypes() {
        List<AppointmentType> appointmentTypes = appointmentTypeService.findAll();
        List<AppointmentTypeDTOResponse> appointmentTypeDTOS = new ArrayList<>();
        for(AppointmentType m : appointmentTypes){
            appointmentTypeDTOS.add(new AppointmentTypeDTOResponse(m));
        }
        return new ResponseEntity<>(appointmentTypeDTOS,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<?> updateAppointmentType(@RequestBody AppointmentTypeDTORequest appType){
        AppointmentType appointmentType = appointmentTypeService.findOneById(appType.getId());
        if(appointmentType.getId() != null){
            //appointmentTypeService.updateAppointmentType(appType.getName(),appType.getId());
            appointmentType = appointmentTypeService.findOneById(appType.getId());
            return new ResponseEntity<>(new AppointmentTypeDTOResponse(appointmentType),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new AppointmentTypeDTOResponse(),HttpStatus.BAD_REQUEST);
    }
}
