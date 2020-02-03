package isa.demo.dto.request;

import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentDTORequest {
    private Doctor doctor;
    private Date date;
    private String type;
    private Long patientId;


    public AppointmentDTORequest() {

    }
    public AppointmentDTORequest(Appointment a) {
        this(a.getDoctor(),a.getDate(),a.getType(),a.getPatient().getId());
    }
    public AppointmentDTORequest(Doctor doctor, Date date, String type,Long patientId) {
        this.doctor = doctor;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
    }
}
