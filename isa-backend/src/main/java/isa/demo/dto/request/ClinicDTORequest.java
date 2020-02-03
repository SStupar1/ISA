package isa.demo.dto.request;

import isa.demo.model.Clinic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDTORequest {

    private Long id;
    private String name;
    private String address;
    private String description;

    public ClinicDTORequest() {
    }


    public ClinicDTORequest(Clinic clinic) {
        this(clinic.getId(),clinic.getName(),clinic.getAddress(),clinic.getDescription());
    }

    public ClinicDTORequest(Long id, String name, String address, String description) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }
}