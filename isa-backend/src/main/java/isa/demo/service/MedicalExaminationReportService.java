package isa.demo.service;

import isa.demo.dto.request.MedicalExaminationReportDTORequest;
import isa.demo.model.Doctor;
import isa.demo.model.MedicalExaminationReport;
import isa.demo.model.Patient;
import isa.demo.repository.MedicalExaminationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MedicalExaminationReportService {
    @Autowired
    private MedicalExaminationReportRepository medicalExaminationReportRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private MedicineService medicineService;

    public MedicalExaminationReport createNew(MedicalExaminationReportDTORequest medicalExaminationDTO) {
        MedicalExaminationReport medicalExaminationReport = new MedicalExaminationReport();
        medicalExaminationReport.setDescription(medicalExaminationDTO.getDescription());
        medicalExaminationReport.setDoctor((Doctor) personService.findOneById(medicalExaminationDTO.getDoctor()));
        medicalExaminationReport.setDiagnosis(diagnosisService.findOneById(medicalExaminationDTO.getDiagnosis()));
        medicalExaminationReport.setMedicine(medicineService.findOneById(medicalExaminationDTO.getMedicine()));
        medicalExaminationReport.setPatient((Patient) personService.findOneById(medicalExaminationDTO.getPatient()));

        return medicalExaminationReportRepository.save(medicalExaminationReport);

    }
    public Set<MedicalExaminationReport> findByPatientId(Long id){
        Set<MedicalExaminationReport> mer = medicalExaminationReportRepository.findAllPatients(id);
        return mer;
    }
}
