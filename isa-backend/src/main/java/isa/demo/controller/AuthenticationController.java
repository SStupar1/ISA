package isa.demo.controller;

import isa.demo.dto.request.RegistrationDTORequest;
import isa.demo.dto.response.PersonDTOResponse;
import isa.demo.exception.ResourceConflictException;
import isa.demo.model.Patient;
import isa.demo.model.Person;
import isa.demo.model.security.UserTokenState;
import isa.demo.security.Auth.JwtAuthenticationRequest;
import isa.demo.security.TokenUtils;
import isa.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/auth",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonService personService;


    /**
     * funkcija koja obavlja login,i salje token na front da ga korisnik posle koristi prilikom autorizacije
     * najveci deo koda dolazi iz spring security-a
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     * @throws IOException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException, IOException {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()

                        ,authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login request: username:" + authentication.getName());

        Person person = (Person) authentication.getPrincipal();
        System.out.println(person);
        String jwt;
        jwt =tokenUtils.generateToken(person);
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));

    }

    /**
     * Funkcija za registrovanje pacijenata putem forme za registraciju na frontu
     * @param patient
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody RegistrationDTORequest patient, UriComponentsBuilder ucBuilder){
        Person person = personService.findOneByUsername(patient.getUsername());
        if(person != null){
            throw new ResourceConflictException(person.getId(), "Username already exists");
        }
        //System.out.println(patient);
        Person person1 = personService.save(patient,"PENDING","ROLE_USER");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(person1.getId()).toUri());
        return new ResponseEntity<>(new PersonDTOResponse(person1), HttpStatus.CREATED);
    }


    /**
     * ova funkcija dolazi is spring security-a i sluzi da se token refresuje kada istenke
     * u ovom projektu nece biti koriscena
     * @param request
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        Person user = (Person) this.personService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }
    @RequestMapping(value = "/activateAccount/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String activateAccount(@PathVariable Long id){
        Person p = personService.findOneById(id);
        if(p.getStatus().equalsIgnoreCase("ACCEPTED")){
            personService.updatePersonStatus("ACTIVE",id);
            return ("<html>\n" +
                    "    <head></head>\n" +
                    "    <body>\n" +
                    "            <h4>Uspesno ste aktivirali vas nalog, mozete se ulogovati na liknu: <a href=\"http://localhost:4200/login\">LOGIN</a></h4>\n" +
                    "    </body>\n" +
                    "</html>");

        }else{
            return ("<html>\n" +
                    "    <head></head>\n" +
                    "    <body>\n" +
                    "            <h4>Nije moguce aktivirati vas nalog ili je nalog vec aktivan</h4>\n" +
                    "    </body>\n" +
                    "</html>");

        }


    }

}