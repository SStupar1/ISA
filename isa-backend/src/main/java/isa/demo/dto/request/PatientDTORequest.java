package isa.demo.dto.request;

import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.model.Patient;
import isa.demo.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTORequest extends PersonDTORequest{
    private String jmbg;
    private String name;
    private String lastname;

    public PatientDTORequest() {
    }

    public PatientDTORequest(String jmbg, String name, String lastname) {
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTORequest(Long id, String firstName, String lastName, String address, String password, String username, String status, String jmbg, String name, String lastname) {
        super(id, firstName, lastName, address, password, username, status);
        this.jmbg = jmbg;
        this.name = name;
        this.lastname = lastname;
    }

    public PatientDTORequest(Patient p){
        super((Person) p);
        this.jmbg = p.getJmbg();
    }
}
