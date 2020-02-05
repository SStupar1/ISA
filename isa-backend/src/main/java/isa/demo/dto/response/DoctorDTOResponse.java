package isa.demo.dto.response;

import isa.demo.model.Doctor;
import isa.demo.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DoctorDTOResponse extends PersonDTOResponse{

    private String name;
    private String lastname;
    private List<SearchDoctorsAppointmentDTOResponse> dates=new ArrayList<>();
    private Long clinic;

    public DoctorDTOResponse() {
    }

    public DoctorDTOResponse(Doctor p){
        super((Person) p);
    }
    public DoctorDTOResponse(String name, String lastname, Long clinic) {
        this.name = name;
        this.lastname = lastname;
        this.clinic = clinic;
    }
}
