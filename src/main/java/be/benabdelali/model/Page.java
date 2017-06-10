package be.benabdelali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */
@Entity
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPage;

    private String title;

    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "BOOK_FK")
    private Book book;

    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();

    public Page() {
    }

    public Page(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public Long getIdPage() {
        return idPage;
    }

    public void setIdPage(Long idPage) {
        this.idPage = idPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @JsonIgnore
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
