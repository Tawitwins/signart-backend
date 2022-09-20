package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Souscription", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Souscription.findAll", query = "SELECT a FROM Souscription a")
    , @NamedQuery(name = "Souscription.findById", query = "SELECT a FROM Souscription a WHERE a.id = :id")
    , @NamedQuery(name = "Souscription.findByNom", query = "SELECT a FROM Souscription a WHERE a.nom = :nom")
    , @NamedQuery(name = "Souscription.findByPrenom", query = "SELECT a FROM Souscription a WHERE a.prenom = :prenom")
    , @NamedQuery(name = "Souscription.findByTelephone", query = "SELECT a FROM Souscription a WHERE a.telephone = :telephone")
    , @NamedQuery(name = "Souscription.findByVille", query = "SELECT a FROM Souscription a WHERE a.ville = :ville")})

public class Souscription implements Serializable {
   
    /*@Lob
    @Column(name = "photo")
    private byte[] photo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artiste")
    private Set<ArtisteFonction> artisteFonctionSet;
    */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "prenom", length = 50)
    private String prenom;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "genre", length = 1)
    private String genre;
    @Column(name = "nomGalerie", length = 50)
    private String nomGalerie;
    @Column(name = "siteWeb", length = 50)
    private String siteWeb;
    @Column(name = "adresseGalerie", length = 100)
    private String adrGalerie;
    @Column(name = "specialite", length = 100)
    private String specialites;
    @Column(name = "formation", length = 255)
    private String formation;
    @Column(name = "exposition", length = 255)
    private String expositions;
    @Column(name = "codePays", length = 5)
    private String codePays;
   
   
   
    public Souscription() {
    }

    public Souscription(Integer id) {
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


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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




   
    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
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
        if (!(object instanceof Souscription)) {
            return false;
        }
        Souscription other = (Souscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Artiste[ id=" + id + " ]";
    }

    /**
     * @return the formationSet
     */
    

    /*public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * @return the artisteFonctionSet
     */
    /*@XmlTransient
    public Set<ArtisteFonction> getArtisteFonctionSet() {
        return artisteFonctionSet;
    }

    /**
     * @param artisteFonctionSet the artisteFonctionSet to set
     */
   /* public void setArtisteFonctionSet(Set<ArtisteFonction> artisteFonctionSet) {
        this.artisteFonctionSet = artisteFonctionSet;
    }*/

}
