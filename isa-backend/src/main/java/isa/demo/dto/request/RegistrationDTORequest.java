package isa.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTORequest {
    private String firstName;
    private String lastName;
    private String address;
    private String jmbg;
    private String username;
    private String password;
}
