package isa.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "end_date",nullable = false)
    private Date endDate;

    @Column(name = "status",nullable = false)
    private String status;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Person staff;

    public Vacation() {
    }

    public Vacation(Date startDate, Date endDate, String status, Person staff) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.staff = staff;
    }

}
