package isa.demo.service;

import isa.demo.dto.request.AdministratorDTORequest;
import isa.demo.dto.request.CreateDoctorDTORequest;
import isa.demo.dto.request.RegistrationDTORequest;
import isa.demo.model.*;
import isa.demo.model.security.Authority;
import isa.demo.repository.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthenticationManager authenticationManager;



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
     * @param status
     * @param role
     * @return
     */
    public Person save(RegistrationDTORequest reg, String status, String role) {
        Patient p = new Patient();
        p.setFirstName(reg.getFirstName());
        p.setLastName(reg.getLastName());
        p.setUsername(reg.getUsername());
        p.setAddress(reg.getAddress());
        p.setJmbg(reg.getJmbg());
        p.setPassword(passwordEncoder.encode(reg.getPassword()));
        p.setEnabled(true);






        if(status != null){
            p.setStatus(status);
        }
        List<Authority> auth =authorityService.findByName(role);
        p.setAuthorities(auth);

        return personRepository.save(p);

    }

    public Person saveAdministrator(AdministratorDTORequest a, String status, String role){
        Administrator admin = new Administrator();
        admin.setFirstName(a.getFirstName());
        admin.setLastName(a.getLastName());
        admin.setUsername(a.getUsername());
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setAddress(a.getAddress());
        admin.setClinic(clinicService.findOneById(a.getClinic_id()));
        admin.setStatus(status);
        List<Authority> auth =authorityService.findByName(role);
        admin.setAuthorities(auth);
        admin.setEnabled(true);

        return personRepository.save(admin);

    }
    public Person saveClinicCentreAdministrator(AdministratorDTORequest a,String status,String role){
        ClinicsAdministrator admin = new ClinicsAdministrator();
        admin.setFirstName(a.getFirstName());
        admin.setLastName(a.getLastName());
        admin.setUsername(a.getUsername());
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setAddress(a.getAddress());
        admin.setStatus(status);
        List<Authority> auth =authorityService.findByName(role);
        admin.setAuthorities(auth);
        admin.setEnabled(true);

        return personRepository.save(admin);

    }
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,oldPassword));
        } else {

            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + username + "'");

        Person user = (Person) loadUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setStatus("ACTIVE");
        personRepository.save(user);

    }
    public Person saveDoctor(CreateDoctorDTORequest d){
        Doctor doctor = new Doctor();
        doctor.setFirstName(d.getFirstName());
        doctor.setLastName(d.getLastName());
        doctor.setUsername(d.getUsername());
        doctor.setPassword(passwordEncoder.encode("123"));
        doctor.setAddress(d.getAddress());
        doctor.setClinic(clinicService.findOneById(d.getClinic_id()));
        doctor.setStatus(d.getStatus());
        List<Authority> auth =authorityService.findByName("ROLE_MEDICAL_STAFF");
        doctor.setAuthorities(auth);
        doctor.setEnabled(true);

        return personRepository.save(doctor);

    }

    public List<Patient> findPatients() { return personRepository.findPatients();}
    public Person findOneByUsername(String username) { return personRepository.findOneByUsername(username); }
    public int updatePersonStatus(String status,Long id) {return personRepository.updateUserStatus(status,id);}
    public Person findOneById(Long id){ return personRepository.findOneById(id);}
    public List<Person> findByType(String type) { return personRepository.findByDiscriminatorValue(type);}
    public int updateUser(String firstName,String lastName,String address,long id) { return personRepository.updateUser(firstName,lastName,address,id); }
    public List<Doctor  > findDoctors() { return personRepository.findDoctors();}
    public List<Administrator> findAdmin(String mail) {return personRepository.findAdmin(mail);}


}
