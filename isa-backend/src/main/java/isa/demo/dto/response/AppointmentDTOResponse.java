package isa.demo.dto.response;

import isa.demo.model.Appointment;
import isa.demo.model.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentDTOResponse {
    private Doctor doctor;
    private Date date;
    private String type;
    private Long patientId;


    public AppointmentDTOResponse() {

    }
    public AppointmentDTOResponse(Appointment a) {
        this(a.getDoctor(),a.getDate(),a.getType(),a.getPatient().getId());
    }
    public AppointmentDTOResponse(Doctor doctor, Date date, String type,Long patientId) {
        this.doctor = doctor;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
    }
}
