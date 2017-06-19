package be.benabdelali.controller;

import be.benabdelali.model.Admin;

import be.benabdelali.model.Book;
import be.benabdelali.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class AdminRestControllerTest {

    @Test
    public void testConnection() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        String username = "hassan";
        String password = "hassan";
        ResponseEntity<Admin> response = restTemplate.getForEntity(fooResourceUrl + "login/"+username+"/"+password, Admin.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void testFindById() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity<Admin>  response = restTemplate.getForEntity(fooResourceUrl + "/admin/findById/1", Admin.class);
        Assertions.assertThat(response.getBody().getName()).isEqualTo("hassan");
        Assertions.assertThat(response.getBody().getIdUser()).isEqualTo(1L);
    }

    @Test
    public void testShowUsers() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/show-users", Admin[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testShowBooks() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/show-books", Book[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testUserByType() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        String type = "admin";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/findByType/" + type, Admin[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testUserById() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        int id = 1;
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/findById/" + id, Admin.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateClient() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        Client client = new Client("jihad simac", "jihad", "jihad", "simac street", "jihad@gmail.com");
        try {
            ResponseEntity response = restTemplate.postForEntity(fooResourceUrl + "admin/create-client", client, String.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (final HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateAdmin() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        Admin admin = new Admin("bart","bart","bart");
        try {
            ResponseEntity response = restTemplate.postForEntity(fooResourceUrl + "admin/create-admin", admin , String.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        }
        catch (final HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Test
    public void testBestClient() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/bestClient", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testBestBook() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/bestBook", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testTotalBookSold() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "admin/totalBookSold", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteUser(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        restTemplate.delete(fooResourceUrl+"admin/delete-user/"+7);
        System.out.println("user deleted successfuly ");
    }

    @Test
    public void testUpdateClient(){

        RestTemplate restTemplate = new RestTemplate();
        Client client = new Client("lukas","lukas","lukas","street simac ","lukas@gmail.com");
        String fooResourceUrl = "http://localhost:8080/bookstore/admin/update-client/"+5;
        restTemplate.put(fooResourceUrl,client);
        System.out.println("user deleted successfuly ");

    }

}
