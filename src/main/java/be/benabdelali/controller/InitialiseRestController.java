package be.benabdelali.controller;

import be.benabdelali.model.Book;
import be.benabdelali.model.Client;
import be.benabdelali.model.Page;
import be.benabdelali.services.BookService;
import be.benabdelali.services.ClientBookService;
import be.benabdelali.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;



@RestController
public class InitialiseRestController {

    @Autowired
    private ClientBookService saleService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/admin/initialise")
    public ResponseEntity initialiseSaleTable(HttpSession session){

        /*
            if(session.getAttribute("admin")==null){
                return new ResponseEntity("YOU HAVE TO BE ADMIN ", HttpStatus.NOT_ACCEPTABLE);
            }
        */

    // saleService.delete((long) 1);

    List<Book> books = bookService.getBooks();
    for(Book b : books){
        b.setQuantitySold(0);
        List<Page> pages = b.getPages();
        for(Page p:pages){
            p.setNumberTitleBought(0);
        }
        bookService.updateBook(b.getIdBook(),b);
    }

    List<Client> clients = clientService.getClients();
    for(Client c : clients){
        c.setNbrBookBought(0);
        clientService.updateClient(c,c.getIdUser());
    }

    return new ResponseEntity("ALL THE INFO IS INITIALISED SUCCESSFULY",HttpStatus.OK);

    }

}
