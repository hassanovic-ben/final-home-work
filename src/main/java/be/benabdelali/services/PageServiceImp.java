package be.benabdelali.services;

import be.benabdelali.model.Book;
import be.benabdelali.model.Page;
import be.benabdelali.repositories.BookRepository;
import be.benabdelali.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
public class PageServiceImp implements PageService {

    @Autowired
    EntityManager em;


    @Override
    public Page getPageByNumber(Book book, int number) {
        List<Page> pages = book.getPages();
        for(Page p: pages ){
            if(p.getNumber() == number){
                return p;
            }
        }
        return null;
    }

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page create(Long idBook, Page page) {
        Book book = bookRepository.getOne(idBook);
        List<Page> pages = book.getPages();
        for (Page p : pages) { // test if number page exist
            if (p.getNumber() == page.getNumber()) {
                return null;
            }
        }

        pages.add(page);
        return pageRepository.save(page);
    }

    @Override
    public Long delete(Long idPage) {
        Page foundPage = getPageById(idPage);
        if (foundPage != null) {
            pageRepository.delete(idPage);
            return idPage;
        }
        return null;
    }

    @Override
    public Page update(Long idPage, Page page) {

        int var = pageRepository.updatePage(page.getTitle(), page.getNumber(), idPage);

        if (var != 0) {
            try {
                return pageRepository.findOne(idPage);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public Page getPageById(Long idPage) {
        return pageRepository.findOne(idPage);
    }

    @Override
    public List<Page> findAll() {
        return pageRepository.findAll();
    }

    @Override
    public List<Page> listOfTopFiveTitles() {

       Query query = em.createQuery("select p.title,sum(p.numberTitleBought) from Page p group by p.title order by sum(p.numberTitleBought) desc ");
       List<Page> list = query.setMaxResults(2).getResultList();
       return list;
       /*

            List<Page> list = pageRepository.getTopTitles();
            for(int i = 0; i<list.size() ; i++ ){
                if(i==5){
                    break;
                }
            }
            return list;
             */
        }

}
