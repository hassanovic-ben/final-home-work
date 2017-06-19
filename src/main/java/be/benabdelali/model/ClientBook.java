package be.benabdelali.model;

import javax.persistence.*;


@Entity
public class ClientBook {
    @Id
    @GeneratedValue
    private Long idClientBook;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_USER")
    private Client client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_BOOK")
    private Book book;

    // additional fields
    private Shipping shipping;
    private String registeredDate;

    public ClientBook() {
    }

    public ClientBook(Long idClientBook, Client client, Book book, Shipping shipping, String registeredDate) {

        this.idClientBook = idClientBook;
        this.client = client;
        this.book = book;
        this.shipping = shipping;
        this.registeredDate = registeredDate;
    }

    public Long getIdClientBook() {
        return idClientBook;
    }

    public void setIdClientBook(Long idClientBook) {
        this.idClientBook = idClientBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

}
