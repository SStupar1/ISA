package isa.demo.repository;

import isa.demo.model.MedicalExaminationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MedicalExaminationReportRepository extends JpaRepository<MedicalExaminationReport,Long> {
    @Query("SELECT mer FROM MedicalExaminationReport mer where mer.patient.id=:id")
    Set<MedicalExaminationReport> findAllPatients(@Param("id") Long id);

}
