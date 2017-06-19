package be.benabdelali.services;

import be.benabdelali.model.Book;
import be.benabdelali.model.Page;

import java.util.List;

/**
 * Created by hassan on 6/06/2017.
 */
public interface PageService {

    Page create(Long idBook, Page page);

    Long delete(Long idPage);

    Page update(Long idPage, Page page);

    Page getPageByNumber(Book book , int number);

    Page getPageById(Long idPage);

    List<Page> findAll();

    List<Page> listOfTopFiveTitles();

}
