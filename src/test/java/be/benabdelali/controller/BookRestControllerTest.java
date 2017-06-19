package be.benabdelali.controller;

import be.benabdelali.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class BookRestControllerTest {


    @Test
    public void testCreateBook() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        Book book = new Book("ANTIGONE",45,10);
        ResponseEntity response = restTemplate.postForEntity(fooResourceUrl + "admin/create-book", book, Book.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);

    }

    @Test
    public void testGetAllBookTest(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl+ "admin/show-all-books",Book[].class);
        Assertions.assertThat(response.getStatusCode().OK);

    }

    @Test
    public void testGetAvailaibleBookTest(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl+ "book/availaible-book",Book[].class);
        Assertions.assertThat(response.getStatusCode().OK);
    }

    @Test
    public void testFindById() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        int id = 1;
        ResponseEntity response = restTemplate.getForEntity(fooResourceUrl + "book/FindById/" + id, Book.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);

    }

    @Test
    public void testDeleteBook(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        int k = 10;
        restTemplate.delete(fooResourceUrl+"admin/book/delete/"+k);
        System.out.println("book deleted successfuly ");
    }

}
