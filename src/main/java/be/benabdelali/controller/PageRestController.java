package be.benabdelali.controller;


import be.benabdelali.model.Book;
import be.benabdelali.model.Page;
import be.benabdelali.model.Status;
import be.benabdelali.services.BookService;
import be.benabdelali.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hassan on 7/06/2017.
 */

@RestController
public class PageRestController {

    @Autowired
    PageService pageService;

    @Autowired
    BookService bookService;

    @GetMapping(value = "/fill/page")
    public ResponseEntity fillPage(){
        fillPageTable(); // to add some data into the page table
        return new ResponseEntity("WE ADDED SOME DATA INTO THE PAGE TABLE", HttpStatus.OK);
    }


    @GetMapping(value = "/admin/book/{idBook}/create-page")
    public ResponseEntity createPage(@PathVariable Long idBook,@RequestBody Page page){
        pageService.create(idBook,page);
        fillPageTable(); // to add some data into the page table
        return new ResponseEntity("WE ADDED SOME DATA INTO THE PAGE TABLE", HttpStatus.OK);
    }

    @GetMapping(value = "/book/{id}/pages")
    public ResponseEntity showPages(@PathVariable Long id){

        Book book = bookService.getBookById(id);
        if(null==book){
            return new ResponseEntity("NO BOOK FOUND FOT THIS " + id ,HttpStatus.NOT_FOUND);
        }

        List<Page> listPage = book.getPages();
        return new ResponseEntity(listPage,HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/admin/book/{idBook}/update-page/{idPage}")
    public ResponseEntity updatePageByIdBook(@PathVariable Long idBook, @PathVariable Long idPage, @RequestBody Page page, HttpSession session){

        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ADD ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }

        Book book = bookService.getBookById(idBook);
        if(null==book){
            return new ResponseEntity("NO BOOK FOUND FOT THIS " + idBook ,HttpStatus.NOT_FOUND);
        }

        Page pageFound = book.getPages().get(Math.toIntExact(idPage));
        Page pageUpdated = pageService.update(pageFound.getIdPage(),page);

        return new ResponseEntity(pageUpdated,HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/admin/book/{idBook}/delete-page/{idPage}")
    public ResponseEntity deletePageByIdBook(@PathVariable Long idBook, @PathVariable Long idPage, HttpSession session){
        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ADD ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }
        Book book = bookService.getBookById(idBook);
        if(null==book){
            return new ResponseEntity("NO BOOK FOUND FOT THIS " + idBook ,HttpStatus.NOT_FOUND);
        }
        Page pageFound = book.getPages().get(Math.toIntExact(idPage));
        if(null==pageFound){
            return new ResponseEntity("NO PAGE FOUND FOT THIS ID :" + idPage ,HttpStatus.NOT_FOUND);
        }
        Long id = pageService.delete(pageFound.getIdPage());

        return new ResponseEntity("PAGE WITH ID :" + id + " IS DELETED SUCCESSFULLY  ",HttpStatus.NOT_FOUND);

    }

    public void fillPageTable(){

        /*************************** Create a Book Object ******************/

        Book book = new Book();
        book.setNameBook("Captain Majid");
        book.setPrice(12.5f);
        book.setQuantity(10);
        book.setShipping("Come him Self");
        book.setStatus(Status.AVAILAIBLE);
        bookService.createBook(book);

        /************** Create some pages of this book *************************/

        Page page1 = new Page("Introduction",1);
        page1.setBook(book);
        Page page2 = new Page("Introduction",2);
        page2.setBook(book);
        Page page3 = new Page("Introduction",3);
        page3.setBook(book);
        Page page4 = new Page("Introduction",4);
        page4.setBook(book);
        Page page5 = new Page("Introduction",5);
        page5.setBook(book);
        Page page6 = new Page("Introduction",6);
        page6.setBook(book);


        /*******************  Insert the pages into the data base **********************/

        pageService.create(1L, page1);
        pageService.create(1L, page2);
        pageService.create(1L, page3);
        pageService.create(1L, page4);
        pageService.create(1L, page5);
        pageService.create(1L, page6);



        /********************* Insert the Book into the data base with his pages **************************/

        bookService.updateBook(book.getIdBook(),book);

    }
}
