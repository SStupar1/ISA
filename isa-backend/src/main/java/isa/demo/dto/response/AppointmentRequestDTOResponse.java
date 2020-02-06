package isa.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTOResponse {
    private Long id;
    private Long doctor;
    private Long patient;
    private Date date;
    private String type;


}


