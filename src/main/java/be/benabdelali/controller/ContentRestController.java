package be.benabdelali.controller;

import be.benabdelali.model.Book;
import be.benabdelali.model.Content;
import be.benabdelali.model.Page;
import be.benabdelali.model.Status;
import be.benabdelali.services.BookService;
import be.benabdelali.services.ContentService;
import be.benabdelali.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 8/06/2017.
 */
@RestController
public class ContentRestController {

    @Autowired
    ContentService contentService;

    @Autowired
    PageService pageService;

    @Autowired
    BookService bookService;

    @Autowired
    PageRestController pageController;

    @PostMapping(value = "/admin/book/{idBook}/pages/{numberPage}/create")
    public ResponseEntity createContent(@PathVariable Long idBook, @PathVariable int numberPage, @RequestBody Content content, HttpSession session) {

        /*
        if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Content cont = new Content(content.getText());

        Book book = bookService.getBookById(idBook);
        if(null==book){
            return new ResponseEntity("BOOK NOT FOUND WITH THIS " + idBook,HttpStatus.NOT_FOUND);
        }

        Page page = pageService.getPageByNumber(book,numberPage);
        if(null==page){
            return new ResponseEntity("PAGE NUMBER "+ numberPage  + " IS NOT FOUND IN BOOK " + book.getNameBook() , HttpStatus.NOT_ACCEPTABLE);
        }

        Content contentFound = contentService.create(cont);
        page.getContents().add(contentFound);
        book.getPages().add(page);
        bookService.updateBook(book.getIdBook(),book);

        return new ResponseEntity(" CONTENT ADDED SUCCESSFULY " + contentFound.getIdContent(), HttpStatus.CREATED);
    }


    @GetMapping(value = "/client/book/{idBook}/pages/{idPage}/content")
    public ResponseEntity getContent(@PathVariable Long idBook, @PathVariable Long idPage, HttpSession session) {

        /* if (session.getAttribute("client") == null) {
            return new ResponseEntity("YOU HAVE TO BE CLIENT TO CONSULT PAGES ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        List<Content> contents = contentService.findContentsByPageAndBook(idBook, idPage);
        if (contents != null) {
            return new ResponseEntity(contents, HttpStatus.FOUND);
        }
        return new ResponseEntity("NO CONTENT FOUND FOR THIS URL ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/admin/delete-content/{id}")
    public ResponseEntity deleteContent(@PathVariable Long id, HttpSession session) {

     /*   if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
        }*/
        Long idDeleted = contentService.delete(id);
        if(idDeleted==null){
            return new ResponseEntity("NO CONTENT FOUND FOR ID " + id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("DELETE SUCCESSFULY FOR " + id, HttpStatus.OK);
    }

    @PutMapping(value = "/admin/update-content/{id}")
    public ResponseEntity updateContent(@PathVariable Long id, @RequestBody Content content, HttpSession session) {

     /*   if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Content foundContent = contentService.getById(id);

        Content updated = contentService.update(id, foundContent);

        if (updated == null) {
            return new ResponseEntity("UPDATE FAILED ", HttpStatus.NOT_MODIFIED);
        }

        List<Content> contents = contentService.findAll();

        return new ResponseEntity(contents, HttpStatus.OK);


    }

}
