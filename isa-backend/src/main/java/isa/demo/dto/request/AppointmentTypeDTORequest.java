package isa.demo.dto.request;

import isa.demo.model.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentTypeDTORequest {
    private Long id;
    private String name;

    public AppointmentTypeDTORequest(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public AppointmentTypeDTORequest(AppointmentType m){
        this(m.getId(),m.getName());
    }

}
