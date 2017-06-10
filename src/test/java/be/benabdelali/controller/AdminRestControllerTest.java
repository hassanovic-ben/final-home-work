package be.benabdelali.controller;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hassan on 10/06/2017.
 */

public class AdminRestControllerTest {

    final String fooResourceUrl = "http://localhost:8080/bookstore/";
    RestTemplate restTemplate = new RestTemplate();
    @Test
    public void testAddAdmin(){



        Admin admin = new Admin("aaaa","aaaa","aaaa");
        admin.setIdUser(1L);
        admin.setType("admin");
        try {
            ResponseEntity<Admin> response;
            response = restTemplate.postForEntity(fooResourceUrl, admin ,  Admin.class);
            System.out.println(response.getStatusCode()+"*********************");
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Client added...");
        System.out.println("any");
    }


}
