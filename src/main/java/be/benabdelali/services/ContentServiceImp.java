package be.benabdelali.services;

import be.benabdelali.model.Content;
import be.benabdelali.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hassan on 6/06/2017.
 */
@Service
public class ContentServiceImp implements ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Override
    public Content create(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public Long delete(Long idContent) {
        contentRepository.delete(idContent);
        return idContent;
    }

    @Override
    public Content update(Long idContent, Content content) {
        Content contentFound = contentRepository.findOne(idContent);
        if (contentFound != null) {
            Content contentUpdated = contentRepository.saveAndFlush(content);
            return contentUpdated;
        }
        return null;
    }

    @Override
    public Content getById(Long idContent) {
        return contentRepository.findOne(idContent);
    }

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }
}
