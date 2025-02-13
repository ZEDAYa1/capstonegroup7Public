package za.ac.cput.controller;/*
 * AgentServiceImplTest.java
 * Author: Sibusiso Dwayi(220226466)
 * Date: 14 June 2023
 * */
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Property;
import za.ac.cput.factory.PropertyFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PropertyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PropertyController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    private Property property;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.property = PropertyFactory.createProperty("123 Main St", 2000.0, "Spacious 2-bedroom apartment");
        this.baseUrl = "http://localhost:" + this.port + "/capstonegroup7/property/";
    }

    @Order(1)
    @Test
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<Property> response = this.restTemplate
                .withBasicAuth("username", "password")
                .postForEntity(url, this.property, Property.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Order(3)
    @Test
    void delete() {
        String url = baseUrl + "delete/" + this.property.getPropertyId();
        this.restTemplate.delete(url);
    }

    @Order(2)
    @Test
    void readId() {
        String url = baseUrl + "read/" + this.property.getPropertyId();
        ResponseEntity<Property> response = this.restTemplate
                .withBasicAuth("username", "password")
                .getForEntity(url, Property.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Order(4)
    @Test
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Property[]> response =
                this.restTemplate
                        .withBasicAuth("username", "password")
                        .getForEntity(url, Property[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(1, response.getBody().length)
        );
    }
}
