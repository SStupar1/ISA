package isa.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import isa.demo.DemoApplication;
import isa.demo.model.Clinic;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")

class ClinicControllerTest {

    private static final String URL_PREFIX = "/api/clinic/";
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    private String accessToken;
    private ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private static int id = 1;

    @Autowired
    MockMvc mockMvc;
    @Test
    void findClinics() throws Exception {
        mockMvc.perform(get(URL_PREFIX+"findClinic?date=&type=")).andExpect(status().isOk());
        String mvcResult2 = mockMvc.perform(get(URL_PREFIX+"findClinic?date=nema&type=nista")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("",mvcResult2);

        mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar")).andExpect(status().isOk());

        mockMvc.perform(get(URL_PREFIX+"findClinic?date=2020-02-01&type=Hirurg")).andExpect(status().isOk());
        //Client testing part
        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=&type=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri,String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic?date=2020-03-02&type=Pedijatar";
        uri = new URI(baseUrl2);
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        result = this.restTemplate.getForEntity(uri,String.class);
        assertEquals(null,result.getBody());
    }

    @Test
    void findDoctors() throws Exception {

        mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-03-02&type=Pedijatar")).
                andExpect(status().isOk());

        mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=Hirurg")).andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get(URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=")).andExpect(status().isOk())
                .andReturn();

        final String baseUrl = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=&date=&type=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri,String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        final String baseUrl2 = "http://localhost:"+randomServerPort+URL_PREFIX+"findClinic/doctors?clinicName=clinic1&date=2020-03-02&type=Pedijatar";
        uri = new URI(baseUrl2);
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        result = this.restTemplate.getForEntity(uri,String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

    }
}