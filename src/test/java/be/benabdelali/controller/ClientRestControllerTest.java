package be.benabdelali.controller;

import be.benabdelali.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ClientRestControllerTest {

    @Test
    public void testBuyBook() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/client/buyBook";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl , Book[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
