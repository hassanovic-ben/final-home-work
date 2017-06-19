package be.benabdelali.repositories;

import be.benabdelali.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    @Modifying
    @Query("UPDATE Page p SET p.title = :title, p.number = :number WHERE p.idPage = :idPage")
    int updatePage(@Param("title") String title, @Param("number") int number,
                   @Param("idPage") Long idPage);

/*
    @Query(value = "select p.title,sum(p.numberTitleBought) from Page p group by p.title order by sum(p.numberTitleBought) desc ")
    List<Page> getTopTitles();
*/
}
