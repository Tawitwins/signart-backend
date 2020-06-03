package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNLOM
 */
public class ArtisteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idUser;
    private String nom;
    private String prenom;
    private String surnom;
    private String telephone;
    private String adresse;
    private String ville;
    private String biographie;

    private String email;
    private String genre;
    private String nomGalerie;
    private String adrGalerie;
    private String villeGalerie;
    private String specialites;
    private String formation;
    private String expositions;

    private String profession;
    private String etatArtiste;
    private String pays;
    private Long nbFans;
    private Long nbOeuvres;
    private Integer idBiographie;

    public ArtisteDto(Integer id, String nom, String prenom, String surnom, String telephone, String adresse, String ville, String pays, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.email = email;
    }
    
    

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
     public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBiographie() {
        return idBiographie;
    }

    public void setIdBiographie(Integer idBiographie) {
        this.idBiographie = idBiographie;
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

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

     public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getNomGalerie() {
        return nomGalerie;
    }

    public void setNomGalerie(String nomGalerie) {
        this.nomGalerie = nomGalerie;
    }

    public String getAdrGalerie() {
        return adrGalerie;
    }

    public void setAdrGalerie(String adrGalerie ) {
        this.adrGalerie = adrGalerie;
    }

    public String getVilleGalerie() {
        return villeGalerie;
    }

    public void setVilleGalerie(String villeGalerie) {
        this.villeGalerie = villeGalerie;
    }

    public String getSpecialites() {
        return specialites;
    }

    public void setSpecialites(String specialites) {
        this.specialites = specialites;
    }
    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    public String getExpositions() {
        return expositions;
    }

    public void setExpositions(String expositions) {
        this.expositions = expositions;
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
package sn.modelsis.signart.dto;

import java.io.Serializable;

