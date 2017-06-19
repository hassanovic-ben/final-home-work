package be.benabdelali.controller;

import be.benabdelali.model.*;
import be.benabdelali.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ClientRestController {

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientBookService clientBookService;

    @GetMapping(value = "/client/buyBook")
    public ResponseEntity buyBook(HttpSession session) {

        /* Client client = (Client) session.getAttribute("client");

        if (client == null) {
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS A CLIENT TO BUY A BOOK", HttpStatus.NOT_ACCEPTABLE);
        }
        */
        List<Book> allAvailaibleBook = bookService.getAvailaibleBooks();
        return new ResponseEntity(allAvailaibleBook, HttpStatus.OK);

    }

    @GetMapping(value = "/client/buyBook/{idBook}/{shipping}/{address}")
    public ResponseEntity doBuyBook(@PathVariable Long idBook,@PathVariable String address, @PathVariable String shipping, HttpSession session) {

        Client client = (Client) session.getAttribute("client");

        Book book = bookService.getBookById(idBook);

        if (null == book) {
            return new ResponseEntity("NO BOOK FOUND ", HttpStatus.NOT_FOUND);
        }

        Shipping shippingMethod = Shipping.valueOf(shipping);
        System.out.println(address);

        if(!client.getAddress().equals(address)){
            return new ResponseEntity("ADDRESS IS NOT CORRECT ",HttpStatus.NOT_ACCEPTABLE);
        }

        generateBuying(book, client, shippingMethod);
        countTitleBought(book);
        return new ResponseEntity("BOOK ==> " + book.getNameBook() + " BOUGHT BY ==> " + client.getName(), HttpStatus.OK);

    }

    private void generateBuying(Book book, Client connectedClient, Shipping shipping) {

        book.setQuantity(book.getQuantity() - 1);

        if (book.getQuantity() == 0) {
            book.setStatus(Status.OUT_OF_STOCK);
        }

        book.setQuantitySold(book.getQuantitySold() + 1);
        ClientBook clientBook = new ClientBook();
        clientBook.setBook(book);
        connectedClient.setNbrBookBought(connectedClient.getNbrBookBought() + 1);
        Client client = clientService.updateClient(connectedClient, connectedClient.getIdUser());
        clientBook.setClient(client);
        clientBook.setShipping(shipping);
        String dateNow = ClientBookServiceImp.getCurrentTimeStamp();
        clientBook.setRegisteredDate(dateNow);
        clientBookService.createClientBook(clientBook);
    }

    private void countTitleBought(Book book){
        List<Page> list = book.getPages();
        int titleCount = 0 ;
        for(Page p : list){
             p.setNumberTitleBought(p.getNumberTitleBought() + 1);
        }
    }
}
