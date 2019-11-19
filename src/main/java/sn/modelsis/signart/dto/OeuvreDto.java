package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class OeuvreDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private byte[] image;
    private String description;
    private BigDecimal prix;
    private BigDecimal taxes;
    private Integer tauxremise;
    private BigDecimal fraisLivraison;
    private Date dateAjout;
    private Boolean nouveau;
    private String dimensions;
    private  byte[] miniature;
    //private Collection<MotCle> motCleCollection;
    //private Collection<Theme> themeCollection;
    //private Collection<Domaine> domaineCollection;
    private Integer idCouleur;
    private String couleur;
    private Integer idSousTechnique;
    private String sousTechnique;
    private Integer idTechnique;
    private String technique;
    private Integer idArtiste;
    private String artiste;

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

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public Integer getIdSousTechnique() {
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
    }

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
