package isa.demo.service;

import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import isa.demo.repository.AppointmentRepository;
import isa.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonRepository personRepository;


    public List<Appointment> findAll(){return appointmentRepository.findAll();}
    public List<Appointment> findAppointments(String type){return appointmentRepository.findAppointments(type);}
    public Appointment save(Appointment a) {  return appointmentRepository.save(a); }
    public List<Appointment> findAllByDoctorId(Long id){
        Doctor d = (Doctor) personRepository.findOneById(id);
        return appointmentRepository.findAllByDoctor(d);
    }
}
