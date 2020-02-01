package isa.demo.dto.response;

import isa.demo.model.MedicalExaminationReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExaminationReportDTOResponse {
    private Long patient;
    private Long doctor;
    private String description;
    private Long diagnosis;
    private Long medicine;

    public MedicalExaminationReportDTOResponse(MedicalExaminationReport medicalExaminationReport){
        this(medicalExaminationReport.getPatient().getId(),
                medicalExaminationReport.getDoctor().getId(),
                medicalExaminationReport.getDescription(),
                medicalExaminationReport.getDiagnosis().getId(),
                medicalExaminationReport.getMedicine().getId());
    }


}
