package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNMBENGUEO
 */
public class ServiceLivraisonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String telephone;
    private String adresse;
    private String email;

    public ServiceLivraisonDto(Integer id, String nom, String telephone, String adresse, String email) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
    }



    public ServiceLivraisonDto() {
    }

    public ServiceLivraisonDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
        if (!(object instanceof ServiceLivraisonDto)) {
            return false;
        }
        ServiceLivraisonDto other = (ServiceLivraisonDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ServiceLivraisonDto[ id=" + id + " ]";
    }
    
}


