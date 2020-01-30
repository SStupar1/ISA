package isa.demo.controller;

import isa.demo.dto.request.PersonDTORequest;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.model.Person;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
}
