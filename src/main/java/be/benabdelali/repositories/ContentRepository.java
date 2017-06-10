package be.benabdelali.repositories;

import be.benabdelali.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hassan on 6/06/2017.
 */
@Repository
public interface ContentRepository extends JpaRepository<Content,Long>{

}
