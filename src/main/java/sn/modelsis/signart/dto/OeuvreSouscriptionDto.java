/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author snfayemp
 */
public class OeuvreSouscriptionDto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private Integer idTechnique;
    private Integer idCouleur;
    private Boolean nouveau;
    private Boolean lithographie;
    private String auteur;
    private String dimensions;
    private Integer annee;
    private BigDecimal prix;
    private Integer tauxremise;
    private BigDecimal taxes;
    private ImageProfilDto image;
    private String description;
    private Integer idArtiste;
    private Integer idSouscription;
    private Date dateAjout;

    private boolean specialDelivery;

    private String libellePoids;

    private String libelleDimension;

    private String usure;

    private Float pourcentageOeuvre;

    private String reference;
    //private String imageText;
    public OeuvreSouscriptionDto() {
    }

    public OeuvreSouscriptionDto(Integer id) {
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
  

    public Boolean getNouveau() {
        return nouveau;
    }

    public void setNouveau(Boolean nouveau) {
        this.nouveau = nouveau;
    }

    public Boolean getLithographie() {
        return lithographie;
    }

    public void setLithographie(Boolean lithographie) {
        this.lithographie = lithographie;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getTauxremise() {
        return tauxremise;
    }

    public void setTauxremise(Integer tauxremise) {
        this.tauxremise = tauxremise;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }




    public ImageProfilDto getImage() {
        return image;
    }

    public void setImage(ImageProfilDto image) {
        this.image = image;
    } 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Integer idTechnique) {
        this.idTechnique = idTechnique;
    }

    public Integer getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Integer idCouleur) {
        this.idCouleur = idCouleur;
    }


 

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public boolean isSpecialDelivery() {
        return specialDelivery;
    }

    public void setSpecialDelivery(boolean specialDelivery) {
        this.specialDelivery = specialDelivery;
    }

    public String getLibellePoids() {
        return libellePoids;
    }

    public void setLibellePoids(String libellePoids) {
        this.libellePoids = libellePoids;
    }

    public String getLibelleDimension() {
        return libelleDimension;
    }

    public String getUsure() {
        return usure;
    }

    public void setUsure(String usure) {
        this.usure = usure;
    }

    public void setLibelleDimension(String libelleDimension) {
        this.libelleDimension = libelleDimension;
    }



    public Float getPourcentageOeuvre() {
        return pourcentageOeuvre;
    }

    public void setPourcentageOeuvre(Float pourcentageOeuvre) {
        this.pourcentageOeuvre = pourcentageOeuvre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

  /**  public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }**/
}
