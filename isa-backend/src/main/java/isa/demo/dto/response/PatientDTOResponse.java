package isa.demo.dto.response;

import isa.demo.model.Patient;
import isa.demo.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTOResponse extends PersonDTOResponse {
    private String jmbg;
    private String name;
    private String lastname;

    public PatientDTOResponse() {
    }

    public PatientDTOResponse(String jmbg, String name, String lastname) {
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTOResponse(Long id, String firstName, String lastName, String address, String password, String username, String status, String jmbg, String name, String lastname) {
        super(id, firstName, lastName, address, password, username, status);
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTOResponse(Patient p){
        super((Person) p);
        this.jmbg = p.getJmbg();
    }
}
