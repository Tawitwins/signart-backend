package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class MarquageOeuvreDto {

    private int idOeuvre;
    private int idClient;
    private int idTypeMarquage;
    private String codeTypeMarquage;
    private Date dateMarquage;

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTypeMarquage() {
        return idTypeMarquage;
    }

    public String getCodeTypeMarquage() {
        return codeTypeMarquage;
    }

    public void setCodeTypeMarquage(String codeTypeMarquage) {
        this.codeTypeMarquage = codeTypeMarquage;
    }

    public void setIdTypeMarquage(int idTypeMarquage) {
        this.idTypeMarquage = idTypeMarquage;
    }

    public Date getDateMarquage() {
        return dateMarquage;
    }

    public void setDateMarquage(Date dateMarquage) {
        this.dateMarquage = dateMarquage;
    }
}
