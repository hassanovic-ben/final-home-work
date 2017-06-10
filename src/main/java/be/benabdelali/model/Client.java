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
@DiscriminatorValue("client")
public class Client extends Admin {

    @NotNull
    private String address;
    @NotNull
    private String email;

    @ManyToMany
    @JoinTable(
            name="CLIENT_BOOK",
            joinColumns= {
                    @JoinColumn(name="ID_CLIENT", referencedColumnName="ID_USER")},
            inverseJoinColumns={
                    @JoinColumn(name="ID_BOOK", referencedColumnName="ID_BOOK")})
    private List<Book> listBooks = new ArrayList<>();

    public Client() {
    }



    public Client(String name, String userName, String password, String address, String email) {
        super(name, userName, password);
        this.address = address;
        this.email = email;
    }

    public Client(String name, String userName, String password, String address, String email, List list) {
        super(name, userName, password);
        this.address = address;
        this.email = email;
        this.listBooks=list;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

}
