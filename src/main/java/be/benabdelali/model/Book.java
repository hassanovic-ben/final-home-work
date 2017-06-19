package be.benabdelali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "ID_BOOK")
    private Long idBook;

    @NotNull
    private String nameBook;

    @NotNull
    private float price;

    @NotNull
    private int quantity;

    private Status status;

    private int quantitySold;

    @OneToMany
    @JoinColumn(name="BOOK_FK",referencedColumnName = "ID_BOOK")
    private List<Page> pages = new ArrayList<>();

    public Book() {
    }

    public Book(String nameBook, float price, int quantity) {
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantity;
        if (quantity < 1) {
            this.status = Status.OUT_OF_STOCK;
        } else {
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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

}
