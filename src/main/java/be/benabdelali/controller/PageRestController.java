package be.benabdelali.controller;

import be.benabdelali.model.Book;
import be.benabdelali.model.Page;
import be.benabdelali.services.BookService;
import be.benabdelali.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class PageRestController {

    @Autowired
    private PageService pageService;

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/admin/book/{idBook}/create")
    public ResponseEntity createPage(@PathVariable Long idBook, @RequestBody Page page, HttpSession session) {

        /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN  ", HttpStatus.NOT_ACCEPTABLE);
        }*/
        Book book = bookService.getBookById(idBook);
        if(null==book){
            return new ResponseEntity("NO BOOK FOUND FOR "+idBook,HttpStatus.NOT_FOUND);
        }

        Page pageCreated = pageService.create(idBook, page);

        if (null == pageCreated) {
            return new ResponseEntity("PAGE NUMBER ALREADY EXIST ", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(" PAGE ADDED SUCCESSFULY ", HttpStatus.CREATED);
    }

    @GetMapping(value = "/client/book/{id}/pages")
    public ResponseEntity showPagesByBook(@PathVariable Long id, HttpSession session) {

      /*  if (session.getAttribute("client") == null) {
            return new ResponseEntity("YOU HAVE TO BE CLIENT TO CONSULT PAGES ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Book book = bookService.getBookById(id);
        if (null == book) {
            return new ResponseEntity("NO BOOK FOUND FOR THIS " + id, HttpStatus.NOT_FOUND);
        }

        List<Page> listPage = book.getPages();
        return new ResponseEntity(listPage, HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/admin/pages")
    public ResponseEntity showAllPages(HttpSession session) {

        /* if (session.getAttribute("client") == null) {
            return new ResponseEntity("YOU HAVE TO BE CLIENT TO CONSULT PAGES ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        List<Page> listPage = pageService.findAll();
        return new ResponseEntity(listPage, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/admin/update-page/{id}")
    public ResponseEntity updatePageByIdBook(@PathVariable Long id, @RequestBody Page page, HttpSession session) {

        /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Page p = pageService.getPageById(id);

        if (null == p) {
            return new ResponseEntity("NO PAGE FOUND", HttpStatus.NOT_FOUND);
        }

        Page pageUpdated = pageService.update(id, page);
        return new ResponseEntity("PAGE UPDATED SUCCESSFULY ", HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/delete-page/{idPage}")
    public ResponseEntity deletePageByIdBook(@PathVariable Long idPage, HttpSession session) {

        /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Page page = pageService.getPageById(idPage);

        if (null == page) {
            return new ResponseEntity("NO PAGE FOUND FOR THIS " + idPage, HttpStatus.NOT_FOUND);
        }

        pageService.delete(idPage);
        return new ResponseEntity("PAGE WITH ID :" + idPage + " IS DELETED SUCCESSFULLY  ", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/topFiveTitles")
    public ResponseEntity getTopTitles(){

        List<Page> pages = pageService.listOfTopFiveTitles();

        if(pages.isEmpty()){
            return new ResponseEntity("LIST IS EMPTY ",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pages,HttpStatus.OK);
    }
}
