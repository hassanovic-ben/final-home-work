package be.benabdelali;


import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;
import be.benabdelali.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"be.benabdelali.model"})
public class BookStoreApplication {


    public static void main(String[] args) {

        SpringApplication.run(BookStoreApplication.class, args);

    }
}
