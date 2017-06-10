package be.benabdelali.controller;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Book;
import be.benabdelali.model.Client;
import be.benabdelali.services.AdminService;
import be.benabdelali.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 3/06/2017.
 */

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminservice;

    @Autowired
    private ClientService clientService;


    @GetMapping("/admin/show-users")
    public ResponseEntity getUsers(HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }
        List users = adminservice.findAll();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @PostMapping(value = "/add/boss")
    public ResponseEntity addBoss(@RequestBody Admin admin) {
        List<Admin> findUser = adminservice.findAll();
        if(0!=findUser.size()){
            return new ResponseEntity("THE BOSS IS ALREADY ADDED PLEASE SIGN IN",HttpStatus.NOT_ACCEPTABLE);
        }
        Admin boss = adminservice.createAdmin(admin);
        return new ResponseEntity("THE BOSS : ADDED SUCCESSFULLY ",HttpStatus.OK);
    }

    @GetMapping(value = "/admin/fill-users")
    public ResponseEntity fillDataTableBook(HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Admin table is filled with some elements ",HttpStatus.OK);
    }


    @GetMapping(value = "/admin/findById/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id,HttpSession session) {

        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }

        Admin admin = adminservice.getById(id);
        if (admin == null) {
            return new ResponseEntity("NO ADMIN FOUND FOR THIS " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(admin, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/findByType/{type}")
    public ResponseEntity getUserByType(@PathVariable String type,HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE CONNECTED AS AN ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }
        List<Admin> listByType = adminservice.getByType(type);
        if (listByType.isEmpty()) {
            return new ResponseEntity("NO USER FOUND FOR THIS " + type , HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity( listByType , HttpStatus.OK);
        }
    }

    @GetMapping(value = "/totalSold")
    public ResponseEntity getTop5Client(HttpSession session){
        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ACCESS",HttpStatus.NOT_ACCEPTABLE);
        }
        int total = adminservice.totalBookSold();
        return new ResponseEntity("TOTAL BOOK SOLD IS " + total ,HttpStatus.OK);

    }

    @GetMapping(value = "/bestSeller")
    public ResponseEntity getBestSeller(HttpSession session){
        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ACCESS",HttpStatus.NOT_ACCEPTABLE);
        }
        Client client = adminservice.bestSellerForTheBook().get(0);
        return new ResponseEntity("THE BEST SELLER IS " + client.getName() ,HttpStatus.OK);

    }

    @GetMapping(value = "/connect/{username}/{password}")
    public ResponseEntity getConnexion(@PathVariable String username,@PathVariable String password, HttpSession session) {

        Admin userFound = adminservice.findByUserNameAndPassword(username, password);
        if (userFound == null) {
            return new ResponseEntity("NO USER FOUND ", HttpStatus.NOT_FOUND);
        }
        else{

            if (userFound instanceof Client) {
                session.setAttribute("connectedClient", userFound);
                return new ResponseEntity("USER " + userFound.getName() + " CONNECTED AS CLIENT ", HttpStatus.OK);
            } else {
                session.setAttribute("connectedAdmin", userFound);
                return new ResponseEntity("USER " + userFound.getName() + " CONNECTED AS ADMIN ", HttpStatus.OK);
            }
        }
    }

    @PostMapping(value = "/admin/create-client")
    public ResponseEntity addClient(@RequestBody Client client,HttpSession session) {

        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ADD CLIENT",HttpStatus.NOT_ACCEPTABLE);
        }

        if (client != null) {
           List<Client>  checkClient = clientService.getClientsByUserName(client.getUserName());
                if(0!=checkClient.size()){
                    return new ResponseEntity("THE USER NAME IS ALREADY EXIST :",HttpStatus.NOT_ACCEPTABLE);
                }
              Client clientAdded = clientService.createClient(client);
            return new ResponseEntity("CLIENT " + clientAdded.getName() + " ADDED SUCCESSFULLY ", HttpStatus.OK);
        }
        return new ResponseEntity("ERROR CLIENT NOT FOUND TO ADD", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/admin/create-admin")
    public ResponseEntity addAdmin(@RequestBody  Admin admin,HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO ADD ADMIN",HttpStatus.NOT_ACCEPTABLE);
        }
        if (admin != null) {
            List<Admin> checkedIfAdminExist =  adminservice.getAdminByUserName(admin.getUserName());
            if(checkedIfAdminExist.size()!=0){
                return new ResponseEntity("USER " + admin.getName() + "  ALREADY EXIST ", HttpStatus.NOT_ACCEPTABLE);
            }

            Admin adminAdded = adminservice.createAdmin(admin);

            return new ResponseEntity("ADMIN " + adminAdded.getName() + " ADDED SUCCESSFULLY ", HttpStatus.OK);
        }
        return new ResponseEntity("ERROR ADMIN NOT FOUND TO ADD", HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/admin/update-client/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Client client, HttpSession session) {

        if(session.getAttribute("connectedAdmin")==null){
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO UPDATE A BOOK ",HttpStatus.NOT_ACCEPTABLE);
        }
        Client c = clientService.getById(id);
        if (null == c) {
            return new ResponseEntity(" NO CLIENT FOUND FOR THIS ID : " + id, HttpStatus.NOT_FOUND);
        }

        Client clientUpdated = clientService.updateClient(client, id);

        if(clientUpdated==null){
            return new ResponseEntity("THE CLIENT EXIST IN DATA BASE ",HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(clientUpdated, HttpStatus.OK);
    }


    @DeleteMapping(value = "/admin/delete-client/{id}")
    public ResponseEntity deleteClient(@PathVariable("id") Long id,HttpSession session) {
        if(session.getAttribute("connectedAdmin")==null) {
            return new ResponseEntity("YOU HAVE TO BE ADMIN TO DELETE CLIENT",HttpStatus.NOT_ACCEPTABLE);
        }

        Client clientToBeDeleted = (Client)adminservice.getById(id);
        clientService.deleteClient(clientToBeDeleted);
        if (!clientService.getClients().contains(clientToBeDeleted)) {
            return new ResponseEntity("CLIENT WITH ID :" + id + " DELETED SUCCESSFULLY", HttpStatus.OK);
        }
        return new ResponseEntity("CLIENT WITH ID :" + id + " CAN NOT BE DELETED", HttpStatus.NOT_ACCEPTABLE);
    }



}
