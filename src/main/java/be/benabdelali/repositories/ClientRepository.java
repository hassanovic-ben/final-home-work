package be.benabdelali.repositories;

import be.benabdelali.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.userName = :userName, c.password = :password, c.address = :address, c.email = :email WHERE c.idUser = :idClient")
    int updateClient(@Param("name") String name,@Param("userName") String userName,@Param("password") String password,
                     @Param("address") String address,@Param("email") String email,@Param("idClient") Long idClient) ;

    List<Client> findByUserName(String userName);
}
