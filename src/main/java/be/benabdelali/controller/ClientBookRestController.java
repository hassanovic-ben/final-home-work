package be.benabdelali.controller;

import be.benabdelali.model.ClientBook;
import be.benabdelali.services.ClientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hassan on 14/06/2017.
 */
@RestController
public class ClientBookRestController {

    @Autowired
    ClientBookService clientBookService;

    @GetMapping(value = "/admin/showSales")
    public ResponseEntity getSales(HttpSession session) {

        /*if(session.getAttribute("admin")!=null){
            return new ResponseEntity("YOU HAVE TO BE ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }*/

        List<ClientBook> sales = clientBookService.listClientBook();

        if (sales.isEmpty()) {
            return new ResponseEntity("NO SALE FOUND ", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(sales, HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/sale/delete/{id}")
    public ResponseEntity deleteSale(HttpSession session, @PathVariable Long id){

        Long idDelete = clientBookService.delete(id);
        if(null==idDelete){
            return new ResponseEntity("NO SALE FOUND FOR " + id ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("SALE DELETED SUCCESSFULY",HttpStatus.OK);
    }

}
