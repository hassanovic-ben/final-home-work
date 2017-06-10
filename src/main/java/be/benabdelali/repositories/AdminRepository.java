package be.benabdelali.repositories;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hassan on 3/06/2017.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT SIZE(c.listBooks) from  Client c ")
    int totalBookSeller();

    @Query("SELECT c FROM Client c WHERE c.listBooks.size = (SELECT max(c.listBooks.size) FROM Client c)")
    List<Client> bestSellerForTheBook();

    List<Admin> findByUserNameAndPassword(String userName, String password);

    List<Admin> findByUserName(String userName);


    List<Admin> findByType(String type);
}
