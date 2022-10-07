package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import sn.modelsis.signart.Artiste;

/**
 *
 * @author SNLOM
 */
public class OeuvreDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private Integer idTechnique;
    private Integer idCouleur;
    private Boolean nouveau;

    private Boolean specialDelivery = false;
    private Boolean lithographie;
    private Boolean isPaid;
    private String auteur;
    private String artiste;
    private String dimensions;
    private Integer annee;
    private BigDecimal prix;
    private Integer tauxremise;
    private BigDecimal taxes;
    private byte[] image;
    private String description;
    private Integer idArtiste;
    private BigDecimal fraisLivraison;
    private Date dateAjout;   
    private  byte[] miniature;
    private Integer idStatus;
    private Integer idMagasin;
    private Integer stock;

    private String reference;
    
    
    
    //private Collection<MotCle> motCleCollection;
    //private Collection<Theme> themeCollection;
    //private Collection<Domaine> domaineCollection;    
    //private String couleur;
    //private Integer idSousTechnique;
    //private String sousTechnique;   
   // private String technique; 
    //private String artiste;

    public OeuvreDto(String nom, Integer idTechnique, Integer idCouleur, Boolean nouveau, Boolean specialDelivery,  Boolean lithographie, String auteur, String dimensions, Integer annee, BigDecimal prix, Integer tauxremise, BigDecimal taxes, byte[] image, String description, Integer idArtiste) {
        this.nom = nom;
        this.idTechnique = idTechnique;
        this.idCouleur = idCouleur;
        this.nouveau = nouveau;
        this.specialDelivery = specialDelivery;
        this.lithographie = lithographie;
        this.auteur = auteur;
        this.dimensions = dimensions;
        this.annee = annee;
        this.prix = prix;
        this.tauxremise = tauxremise;
        this.taxes = taxes;
        this.image = image;
        this.description = description;
        this.idArtiste = idArtiste;
    }
    
    

    public OeuvreDto() {
    }

    public OeuvreDto(Integer id) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public Integer getTauxremise() {
        return tauxremise;
    }

    public void setTauxremise(Integer tauxremise) {
        this.tauxremise = tauxremise;
    }

    public BigDecimal getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(BigDecimal fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Boolean getNouveau() {
        return nouveau;
    }

    public void setNouveau(Boolean nouveau) {
        this.nouveau = nouveau;
    }

    public Boolean getSpecialDelivery() {
        return specialDelivery;
    }

    public void setSpecialDelivery(Boolean specialDelivery) {
        this.specialDelivery = specialDelivery;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public byte[] getMiniature() {
        return miniature;
    }

    public void setMiniature(byte[] miniature) {
        this.miniature = miniature;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getArtiste() {
        return this.artiste;
    }
    
    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }
    

   /* public Integer getIdSousTechnique() {
        return idSousTechnique;
    }

    public void setIdSousTechnique(Integer idSousTechnique) {
        this.idSousTechnique = idSousTechnique;
    }

    public String getSousTechnique() {
        return sousTechnique;
    }

    public void setSousTechnique(String sousTechnique) {
        this.sousTechnique = sousTechnique;
    }*/

    public Integer getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Integer idTechnique) {
        this.idTechnique = idTechnique;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Integer getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Integer idCouleur) {
        this.idCouleur = idCouleur;
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

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        if (!(object instanceof OeuvreDto)) {
            return false;
        }
        OeuvreDto other = (OeuvreDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Oeuvre[ id=" + id + " ]";
    }
    
}
