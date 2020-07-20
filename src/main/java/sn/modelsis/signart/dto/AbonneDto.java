/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author Pendaaa
 */
public class AbonneDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String pays;
    private String region;
    private String ville;
    private String adresse;
    private Integer idUtilisateur;
    private Integer idListeSelection;
    

    public AbonneDto(Integer id, String nom, String prenom, String email, String telephone, String pays, String region, String ville, String adresse, Integer idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
        this.idUtilisateur = idUtilisateur;
    }

    public AbonneDto(String nom, String prenom, String email, String telephone, String pays, String region, String ville, String adresse, Integer idUtilisateur) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
        this.idUtilisateur = idUtilisateur;
    }

    public AbonneDto() {
    }

    public Integer getIdListeSelection() {
        return idListeSelection;
    }

    public void setIdListeSelection(Integer idListeSelection) {
        this.idListeSelection = idListeSelection;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    
}
