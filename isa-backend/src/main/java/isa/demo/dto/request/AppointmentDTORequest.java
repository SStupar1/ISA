package isa.demo.dto.request;

import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentDTORequest {
    private Long appointmentId;
    private Doctor doctor;
    private Date date;
    private String type;
    private Long patientId;
    private double price;
    private long discount;
    private String room;


    public AppointmentDTORequest() {

    }
    public AppointmentDTORequest(Appointment a) {
        this(a.getId(),a.getDoctor(),a.getDate(),a.getType(),a.getPatient().getId(),a.getPrice(),a.getDiscount(),a.getRoom().getName());
    }
    public AppointmentDTORequest(Long appointmentId,Doctor doctor, Date date, String type,Long patientId,double price,long discount,String room) {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
        this.price = price;
        this.discount = discount;
        this.room = room;
    }
}
