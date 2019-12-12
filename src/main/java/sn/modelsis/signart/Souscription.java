package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNNGOM
 */
@Entity
@Table(name = "Souscription", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Souscription.findAll", query = "SELECT a FROM Souscription a")
    , @NamedQuery(name = "Souscription.findById", query = "SELECT a FROM Souscription a WHERE a.id = :id")
    , @NamedQuery(name = "Souscription.findByNom", query = "SELECT a FROM Souscription a WHERE a.nom = :nom")
    , @NamedQuery(name = "Souscription.findByPrenom", query = "SELECT a FROM Souscription a WHERE a.prenom = :prenom")
    , @NamedQuery(name = "Souscription.findByEmail", query = "SELECT a FROM Souscription a WHERE a.email = :email")
    , @NamedQuery(name = "Souscription.findByTelephone", query = "SELECT a FROM Souscription a WHERE a.telephone = :telephone")
    , @NamedQuery(name = "Souscription.findByAdresseGalerie", query = "SELECT a FROM Souscription a WHERE a.adresseGalerie = :adresseGalerie")
    , @NamedQuery(name = "Souscription.findByVille", query = "SELECT a FROM Souscription a WHERE a.ville = :ville")})
public class Souscription implements Serializable {
    /**
     *
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
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "sexe", length = 1)
    private Character sexe;
    @Column(name = "codePays", length = 5)
    private String codePays;
    @Column(name = "siteWeb", length = 50)
    private String siteWeb;
    @Column(name = "specialite", length = 50)
    private String specialite;
    @Column(name = "nomGalerie", length = 50)
    private String nomGalerie;
    @Column(name = "adresseGalerie", length = 100)
    private String adresseGalerie;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "formation", length = 255)
    private String formation;
    @Column(name = "exposition", length = 255)
    private String exposition;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSouscription")
    private Set<OeuvreSouscription> oeuvreSouscriptionSet;

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
    @XmlTransient
    public Set<OeuvreSouscription> getOeuvreSouscriptionSet() {
        return oeuvreSouscriptionSet;
    }

    public void setOeuvreSouscriptionSet(Set<OeuvreSouscription> oeuvreSouscriptionSet) {
        this.oeuvreSouscriptionSet = oeuvreSouscriptionSet;
    }
    
}