package isa.demo.service;

import isa.demo.dto.request.DiagnosisDTORequest;
import isa.demo.model.Diagnosis;
import isa.demo.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis findOneByName(String name) {return diagnosisRepository.findByName(name);}
    public Diagnosis findOneById(Long id) {return diagnosisRepository.getOne(id);}
    public List<Diagnosis> findAll() { return diagnosisRepository.findAll();}
    public Diagnosis save(DiagnosisDTORequest diagnosisDTO) {
        Diagnosis d = new Diagnosis();
        d.setName(diagnosisDTO.getName());
        return diagnosisRepository.save(d);
    }
}
