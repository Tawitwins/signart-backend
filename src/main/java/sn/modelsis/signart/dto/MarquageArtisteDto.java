package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class MarquageArtisteDto {

    private int idArtiste;
    private int idClient;
    private int idTypeMarquage;
    private String codeTypeMarquage;
    private Date dateMarquage;

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
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

    public void setIdTypeMarquage(int idTypeMarquage) {
        this.idTypeMarquage = idTypeMarquage;
    }

    public String getCodeTypeMarquage() {
        return codeTypeMarquage;
    }

    public void setCodeTypeMarquage(String codeTypeMarquage) {
        this.codeTypeMarquage = codeTypeMarquage;
    }

    public Date getDateMarquage() {
        return dateMarquage;
    }

    public void setDateMarquage(Date dateMarquage) {
        this.dateMarquage = dateMarquage;
    }
}
