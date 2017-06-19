package be.benabdelali.repositories;

import be.benabdelali.model.ClientBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientBookRepository extends JpaRepository<ClientBook, Long> {


}
