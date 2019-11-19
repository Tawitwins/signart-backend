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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Adresse", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a")
    , @NamedQuery(name = "Adresse.findById", query = "SELECT a FROM Adresse a WHERE a.id = :id")
    , @NamedQuery(name = "Adresse.findByAdresse", query = "SELECT a FROM Adresse a WHERE a.adresse = :adresse")
    , @NamedQuery(name = "Adresse.findByVille", query = "SELECT a FROM Adresse a WHERE a.ville = :ville")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "prenom", length = 50)
    private String prenom;
    @Column(name = "nom", length = 50)
    private String nom;
    @Basic(optional = false)
    @Column(name = "adresse", nullable = false, length = 500)
    private String adresse;
    @Column(name = "ville", length = 150)
    private String ville;
    @Column(name = "region", length = 100)
    private String region;
    @Basic(optional = false)
    @Column(name = "indicatif", nullable = false, length = 3)
    private String indicatif;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "idPays", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Pays idPays;
    @JoinColumn(name = "idTypeAdresse", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TypeAdresse idTypeAdresse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdresseLivraison")
    private Set<Livraison> livraisonSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdresseFacturation")
    private Set<Livraison> livraisonFacturationSet;

    public Adresse() {
    }

    public Adresse(Integer id) {
        this.id = id;
    }

    public Adresse(Integer id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    public TypeAdresse getIdTypeAdresse() {
        return idTypeAdresse;
    }

    public void setIdTypeAdresse(TypeAdresse idTypeAdresse) {
        this.idTypeAdresse = idTypeAdresse;
    }

    @XmlTransient
    public Set<Livraison> getLivraisonSet() {
        return livraisonSet;
    }

    public void setLivraisonSet(Set<Livraison> livraisonSet) {
        this.livraisonSet = livraisonSet;
    }

    @XmlTransient
    public Set<Livraison> getLivraisonFacturationSet() {
        return livraisonFacturationSet;
    }

    public void setLivraisonFacturationSet(Set<Livraison> livraisonFacturationSet) {
        this.livraisonFacturationSet = livraisonFacturationSet;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Adresse[ id=" + id + " ]";
    }
    
}
