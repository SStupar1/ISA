package isa.demo.dto.request;

import isa.demo.model.Medicine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDTORequest {
    private Long id;
    private String name;

    public MedicineDTORequest(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public MedicineDTORequest(){

    }
    public MedicineDTORequest(Medicine m){
        this(m.getId(),m.getName());
    }
}
