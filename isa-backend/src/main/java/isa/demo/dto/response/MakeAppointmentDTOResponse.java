package isa.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakeAppointmentDTOResponse {
    private String type;
    private Date date;
    private double price;
    private int discount;
    private Long room;
    private Long doctor;
    private Long patient;
    private Long appointmentRequestId;






}