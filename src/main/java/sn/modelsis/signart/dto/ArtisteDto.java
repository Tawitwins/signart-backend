package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNLOM
 */
public class ArtisteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String prenom;
    private String surnom;
    private String telephone;
    private String adresse;
    private String ville;
    private String biographie;
    private String profession;
    private String etatArtiste;
    private String pays;
    private Long nbFans;
    private Long nbOeuvres;

    public ArtisteDto() {
    }

    public ArtisteDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEtatArtiste() {
        return etatArtiste;
    }

    public void setEtatArtiste(String etatArtiste) {
        this.etatArtiste = etatArtiste;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getNbFans() {
        return nbFans;
    }

    public void setNbFans(Long nbFans) {
        this.nbFans = nbFans;
    }

    public Long getNbOeuvres() {
        return nbOeuvres;
    }

    public void setNbOeuvres(Long nbOeuvres) {
        this.nbOeuvres = nbOeuvres;
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
        if (!(object instanceof ArtisteDto)) {
            return false;
        }
        ArtisteDto other = (ArtisteDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Artiste[ id=" + id + " ]";
    }
    
}
