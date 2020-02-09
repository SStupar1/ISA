package isa.demo.controller;

import isa.demo.dto.request.DiagnosisDTORequest;
import isa.demo.dto.response.DiagnosisDTOResponse;
import isa.demo.model.Diagnosis;
import isa.demo.service.DiagnosisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/diagnosis")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    @RequestMapping(value = "/addDiagnosis",consumes = "application/json")
    public ResponseEntity<?> addMedicine(@RequestBody DiagnosisDTORequest diagnosisDTO){
        Diagnosis diagnosis= diagnosisService.findOneByName(diagnosisDTO.getName());
        if(diagnosis != null) {
            return new ResponseEntity<>(new DiagnosisDTOResponse(diagnosis), HttpStatus.BAD_REQUEST);
        }
        diagnosis = diagnosisService.save(diagnosisDTO);
        return new ResponseEntity<>(new DiagnosisDTOResponse(diagnosis), HttpStatus.CREATED);


    }
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllMedicine() {
        List<Diagnosis> diagnosises = diagnosisService.findAll();
        List<DiagnosisDTOResponse> diagnosisDTOS = new ArrayList<>();
        for(Diagnosis d : diagnosises){
            diagnosisDTOS.add(new DiagnosisDTOResponse(d));
        }
        return new ResponseEntity<>(diagnosisDTOS,HttpStatus.OK);
    }
}
