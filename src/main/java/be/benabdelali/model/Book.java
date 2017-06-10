package be.benabdelali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_BOOK")
    private Long idBook;

    @NotNull
    private String nameBook;

    @NotNull
    private float price;

    @NotNull
    private int quantity;

    private String shipping;

    private Status status;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Page> pages = new ArrayList<>();

    @ManyToMany(mappedBy = "listBooks")
    private List<Client> listClients;

    public Book() {
    }

    public Book(String nameBook, float price, int quantity) {
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantity;

    }

    public Book(String nameBook, float price, int quantity, String shipping) {
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantity;
        this.shipping = shipping;
        if(quantity<1){
            this.status = Status.OUT_OF_STOCK;
        }
        else{
            this.status = Status.AVAILAIBLE;
        }
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    @JsonIgnore
    public List<Client> getListClients() {
        return listClients;
    }

    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
