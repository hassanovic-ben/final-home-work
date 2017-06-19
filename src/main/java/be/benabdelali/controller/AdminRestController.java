package be.benabdelali.controller;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Book;
import be.benabdelali.model.Client;
import be.benabdelali.services.AdminService;
import be.benabdelali.services.BookService;
import be.benabdelali.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Instant;
import java.util.*;


@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseEntity getConnection(@PathVariable String username, @PathVariable String password, HttpSession session) {

        Admin userFound = adminService.findByUserNameAndPassword(username, password);
        if (userFound == null) {
            return new ResponseEntity("NO USER FOUND ", HttpStatus.NOT_FOUND);
        } else {
            if (userFound instanceof Client) {
                session.setAttribute("client", userFound);
                Instant instant = Instant.now();
                session.setAttribute("startSession", instant);

                return new ResponseEntity(userFound, HttpStatus.FOUND);
            } else {
                session.setAttribute("admin", userFound);
                Instant instant = Instant.now();
                session.setAttribute("startSession", instant);
                return new ResponseEntity(userFound, HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity logOut(HttpSession session) {

        Instant instant = Instant.now();
        Duration duration = Duration.between((Instant) session.getAttribute("startSession"), instant);
        Long dur = duration.toMillis();

        if (session.getAttribute("client") != null) {
            Client client = ((Client) session.getAttribute("client"));
            Long newTime = client.getTimeLoggin() + dur;
            client.setTimeLoggin(newTime);
            clientService.updateClient(client, client.getIdUser());
        }

        session.invalidate();
        return new ResponseEntity("YOU ARE DISCONNECT", HttpStatus.OK);
    }

    @GetMapping("/admin/show-users")
    public ResponseEntity getUsers(HttpSession session) {

        List<Admin> users = adminService.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/admin/show-books")
    public ResponseEntity getAllBook(HttpSession session) {

        List<Book> books = bookService.getBooks();
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/findById/{id}")
    public ResponseEntity getUserById(@PathVariable Long id, HttpSession session) {

        Admin admin = adminService.getById(id);
        if(admin==null){
            return new ResponseEntity("NO BOOK FOUN FOR THIS ID " + id ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(admin, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/findByType/{type}")
    public ResponseEntity getUserByType(@PathVariable String type, HttpSession session) {

        List<Admin> listByType = adminService.getByType(type);
        if (listByType.isEmpty()) {
            return new ResponseEntity("NO USER FOUND FOR  " + type, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(listByType, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/create-client")
    public ResponseEntity addClient(@RequestBody Client client , HttpSession session ) {

        List<Client> isClientExist = clientService.getClientsByUserName(client.getUserName());

        if (null != isClientExist.get(0)) {
            return new ResponseEntity(" THE USER NAME IS ALREADY EXIST => : ", HttpStatus.NOT_ACCEPTABLE);
        }

        Client clientAdded = clientService.createClient(client);
        return new ResponseEntity(" CLIENT  ADDED SUCCESSFULLY ", HttpStatus.CREATED);

    }

    @PostMapping(value = "/admin/create-admin")
    public ResponseEntity addAdmin(@RequestBody Admin admin, HttpSession session) {

        List<Admin> checkedIfAdminExist = adminService.getAdminByUserName(admin.getUserName());
        if (checkedIfAdminExist.size() != 0) {
            return new ResponseEntity("USER " + admin.getName() + "  ALREADY EXIST ", HttpStatus.NOT_ACCEPTABLE);
        }

        Admin adminAdded = adminService.createAdmin(admin);
        return new ResponseEntity("ADMIN " + adminAdded.getName() + " ADDED SUCCESSFULLY ", HttpStatus.CREATED);

    }

    @PutMapping(value = "/admin/update-client/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client, HttpSession session) {

        Client c = clientService.getById(id);
        if (null == c) {
            return new ResponseEntity(" NO CLIENT FOUND FOR THIS ID : ", HttpStatus.NOT_FOUND);
        }

        Client clientUpdated = clientService.updateClient(client, id);
        if(null!=clientUpdated){
            return new ResponseEntity(" CLIENT UPDATED SUCCESSFULY ", HttpStatus.OK);
        }
        return new ResponseEntity("PROBLEM DURING UPDATING ",  HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value = "/admin/delete-user/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id, HttpSession session) {

        Admin user =  adminService.getById(id);

        if(user==null){
            return new ResponseEntity("USER NOT FOUND ",HttpStatus.NOT_FOUND);
        }

        adminService.deleteAdmin(id);
        return new ResponseEntity("CLIENT WITH ID :" + id + " DELETED SUCCESSFULLY", HttpStatus.OK);
    }

    @GetMapping(value = "/admin/bestClient")
    public ResponseEntity getBestClient() {

        List<Client> sortedListBest = clientService.findBestClient("type");
        Client best = sortedListBest.get(0);
        return new ResponseEntity(best.getName() + " IS THE BEST CLIENT ", HttpStatus.OK);
    }

    @GetMapping(value = "/admin/bestBook")
    public ResponseEntity getBestBook() {

        Book bestBook = bookService.getBestBookBought();
        return new ResponseEntity("The best client is " + bestBook.getNameBook(), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/totalBookSold")
    public ResponseEntity getTotalSold() {

        long total = bookService.getSumTotalBook();
        return new ResponseEntity("Total Book Sold is  " + total, HttpStatus.OK);
    }
}
