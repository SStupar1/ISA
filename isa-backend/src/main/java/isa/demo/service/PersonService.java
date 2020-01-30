package isa.demo.service;

import isa.demo.model.Person;
import isa.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findOneByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return person;
        }
    }

    public Person findOneByUsername(String username) { return personRepository.findOneByUsername(username); }

}
