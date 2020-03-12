package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "MarquageArtiste", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarquageArtiste.findMarqueByClient", query = "SELECT m.idArtiste FROM MarquageArtiste m where m.idTypeMarquage.code = :codeTypeMarquage and m.idClient.id = :idClient")
    , @NamedQuery(name = "MarquageArtiste.countMarqueByClient", query = "SELECT count(m) FROM MarquageArtiste m where m.idTypeMarquage.code = :codeTypeMarquage")
    , @NamedQuery(name = "MarquageArtiste.findAll", query = "SELECT m FROM MarquageArtiste m")
    , @NamedQuery(name = "MarquageArtiste.findById", query = "SELECT m FROM MarquageArtiste m WHERE m.id = :id")
    , @NamedQuery(name = "MarquageArtiste.findByDateMarquage", query = "SELECT m FROM MarquageArtiste m WHERE m.dateMarquage = :dateMarquage")
    , @NamedQuery(name = "MarquageArtiste.findMarqueByClientAndArtiste", query = "SELECT m FROM MarquageArtiste m where m.idTypeMarquage.code = :codeTypeMarquage and m.idClient.id = :idClient and m.idArtiste.id =:idArtiste")
    , @NamedQuery(name = "MarquageArtiste.findMarqueByVisiteurAndArtiste", query = "SELECT m FROM MarquageArtiste m where m.idTypeMarquage.code = :codeTypeMarquage and m.idVisiteur.id = :idVisiteur and m.idArtiste.id =:idArtiste")})

public class MarquageArtiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateMarquage", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMarquage;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "idTypeMarquage", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TypeMarquage idTypeMarquage;
    
    @JoinColumn(name = "idVisiteur", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Visiteur idVisiteur;

    public MarquageArtiste() {
    }

    public MarquageArtiste(Integer id) {
        this.id = id;
    }

    public MarquageArtiste(Integer id, Date dateMarquage) {
        this.id = id;
        this.dateMarquage = dateMarquage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateMarquage() {
        return dateMarquage;
    }

    public void setDateMarquage(Date dateMarquage) {
        this.dateMarquage = dateMarquage;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public TypeMarquage getIdTypeMarquage() {
        return idTypeMarquage;
    }

    public Visiteur getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdVisiteur(Visiteur idVisiteur) {
        this.idVisiteur = idVisiteur;
    }
    public void setIdTypeMarquage(TypeMarquage idTypeMarquage) {
        this.idTypeMarquage = idTypeMarquage;
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
        if (!(object instanceof MarquageArtiste)) {
            return false;
        }
        MarquageArtiste other = (MarquageArtiste) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.MarquageArtiste[ id=" + id + " ]";
    }
}
