package isa.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Diagnosis {

    //region column definitions
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true,nullable = false)
    private String name;
    //endregion

    @JsonBackReference
    @OneToMany(mappedBy = "diagnosis",fetch = FetchType.LAZY)
    private Set<MedicalExaminationReport> medicalExaminationReports = new HashSet<>();

    public Diagnosis() {
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diagnosis d = (Diagnosis) o;
        if (d.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, d.id);
    }
}
