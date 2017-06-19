package be.benabdelali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Columns;

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

    private int nbrBookBought;

    private Long timeLoggin = new Long(0);

    public Client() {
    }


    public Client(String name, String userName, String password, String address, String email) {

        super(name, userName, password);
        this.address = address;
        this.email = email;
        this.nbrBookBought = 0;
        this.timeLoggin = 0L;
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

    public int getNbrBookBought() {
        return nbrBookBought;
    }

    public void setNbrBookBought(int nbrBookBought) {
        this.nbrBookBought = nbrBookBought;
    }

    public Long getTimeLoggin() {
        return timeLoggin;
    }

    public void setTimeLoggin(Long timeLoggin) {
        this.timeLoggin = timeLoggin;
    }


}
