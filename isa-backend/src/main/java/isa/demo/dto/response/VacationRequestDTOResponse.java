package isa.demo.dto.response;

import isa.demo.model.Vacation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VacationRequestDTOResponse {

    private Long id;
    private Date startDate;
    private Date endDate;
    private String status;
    private String firstName;
    private String lastName;

    public VacationRequestDTOResponse(Long id, Date startDate, Date endDate, String status, String firstName, String lastName) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public VacationRequestDTOResponse(Vacation v) {
        this.id = v.getId();
        this.startDate = v.getStartDate();
        this.endDate = v.getEndDate();
        this.status = v.getStatus();
        this.firstName = v.getStaff().getFirstName();
        this.lastName = v.getStaff().getLastName();
    }

    public VacationRequestDTOResponse() {
    }
}
