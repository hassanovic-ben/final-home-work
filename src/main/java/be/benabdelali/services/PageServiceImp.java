package be.benabdelali.services;


import be.benabdelali.model.Book;
import be.benabdelali.model.Page;
import be.benabdelali.repositories.BookRepository;
import be.benabdelali.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hassan on 7/06/2017.
 */
@Service
@Transactional
public class PageServiceImp implements PageService{

    @Autowired
    PageRepository pageRepository;

    @Autowired
    BookRepository bookRepository;


    @Override
    public Page create(Long idBook, Page page) {
        Book book = bookRepository.getOne(idBook);
        page.setBook(book);
        book.getPages().add(page);
        return pageRepository.save(page);
    }

    @Override
    public Long delete(Long idPage) {
         pageRepository.delete(idPage);
         return idPage;
    }

    @Override
    public Page update(Long idPage, Page page) {
        Page pageFound = pageRepository.findOne(idPage);
        if (pageFound != null) {
            Page pageUpdated = pageRepository.saveAndFlush(page);
            return pageUpdated;
        }

        return null;
    }

    @Override
    public Page getPageById(Long idPage) {
        return pageRepository.getOne(idPage);
    }

    @Override
    public List<Page> findAll() {
        return pageRepository.findAll();
    }
}
