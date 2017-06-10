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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/fill/content")
    public ResponseEntity fillContent() {

        Book book = new Book("ANTIGONE",14f,10,"COME HIM SELF");
        Page page = new Page("INTRODUCTION",101);
        Content content = new Content("ANTIGONE IS A NICE STORY",LocalDate.now());
        book.getPages().add(page);
        page.setBook(book);
        page.getContents().add(content);
        content.setPage(page);


        /*************** AFFECTATION ******************/

        bookService.createBook(book);

        return new ResponseEntity("WE ADDED SOME DATA INTO THE DATA BASE",HttpStatus.OK);

    }


}
