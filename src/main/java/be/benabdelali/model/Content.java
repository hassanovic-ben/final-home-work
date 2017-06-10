package be.benabdelali.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by hassan on 4/06/2017.
 */
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idContent;

    private String text;

    private LocalDate lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_FK")
    private Page page;

    public Content() {
    }

    public Content(String text, LocalDate lastUpdateDate) {
        this.text = text;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getIdContent() {
        return idContent;
    }

    public void setIdContent(Long idContent) {
        this.idContent = idContent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    @JsonIgnore
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
