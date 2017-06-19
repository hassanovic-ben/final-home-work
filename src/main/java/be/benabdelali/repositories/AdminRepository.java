package be.benabdelali.repositories;

import be.benabdelali.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findByUserNameAndPassword(String userName, String password);

    List<Admin> findByUserName(String userName);

    List<Admin> findByType(String type);


}
