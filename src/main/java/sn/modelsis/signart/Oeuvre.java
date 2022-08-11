package sn.modelsis.signart;

import sn.modelsis.signart.facade.MagasinFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Oeuvre", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Oeuvre.findAll", query = "SELECT o FROM Oeuvre o")
    , @NamedQuery(name = "Oeuvre.countOeuvreByArtiste", query = "SELECT count(o) FROM Oeuvre o WHERE o.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "Oeuvre.findById", query = "SELECT o FROM Oeuvre o WHERE o.id = :id")
    , @NamedQuery(name = "Oeuvre.findByNom", query = "SELECT o FROM Oeuvre o WHERE o.nom = :nom")
    , @NamedQuery(name = "Oeuvre.findByDescription", query = "SELECT o FROM Oeuvre o WHERE o.description = :description")
    , @NamedQuery(name = "Oeuvre.findByPrix", query = "SELECT o FROM Oeuvre o WHERE o.prix = :prix")
    , @NamedQuery(name = "Oeuvre.findByTaxes", query = "SELECT o FROM Oeuvre o WHERE o.taxes = :taxes")
    , @NamedQuery(name = "Oeuvre.findByTauxremise", query = "SELECT o FROM Oeuvre o WHERE o.tauxremise = :tauxremise")
    , @NamedQuery(name = "Oeuvre.findByFraisLivraison", query = "SELECT o FROM Oeuvre o WHERE o.fraisLivraison = :fraisLivraison")
    , @NamedQuery(name = "Oeuvre.findByDateAjout", query = "SELECT o FROM Oeuvre o WHERE o.dateAjout = :dateAjout")
    , @NamedQuery(name = "Oeuvre.findByNouveau", query = "SELECT o FROM Oeuvre o WHERE o.nouveau = :nouveau")
    , @NamedQuery(name = "Oeuvre.findByDimensions", query = "SELECT o FROM Oeuvre o WHERE o.dimensions = :dimensions")})
public class Oeuvre implements Serializable {

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
    @ManyToOne(optional= false)
    private Couleur idCouleur;
        
    @Column(name = "nouveau")
    private Boolean nouveau;
    
     @Column(name = "lithographie")
    private Boolean lithographie;
      
      @Column(name = "auteur", length = 50)
    private String auteur;
      
       @Column(name = "dimensions", length = 50)
    private String dimensions;
       
       @Column(name = "annee")
    private Integer annee;
       
       @Column(name = "stock")
    private Integer stock;
           // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix", precision = 19, scale = 4)
    private BigDecimal prix;
        
    @Column(name = "tauxremise")
    private Integer tauxremise;
        
    @Column(name = "taxes", precision = 19, scale = 4)
    private BigDecimal taxes;
    
        @Lob
    @Column(name = "image")
    private byte[] image;
            
    @Column(name = "description", length = 1000)
    private String description;
    
      @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    
    @Column(name = "fraisLivraison", precision = 19, scale = 4)
    private BigDecimal fraisLivraison;
    
    @Column(name = "dateAjout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjout;
 
    @Lob
    @Column(name = "miniature")
    private byte[] miniature;
    
    @ManyToMany(mappedBy = "oeuvreSet")
    private Set<MotCle> motCleSet;
    
    @JoinTable(name = "Theme_Oeuvre", joinColumns = {
        @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = true)}, inverseJoinColumns = {
        @JoinColumn(name = "idTheme", referencedColumnName = "id", nullable = true)})
    @ManyToMany
    private Set<Theme> themeSet;
    
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "oeuvreSet")
    private Set<Domaine> domaineSet;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOeuvre")
    private Set<Image> imageSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOeuvre")
    private Set<MarquageOeuvre> marquageOeuvreSet;
    
  
   
    @JoinColumn(name = "idStatut", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private StatutOeuvre idStatut;

    @JoinColumn(name = "idMagasin", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Magasin idMagasin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOeuvre")
    private Set<PromotionOeuvre> promotionOeuvreSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOeuvre")
    private Set<LignePanier> lignePanierSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOeuvre")
    private Set<LigneCommande> ligneCommandeSet;

    public Oeuvre() {
    }

    public Oeuvre(Integer id) {
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

    public Magasin getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(Magasin idMagasin) {
        this.idMagasin = idMagasin;
    }
    @XmlTransient
    public Set<MotCle> getMotCleSet() {
        return motCleSet;
    }

    public void setMotCleSet(Set<MotCle> motCleSet) {
        this.motCleSet = motCleSet;
    }

    @XmlTransient
    public Set<Theme> getThemeSet() {
        return themeSet;
    }

    public void setThemeSet(Set<Theme> themeSet) {
        this.themeSet = themeSet;
    }

    @XmlTransient
    public Set<Domaine> getDomaineSet() {
        return domaineSet;
    }

    public void setDomaineSet(Set<Domaine> domaineSet) {
        this.domaineSet = domaineSet;
    }

    @XmlTransient
    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @XmlTransient
    public Set<MarquageOeuvre> getMarquageOeuvreSet() {
        return marquageOeuvreSet;
    }

    public void setMarquageOeuvreSet(Set<MarquageOeuvre> marquageOeuvreSet) {
        this.marquageOeuvreSet = marquageOeuvreSet;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Couleur getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Couleur idCouleur) {
        this.idCouleur = idCouleur;
    }

    public Technique getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Technique idTechnique) {
        this.idTechnique = idTechnique;
    }

    public StatutOeuvre getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(StatutOeuvre idStatut) {
        this.idStatut = idStatut;
    }

    @XmlTransient
    public Set<PromotionOeuvre> getPromotionOeuvreSet() {
        return promotionOeuvreSet;
    }

    public void setPromotionOeuvreSet(Set<PromotionOeuvre> promotionOeuvreSet) {
        this.promotionOeuvreSet = promotionOeuvreSet;
    }

    @XmlTransient
    public Set<LignePanier> getLignePanierSet() {
        return lignePanierSet;
    }

    public void setLignePanierSet(Set<LignePanier> lignePanierSet) {
        this.lignePanierSet = lignePanierSet;
    }

    @XmlTransient
    public Set<LigneCommande> getLigneCommandeSet() {
        return ligneCommandeSet;
    }

    public void setLigneCommandeSet(Set<LigneCommande> ligneCommandeSet) {
        this.ligneCommandeSet = ligneCommandeSet;
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
    
    
    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
        if (!(object instanceof Oeuvre)) {
            return false;
        }
        Oeuvre other = (Oeuvre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Oeuvre[ id=" + id + " ]";
    }
    
}
