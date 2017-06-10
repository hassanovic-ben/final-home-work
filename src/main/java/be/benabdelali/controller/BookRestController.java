package be.benabdelali.controller;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Book;
import be.benabdelali.model.Status;
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

    @GetMapping(value = "/admin/fill-book")
    public ResponseEntity fillDataTableBook(HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS ADMIN TO FILL A TABLE ",HttpStatus.NOT_ACCEPTABLE);
        }
        fillBookTable();
        return new ResponseEntity("Book table is filled with some elements ", HttpStatus.OK);
    }

    @PostMapping(value = "/admin/create-book")
    public ResponseEntity addBook(@RequestBody Book book, HttpSession session) {

        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO CREATE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }
        Book bookToAdd = bookService.createBook(book);
        if (bookToAdd == null) {
            return new ResponseEntity("ERROR DURING ADDING", HttpStatus.OK);
        }

        return new ResponseEntity("BOOK ADDED SUCCESSFULLY ", HttpStatus.OK);
    }


    @GetMapping(value = "/admin/show-all-books")
    public ResponseEntity getAllBooks(HttpSession session) {
        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN ", HttpStatus.NOT_FOUND);
        }
        List<Book> books = bookService.getBooks();
        if (books.isEmpty()) {
            return new ResponseEntity("NO BOOK FOUND ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/show-availaible-books")
    public ResponseEntity getAvailableBooks(HttpSession session) {
        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN ", HttpStatus.NOT_FOUND);
        }
        List<Book> books = bookService.getAvailaibleBooks();
        if (books.isEmpty()) {
            return new ResponseEntity("NO BOOK AVAILAIBLE ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity getBookById(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO FIND A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }
        Book bookFound = bookService.getBookById(id);
        if (bookFound == null) {
            return new ResponseEntity("BOOK NOT FOUND FOR THIS " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookFound, HttpStatus.OK);
    }

    @GetMapping(value = "/book/{name}")
    public ResponseEntity getBookByName(@PathVariable String nameBook, HttpSession session) {
        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO FIND A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }
        Book bookFound = bookService.getBookByName(nameBook);
        if (bookFound == null) {
            return new ResponseEntity("BOOK NOT FOUND FOR THIS NAME " + nameBook, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookFound, HttpStatus.OK);
    }

    @PutMapping(value = "/book/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Book book, HttpSession session) {

        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO UPDATE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }

        book = bookService.updateBook(id, book);

        if (null == book) {
            return new ResponseEntity(" NO BOOK FOUND WITH THIS ID : " + id, HttpStatus.NOT_FOUND);
        }
        List<Book> listAfterUpdate = bookService.getBooks();
        return new ResponseEntity(listAfterUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/book/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("connectedAdmin") == null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO DELETE A BOOK ", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == bookService.deleteBook(id)) {
            return new ResponseEntity("NO BOOK FOUND FOR THIS ID :" + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("BOOK FOR THIS ID : " + id + "IS DELETED", HttpStatus.OK);
    }

    public void fillBookTable() {
        Book book1 = new Book("Nineteen Eighty Four by George Orwell", 45.2f, 25, "COME HIM SELF");
        Book book2 = new Book("Don Quixote by Miguel de Cervantes", 14.9f, 1, "BY POST");
        Book book3 = new Book("In Search of Lost Time by Marcel Proust", 34.5f, 45, "BY POST");
        Book book4 = new Book("The Sound and the Fury by William Faulkner", 74.3f, 21, "COME HIM SELF");
        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);
    }


}
