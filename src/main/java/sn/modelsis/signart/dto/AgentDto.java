package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNMBENGUEO
 */
public class AgentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String prenom;
    private String surnom;
    private String telephone;
    private String adresse;
    private String ville;
    private String email;
    private Integer idUser;
    private Integer idMagasin;
    private Integer idServiceLivraison;
    private Integer idProfil;

    public AgentDto(Integer id, String nom, String prenom, String surnom, String telephone, String adresse, String ville, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ville = ville;
        this.email = email;
    }



    public AgentDto() {
    }

    public AgentDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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
        if (!(object instanceof AgentDto)) {
            return false;
        }
        AgentDto other = (AgentDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.AgentDto[ id=" + id + " ]";
    }
    
}


