package be.benabdelali.services;

import be.benabdelali.model.Book;
import be.benabdelali.model.Content;
import be.benabdelali.model.Page;
import be.benabdelali.repositories.BookRepository;
import be.benabdelali.repositories.ContentRepository;
import be.benabdelali.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImp implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Content create(Content content) {

        return contentRepository.save(content);
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

    @Override
    public List<Content> findContentsByPageAndBook(Long idBook, Long idPage) {
        Book book = bookRepository.findOne(idBook);
        if (null == book) {
            return null;
        }
        List<Page> listPage = book.getPages();

        for (Page p : listPage) {
            if (p.getIdPage() == idPage) {
                List<Content> listContents = p.getContents();
                if (listContents.isEmpty()) {
                    return null;
                }
                return listContents;
            }
        }
        return null;
    }

    @Override
    public Long delete(Long idContent) {
        Content content = contentRepository.findOne(idContent);
        if(null!=content){
            contentRepository.delete(idContent);
            return idContent;
        }
        return null;

    }


}
