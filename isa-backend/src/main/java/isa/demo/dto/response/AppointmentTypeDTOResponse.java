package isa.demo.dto.response;

import isa.demo.model.AppointmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentTypeDTOResponse {
    private Long id;
    private String name;

    public AppointmentTypeDTOResponse(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public AppointmentTypeDTOResponse(AppointmentType m){
        this(m.getId(),m.getName());
    }

}

