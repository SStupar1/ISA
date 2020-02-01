package isa.demo.dto.request;

import isa.demo.model.Diagnosis;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisDTORequest {

    private Long id;
    private String name;

    public DiagnosisDTORequest(Long id,String name){
        this.id = id;
        this.name = name;
    }
    public DiagnosisDTORequest(){

    }
    public DiagnosisDTORequest(Diagnosis d){
        this(d.getId(),d.getName());
    }
}
