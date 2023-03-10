/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import javax.persistence.Column;

/**
 *
 * @author Pendaaa
 */
public class OeuvreNumeriqueDto {
    private Integer id;
    private Integer annee;
    private Integer identiteAuteur; 
    private String titre;
    private Integer largeur;
    private Integer longueur;
    private String motscles;
    private Integer tarif;
    private String description;
    private String technique;
    private ImageNumeriqueDto avatar;
    private String nom;

    private Float poids;

    private String niveauPoids;
    private String dimensionLevel;

    public OeuvreNumeriqueDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getIdentiteAuteur() {
        return identiteAuteur;
    }

    public void setIdentiteAuteur(Integer identiteAuteur) {
        this.identiteAuteur = identiteAuteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public Integer getLongueur() {
        return longueur;
    }

    public void setLongueur(Integer longueur) {
        this.longueur = longueur;
    }

    public String getMotscles() {
        return motscles;
    }

    public void setMotscles(String motscles) {
        this.motscles = motscles;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public ImageNumeriqueDto getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageNumeriqueDto avatar) {
        this.avatar = avatar;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Float getPoids() {
        return poids;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public String getDimensionLevel() {
        return dimensionLevel;
    }

    public void setDimensionLevel(String dimensionLevel) {
        this.dimensionLevel = dimensionLevel;
    }
    public String getNiveauPoids() {
        return niveauPoids;
    }

    public void setNiveauPoids(String niveauPoids) {
        this.niveauPoids = niveauPoids;
    }


    
    
    
    
}
