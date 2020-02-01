package isa.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("N")
public class Nurse extends Person {

    @Column(name = "worktime_start")
    private String worktimeStart;

    @Column(name="worktime_end")
    private String worktimeEnd;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinic clinic;

    @JsonBackReference
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Vacation> vacations = new HashSet<>();




}