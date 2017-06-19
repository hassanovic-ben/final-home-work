package be.benabdelali.repositories;

import be.benabdelali.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.userName = :userName, c.password = :password, c.timeLoggin = :timeLoggin, c.address = :address, c.email = :email, c.nbrBookBought = :nbrBookBought WHERE c.idUser = :idClient")
    int updateClient(@Param("name") String name, @Param("userName") String userName, @Param("password") String password, @Param("timeLoggin") Long timeLoggin,
                     @Param("address") String address, @Param("email") String email, @Param("nbrBookBought") int nbrBookBought, @Param("idClient") Long idClient);

    List<Client> findByUserName(String userName);

    List<Client> findByTypeOrderByNbrBookBoughtDesc(String type);
}
