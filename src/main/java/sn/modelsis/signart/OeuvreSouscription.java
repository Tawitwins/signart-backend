/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author snfayemp
 */

@Entity
@Table(name = "OeuvreSouscription", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "OeuvreSouscription.findAll", query = "SELECT o FROM OeuvreSouscription o")
//    , @NamedQuery(name = "OeuvreSouscription.findAllButImg", query = "SELECT nom,idTechnique,idCouleur,nouveau,lithographie,auteur,dimensions,annee,prix,tauxremise,taxes,description,idArtiste,dateAjout,"
 //           + "id,idSouscription FROM OeuvreSouscription WHERE 1=1")
    , @NamedQuery(name = "OeuvreSouscription.countOeuvreSouscriptionByArtiste", query = "SELECT count(o) FROM OeuvreSouscription o WHERE o.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "OeuvreSouscription.findById", query = "SELECT o FROM OeuvreSouscription o WHERE o.id = :id")
    , @NamedQuery(name = "OeuvreSouscription.findByNom", query = "SELECT o FROM OeuvreSouscription o WHERE o.nom = :nom")
    , @NamedQuery(name = "OeuvreSouscription.findByDateAjout", query = "SELECT o FROM OeuvreSouscription o WHERE o.dateAjout = :dateAjout")
    , @NamedQuery(name = "OeuvreSouscription.findByNouveau", query = "SELECT o FROM OeuvreSouscription o WHERE o.nouveau = :nouveau")
        , @NamedQuery(name = "OeuvreSouscription.findBySpecialDelivery", query = "SELECT o FROM OeuvreSouscription o WHERE o.specialDelivery = :specialDelivery")
        , @NamedQuery(name = "OeuvreSouscription.findByUsure", query = "SELECT o FROM OeuvreSouscription o WHERE o.usure = :usure")
        , @NamedQuery(name = "OeuvreSouscription.findByLibelleDimension", query = "SELECT o FROM OeuvreSouscription o WHERE o.libelleDimension = :libelleDimension")
        , @NamedQuery(name = "OeuvreSouscription.findByLibellePoids", query = "SELECT o FROM OeuvreSouscription o WHERE o.libellePoids = :libellePoids")
        , @NamedQuery(name = "OeuvreSouscription.findByPourcentageOeuvre", query = "SELECT o FROM OeuvreSouscription o WHERE o.pourcentageOeuvre = :pourcentageOeuvre")
        , @NamedQuery(name = "OeuvreSouscription.findByReference", query = "SELECT o FROM OeuvreSouscription o WHERE o.reference = :reference")
        , @NamedQuery(name = "OeuvreSouscription.findByDimensions", query = "SELECT o FROM OeuvreSouscription o WHERE o.dimensions = :dimensions")})
public class OeuvreSouscription implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "nom", length = 200)
    private String nom;
        
   @JoinColumn(name = "idTechnique", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Technique idTechnique;
     
    @JoinColumn(name = "idCouleur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Couleur idCouleur;
        
    @Column(name = "nouveau")
    private Boolean nouveau;

   // @Column(name = "specialDelivery")
   // private Boolean specialDelivery;
     @Column(name = "lithographie")
    private Boolean lithographie;
      
      @Column(name = "auteur", length = 50)
    private String auteur;
      
       @Column(name = "dimensions", length = 50)
    private String dimensions;
       
       @Column(name = "annee")
    private Integer annee;
       
           // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix", precision = 19, scale = 4)
    private BigDecimal prix;
        
    @Column(name = "tauxremise")
    private Integer tauxremise;
        
    @Column(name = "taxes", precision = 19, scale = 4)
    private BigDecimal taxes;

    @Column(name = "image")
    private byte[] image;
            
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "specialDelivery")
    private boolean specialDelivery;

    @Column(name = "libellePoids" , length = 100)
    private String libellePoids;

    @Column(name = "libelleDimension" , length = 100)
    private String libelleDimension;

    @Column(name = "usure", length = 100)
    private String usure;

    @Column(name = "pourcentageOeuvre" , length = 100)
    private Float pourcentageOeuvre;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    
    @JoinColumn(name = "idSouscription", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Souscription idSouscription;
    
    @Column(name = "dateAjout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjout;
    @Column(name = "reference", length = 100)
    private String reference;

    public OeuvreSouscription() {
    }

    public OeuvreSouscription(Integer id) {
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

    public Technique getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Technique idTechnique) {
        this.idTechnique = idTechnique;
    }

    public Couleur getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Couleur idCouleur) {
        this.idCouleur = idCouleur;
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

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }
    
    public Souscription getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Souscription idSouscription) {
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

    public void setLibelleDimension(String libelleDimension) {
        this.libelleDimension = libelleDimension;
    }

    public String getUsure() {
        return usure;
    }

    public void setUsure(String usure) {
        this.usure = usure;
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
}
