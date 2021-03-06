package isa.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDTOResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String status;
    private Long clinic_id;
}