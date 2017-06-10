package be.benabdelali.controller;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Book;
import be.benabdelali.model.Client;
import be.benabdelali.model.Status;
import be.benabdelali.services.AdminService;
import be.benabdelali.services.BookService;
import be.benabdelali.services.ClientService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hassan on 5/06/2017.
 */
@Controller
public class ClientRestController {

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/connected/buyBook/{idBook}")
    public ResponseEntity doBuyBook(@PathVariable Long idBook, HttpSession session) {
        Book book = bookService.getBookById(idBook);
        Client client = (Client) session.getAttribute("connectedClient");
        generateBuying(book, (Client) client);
        return new ResponseEntity("BOOK ==> " + book.getNameBook() + " BOUGHT BY ==> " + client.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/connected/buyBook")
    public ResponseEntity buyBook(HttpSession session) {
        Client client = (Client)session.getAttribute("connectedClient");
        if(client==null){
           return new ResponseEntity("YOU HAVE TO BE CONNECTED AS A CLIENT TO BUY A BOOK",HttpStatus.NOT_ACCEPTABLE);
        }
            if(client.getAddress()!=client.getAddress()){
                return new ResponseEntity( "THE ADDRESS IS NOT CORRECT PLEASE CHECK THE ADDRESS" , HttpStatus.OK);
            }
            List<Book> allAvailaibleBook = bookService.getAvailaibleBooks();
            return new ResponseEntity( allAvailaibleBook , HttpStatus.OK);
    }

    @GetMapping(value = "/booksByClient/{id}")
    public ResponseEntity getBookByClient(@PathVariable Long id) {
        Client client = (Client) adminService.getById(id);
        if(client==null){
            return new ResponseEntity("NO CLIENT FOUND FOR "+ id,HttpStatus.NOT_FOUND);
        }
        List<Book> books = client.getListBooks();
        return new ResponseEntity(books, HttpStatus.OK);
    }

    private void generateBuying(Book book, Client connectedClient) {
        book.setShipping("come himself");
        book.getListClients().add(connectedClient);
        connectedClient.getListBooks().add(book);
        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0){
            book.setStatus(Status.OUT_OF_STOCK);
        }
        clientService.updateClient(connectedClient,connectedClient.getIdUser());
        bookService.updateBook(book.getIdBook(),book);
    }
}
