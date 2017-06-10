package be.benabdelali.services;

import be.benabdelali.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hassan on 6/06/2017.
 */
@Service
public interface ContentService {

    Content create(Content content);
    Long delete(Long idContent);
    Content update(Long idContent,Content content);
    Content getById(Long idContent);
    List<Content> findAll();
}
