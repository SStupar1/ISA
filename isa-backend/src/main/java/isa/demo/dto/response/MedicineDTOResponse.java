package isa.demo.dto.response;

import isa.demo.model.Medicine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDTOResponse {
    private Long id;
    private String name;

    public MedicineDTOResponse(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public MedicineDTOResponse(){

    }
    public MedicineDTOResponse(Medicine m){
        this(m.getId(),m.getName());
    }
}
