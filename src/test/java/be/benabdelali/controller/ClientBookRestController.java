package be.benabdelali.controller;

import be.benabdelali.model.ClientBook;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ClientBookRestController {

    @Test
    public void testShowSales(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookstore/";
        ResponseEntity response = restTemplate.getForEntity(url + "/admin/showSales" , ClientBook[].class);
        Assertions.assertThat(response.getStatusCode().OK);
    }

    @Test
    public void testDeleteBook(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/bookstore/";
        int k = 23;
        restTemplate.delete(fooResourceUrl+"/admin/sale/delete/"+k);
        System.out.println("sale deleted successfuly ");
    }

}
