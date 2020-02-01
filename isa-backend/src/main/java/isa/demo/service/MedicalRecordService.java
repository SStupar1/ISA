package isa.demo.service;

import isa.demo.model.MedicalRecord;
import isa.demo.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord findByPatientId(Long id) {return medicalRecordRepository.findOneByPatientId(id);}
    public MedicalRecord findOneById(Long id) {return medicalRecordRepository.findOneById(id);}
    public int updateMedicalRecord(double height,double weight,double diopter,String alergies,String bloodType,Long id) {
        return medicalRecordRepository.updateMedicalRecord(height,weight,diopter,alergies,bloodType,id);
    }
}
