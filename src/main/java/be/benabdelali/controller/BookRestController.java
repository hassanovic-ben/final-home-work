package be.benabdelali.controller;

import be.benabdelali.model.Book;
import be.benabdelali.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */

@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/admin/create-book")
    public ResponseEntity addBook(@RequestBody Book book, HttpSession session) {

       /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO CREATE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        Book bookToAdd = bookService.createBook(book);
        if (bookToAdd == null) {
            return new ResponseEntity(bookToAdd, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(bookToAdd, HttpStatus.CREATED);
    }

    @GetMapping(value = "/admin/show-all-books")
    public ResponseEntity getAllBooks(HttpSession session) {

       /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN ", HttpStatus.NOT_FOUND);
        }*/

        List<Book> books = bookService.getBooks();
        if (books.isEmpty()) {
            return new ResponseEntity("NO BOOK FOUND ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/book/availaible-book")
    public ResponseEntity getAvailableBooks(HttpSession session) {

        /*if (session.getAttribute("client") == null) {
            return new ResponseEntity("YOU HAVE TO BE CLIENT ", HttpStatus.NOT_ACCEPTABLE);
        }*/
        List<Book> books = bookService.getAvailaibleBooks();
        if (books.isEmpty()) {
            return new ResponseEntity("NO BOOK AVAILAIBLE ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/book/FindById/{id}")
    public ResponseEntity getBookById(@PathVariable Long id, HttpSession session) {

        Book bookFound = bookService.getBookById(id);
        if (bookFound == null) {
            return new ResponseEntity("BOOK NOT FOUND FOR ID : " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookFound, HttpStatus.FOUND);
    }

    @PutMapping(value = "/admin/book/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Book book, HttpSession session) {

        /* if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO UPDATE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        book = bookService.updateBook(id, book);

        if (null == book) {
            return new ResponseEntity(" NO BOOK FOUND WITH THIS ID : " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("BOOK UPDATED SUCCESSFULY ", HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/book/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, HttpSession session) {

        /*if (session.getAttribute("admin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO DELETE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }*/

        if (null == bookService.deleteBook(id)) {
            return new ResponseEntity("NO BOOK FOUND FOR THIS ID :" + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("BOOK FOR THIS ID : " + id + "IS DELETED", HttpStatus.OK);
    }
}
