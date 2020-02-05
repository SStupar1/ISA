package isa.demo.repository;

import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a where a.type=?1")
    List<Appointment> findAppointments(String type);

    List<Appointment> findAll();
    List<Appointment> findAllByDoctor(Doctor d);
    Appointment findOneById(Long id);
}
