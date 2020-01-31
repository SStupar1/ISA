package isa.demo.service;

import isa.demo.model.Person;
import isa.demo.model.security.Authority;
import isa.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findOneByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return person;
        }
    }

    /**
     * funkcija za upisivanje osobe u bazu,posto je person nad klasa u isa hijerarhiji
     * moze se koristiti za upis svih vrsta osoba,pacijente doktore itd..
     * @param person
     * @param status
     * @param role
     * @return
     */
    public Person save(Person person,String status,String role) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setEnabled(true);

        if(status != null){
            person.setStatus(status);
        }
        List<Authority> auth =authorityService.findByName(role);
        person.setAuthorities(auth);

        return personRepository.save(person);

    }

    public Person findOneByUsername(String username) { return personRepository.findOneByUsername(username); }

}
