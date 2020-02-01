package isa.demo.dto.response;

import isa.demo.model.Diagnosis;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisDTOResponse {

    private Long id;
    private String name;

    public DiagnosisDTOResponse(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public DiagnosisDTOResponse(){

    }
    public DiagnosisDTOResponse(Diagnosis d){
        this(d.getId(),d.getName());
    }
}
