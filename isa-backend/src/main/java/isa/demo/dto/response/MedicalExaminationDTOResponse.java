package isa.demo.dto.response;

import isa.demo.model.MedicalExaminationReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalExaminationDTOResponse {
    private Long patient;
    private Long doctor;
    private String description;
    private String diagnosis;
    private String medicine;

    public MedicalExaminationDTOResponse(MedicalExaminationReport m){
        this(m.getPatient().getId(),m.getDoctor().getId(),m.getDescription(),m.getDiagnosis().getName(),m.getMedicine().getName());
    }
}
