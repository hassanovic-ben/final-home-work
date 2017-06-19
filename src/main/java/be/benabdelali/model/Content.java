package be.benabdelali.model;

import be.benabdelali.services.ClientBookServiceImp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idContent;

    private String text;

    private String lastUpdateDate;

    public Content() {
    }

    public Content(String text) {
        this.text = text;
        System.out.println(ClientBookServiceImp.getCurrentTimeStamp()+"****************");
        this.lastUpdateDate = ClientBookServiceImp.getCurrentTimeStamp();
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

}
