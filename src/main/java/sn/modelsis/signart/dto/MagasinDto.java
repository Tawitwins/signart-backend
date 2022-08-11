package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNMBENGUEO
 */
public class MagasinDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String nomResp;
    private String telephoneResp;
    private String adresse;
    private String emailResp;
    public MagasinDto(Integer id, String nom, String nomResp,String telephoneResp, String adresse,String emailResp) {
        this.id = id;
        this.nom = nom;
        this.nomResp = nomResp;
        this.telephoneResp = telephoneResp;
        this.adresse = adresse;
        this.emailResp = emailResp;
    }



    public MagasinDto() {
    }

    public MagasinDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomResp() {
        return nomResp;
    }

    public void setNomResp(String nomResp) {
        this.nomResp = nomResp;
    }

    public String getTelephoneResp() {
        return telephoneResp;
    }

    public void setTelephoneResp(String telephoneResp) {
        this.telephoneResp = telephoneResp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmailResp() {
        return emailResp;
    }

    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }





    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MagasinDto)) {
            return false;
        }
        MagasinDto other = (MagasinDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.MagasinDto[ id=" + id + " ]";
    }
    
}


