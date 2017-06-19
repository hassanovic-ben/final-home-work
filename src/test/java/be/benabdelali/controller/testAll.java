package be.benabdelali.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hassan on 17/06/2017.
 */
public class testAll {


    @Test
    public void testAll() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/springrest/customers";

        /*Admin customer = new Customer("mike new");
        ResponseEntity<Customer> response
                = restTemplate.postForEntity(fooResourceUrl, customer,Customer.class);
        System.out.println(response.getBody());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println("customer added...");

        ResponseEntity<String>response2 = restTemplate.getForEntity(fooResourceUrl + "/real", String.class);
        System.out.println(response2.getBody());
        Assertions.assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);

        restTemplate.delete(fooResourceUrl + "/1");
        System.out.println("customer deleted...");

        ResponseEntity<String>response3 = restTemplate.getForEntity(fooResourceUrl + "/real", String.class);
        System.out.println(response3.getBody());
        Assertions.assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        */

    }

}
