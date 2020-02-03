package isa.demo.dto.response;

import isa.demo.model.Clinic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDTOResponse {

    private Long id;
    private String name;
    private String address;
    private String description;

    public ClinicDTOResponse() {
    }


    public ClinicDTOResponse(Clinic clinic) {
        this(clinic.getId(),clinic.getName(),clinic.getAddress(),clinic.getDescription());
    }

    public ClinicDTOResponse(Long id, String name, String address, String description) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }
}
