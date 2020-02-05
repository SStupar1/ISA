package isa.demo.controller;

import isa.demo.dto.request.PersonDTORequest;
import isa.demo.dto.response.AdministratorDTOResponse;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.model.Administrator;
import isa.demo.model.Doctor;
import isa.demo.model.Person;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @RequestMapping(consumes = "application/json",value ="/changePassword",method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChanger passwordChanger) {
        personService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }
    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
    @RequestMapping(value = "/getAllDoctors",method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctors() {
        List<Doctor> doctors = personService.findDoctors();
        List<PersonDTOResponse> response = new ArrayList<>();
        for(Doctor d : doctors){
            response.add(new PersonDTOResponse((Person) d));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    @RequestMapping(value="/getAdminByEmail",method = RequestMethod.GET)
    public ResponseEntity<?> getAdminByEmail(@RequestParam String mail) {
        Administrator a = personService.findAdmin(mail).get(0);
        if (a == null)
            return null;
        return new ResponseEntity<>(new AdministratorDTOResponse(a), HttpStatus.OK);

    }

}
