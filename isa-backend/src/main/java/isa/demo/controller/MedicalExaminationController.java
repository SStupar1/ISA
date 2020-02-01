package isa.demo.controller;

import isa.demo.dto.request.MedicalExaminationReportDTORequest;
import isa.demo.dto.response.MedicalExaminationDTOResponse;
import isa.demo.dto.response.MedicalExaminationReportDTOResponse;
import isa.demo.model.MedicalExaminationReport;
import isa.demo.service.MedicalExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "api/medicalExamination")
public class MedicalExaminationController {

    @Autowired
    MedicalExaminationReportService medicalExaminationReportService;

    @RequestMapping(value = "/new",consumes = "application/json" ,method = RequestMethod.POST)
    public ResponseEntity<?> addNewReport(@RequestBody MedicalExaminationReportDTORequest medicalExaminationDTO){
        MedicalExaminationReport medicalExaminationReport = medicalExaminationReportService.createNew(medicalExaminationDTO);
        return new ResponseEntity<>(new MedicalExaminationReportDTOResponse(medicalExaminationReport), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/getAll/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getAllForSiglePatient(@PathVariable Long id){

        Set<MedicalExaminationReport> medicalExaminationReports = medicalExaminationReportService.findByPatientId(id);
        Set<MedicalExaminationDTOResponse> medicalExaminationResponseDTOSet = new HashSet<>();
        for(MedicalExaminationReport m : medicalExaminationReports){
            medicalExaminationResponseDTOSet.add(new MedicalExaminationDTOResponse(m));
        }
        return new ResponseEntity<>(medicalExaminationResponseDTOSet,HttpStatus.OK);
    }
}
