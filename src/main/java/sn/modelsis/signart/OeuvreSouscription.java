package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author SNNGOM
 */
@Entity
@Table(name = "OeuvreSouscription", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "OeuvreSouscription.findAll", query = "SELECT o FROM OeuvreSouscription o")
    , @NamedQuery(name = "OeuvreSouscription.countOeuvreSouscriptionBySouscription", query = "SELECT count(o) FROM OeuvreSouscription o WHERE o.idSouscription.id = :idSouscription")
    , @NamedQuery(name = "OeuvreSouscription.findById", query = "SELECT o FROM OeuvreSouscription o WHERE o.id = :id")
    , @NamedQuery(name = "OeuvreSouscription.findByNom", query = "SELECT o FROM OeuvreSouscription o WHERE o.nom = :nom")
    , @NamedQuery(name = "OeuvreSouscription.findByDescription", query = "SELECT o FROM OeuvreSouscription o WHERE o.description = :description")
    , @NamedQuery(name = "OeuvreSouscription.findByPrix", query = "SELECT o FROM OeuvreSouscription o WHERE o.prix = :prix")})
public class OeuvreSouscription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "prix", precision = 19, scale = 4)
    private BigDecimal prix;
    @JoinColumn(name = "idSouscription", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Souscription idSouscription;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OeuvreSouscription)) {
            return false;
        }
        OeuvreSouscription other = (OeuvreSouscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.OeuvreSouscription[ id=" + id + " ]";
    }

    public Souscription getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Souscription idSouscription) {
        this.idSouscription = idSouscription;
    }
    
}
