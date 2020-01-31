package isa.demo.controller;

import isa.demo.dto.request.PersonDTORequest;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.model.Person;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/person",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService personService;

    //funckija koja sluzi da se preuzme jedan korisnik iz baze
    @RequestMapping(consumes = "application/json",value="/getByEmail",method = RequestMethod.POST)
    public ResponseEntity<?> getByEmail(@RequestBody PersonDTORequest person) {
        Person personRet = personService.findOneByUsername(person.getUsername());
        if (personRet == null)
            return null;
        return new ResponseEntity<>(new PersonDTOResponse(personRet), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<PersonDTOResponse> updateMedicalStaff(@RequestBody PersonDTORequest person){
        Person person1 = personService.findOneById(person.getId());
        if(person1.getId() != null){
            personService.updateUser(person.getFirstName(),person.getLastName(),person.getAddress(),person.getId());
            person1 = personService.findOneById(person.getId());
            return new ResponseEntity<>(new PersonDTOResponse(person1),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new PersonDTOResponse(),HttpStatus.BAD_REQUEST);
    }
}
