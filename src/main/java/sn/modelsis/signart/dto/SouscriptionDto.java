package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SNNGOM
 */
public class SouscriptionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Character sexe;
    private String codePays;
    private String siteWeb;
    private String specialite;
    private String nomGalerie;
    private String adresseGalerie;
    private String ville;
    private String formation;
    private String exposition;
    //private List<OeuvreSouscriptionDto> oeuvres;
    //private List<OeuvreSouscriptionDto> listeuvres;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNomGalerie() {
        return nomGalerie;
    }

    public void setNomGalerie(String nomGalerie) {
        this.nomGalerie = nomGalerie;
    }

    public String getAdresseGalerie() {
        return adresseGalerie;
    }

    public void setAdresseGalerie(String adresseGalerie) {
        this.adresseGalerie = adresseGalerie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getExposition() {
        return exposition;
    }

    public void setExposition(String exposition) {
        this.exposition = exposition;
    }

    /*public List<OeuvreSouscriptionDto> getOeuvres() {
        return oeuvres;
    }

    public void setOeuvres(List<OeuvreSouscriptionDto> oeuvres) {
        this.oeuvres = oeuvres;
    }*/
}