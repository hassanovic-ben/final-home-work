package be.benabdelali.repositories;

import be.benabdelali.model.Book;
import be.benabdelali.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b from Book b where b.status = 'AVAILAIBLE'")
    List<Book> findAvailaibleBook();

    @Modifying
    @Query("UPDATE Book b SET b.nameBook = :nameBook, b.price = :price, b.quantity = :quantity, b.status = :status" +
            ", b.quantitySold = :quantitySold WHERE b.idBook = :idBook")
    int updateBook(@Param("nameBook") String nameBook, @Param("price") float price, @Param("quantity") int quantity,
                   @Param("status") Status status, @Param("quantitySold") int quantitySold, @Param("idBook") Long idBook);

    List<Book> findByNameBook(String nameBook);

    @Query("select sum(b.quantitySold) from Book b")
    long getSumTotalBook();


}
