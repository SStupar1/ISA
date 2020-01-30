package isa.demo.dto.request;

import isa.demo.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTORequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;

    public PersonDTORequest() {

    }

    public PersonDTORequest(Long id, String firstName, String lastName, String address, String password, String username,String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.username = username;
        this.status = status;
    }
    public PersonDTORequest(Person p){
        this(p.getId(),p.getFirstName(),p.getLastName(),p.getAddress(),p.getPassword(),p.getUsername(),p.getStatus());
    }
    @Override
    public String toString() {
        return "PersonDTORequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
