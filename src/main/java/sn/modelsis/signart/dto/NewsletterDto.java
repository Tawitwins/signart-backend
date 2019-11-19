package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class NewsletterDto {
    private Integer id;
    private String contenu;
    private boolean envoye;
    private Date dateEnvoi;
    private Integer idTypeNewsletter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public boolean isEnvoye() {
        return envoye;
    }

    public void setEnvoye(boolean envoye) {
        this.envoye = envoye;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Integer getIdTypeNewsletter() {
        return idTypeNewsletter;
    }

    public void setIdTypeNewsletter(Integer idTypeNewsletter) {
        this.idTypeNewsletter = idTypeNewsletter;
    }
    
}
