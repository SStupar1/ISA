package isa.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CCA")
@Getter
@Setter
public class ClinicsAdministrator extends Person {

}