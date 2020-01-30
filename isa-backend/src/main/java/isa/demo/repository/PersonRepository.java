package isa.demo.repository;

import isa.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findAll();

    Person findOneById(Long id);
    Person findOneByUsername(String username);
}
