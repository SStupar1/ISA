package isa.demo.repository;

import isa.demo.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    List<Diagnosis> findAll();
    Diagnosis findByName(String name);
}
