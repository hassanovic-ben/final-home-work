package be.benabdelali.controller;

import be.benabdelali.model.Page;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class PageRestControllerTest {

    @Test
    public void testCreatePage(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        Page page = new Page("CONCLUSION",9);
        ResponseEntity response = restTemplate.postForEntity(fooResourceUrl + "admin/book/1/create", page, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


}
