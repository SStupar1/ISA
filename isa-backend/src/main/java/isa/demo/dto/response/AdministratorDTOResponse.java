package isa.demo.dto.response;

import isa.demo.model.Administrator;
import isa.demo.model.ClinicsAdministrator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDTOResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;
    private Long clinic_id;

    public AdministratorDTOResponse() {
    }

    public AdministratorDTOResponse(Long id, String firstName, String lastName, String address, String password, String username, String status, Long clinic_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
        this.status = status;
        this.clinic_id = clinic_id;
    }
    public AdministratorDTOResponse(Administrator a){
        this(a.getId(),a.getFirstName(),a.getLastName(),a.getAddress(),a.getPassword(),a.getUsername(),a.getStatus(),a.getClinic().getId());
    }
    public AdministratorDTOResponse(ClinicsAdministrator a){
        this(a.getId(),a.getFirstName(),a.getLastName(),a.getAddress(),a.getPassword(),a.getUsername(),a.getStatus(),a.getId());
    }
}
