package isa.demo.service;

import isa.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void userAccepted(Person p) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getUsername());
        System.out.print(env.getProperty("spring.mail.username"));
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Registracija odobrena");
        mail.setText("Pozdrav " + p.getFirstName() + "," +
                "acconut mozete aktivitari na linku:\n\n" +
                "http://localhost:9090/auth/activateAccount/" + p.getId());
        javaMailSender.send(mail);
    }
    @Async
    public void userDenied(Person p) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getUsername());
        System.out.print(env.getProperty("spring.mail.username"));
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Registracija odbijena");
        mail.setText("Pozdrav " + p.getFirstName() + "," +
                "na zalost ne mozemo da vam odobrimoda koristite aplikaciju");
        javaMailSender.send(mail);
    }
}
