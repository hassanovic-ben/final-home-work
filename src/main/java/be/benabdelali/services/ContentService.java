package be.benabdelali.services;

import be.benabdelali.model.Content;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ContentService {

    Content create(Content content);

    Content update(Long idContent, Content content);

    Content getById(Long idContent);

    List<Content> findAll();

    List<Content> findContentsByPageAndBook(Long idBook, Long idPage);

    Long delete(Long idContent);

}
