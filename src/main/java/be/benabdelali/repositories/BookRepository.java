package be.benabdelali.repositories;

import be.benabdelali.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b from Book b where b.status = 'AVAILAIBLE'")
    List<Book> findAvailaibleBook();

    List<Book> findByNameBook(String nameBook);
}
