package be.benabdelali;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"be.benabdelali.model"})
public class BookStoreApplication {


    public static void main(String[] args) {

        SpringApplication.run(BookStoreApplication.class, args);

    }
}
