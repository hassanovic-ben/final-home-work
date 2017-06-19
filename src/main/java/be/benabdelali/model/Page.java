package be.benabdelali.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PAGE")
    private Long idPage;

    private String title;

    private int number;

    private int numberTitleBought;

    @OneToMany
    @JoinColumn(name = "PAGE_FR",referencedColumnName = "ID_PAGE")
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public int getNumberTitleBought() {
        return numberTitleBought;
    }

    public void setNumberTitleBought(int numberTitleBought) {
        this.numberTitleBought = numberTitleBought;
    }
}
