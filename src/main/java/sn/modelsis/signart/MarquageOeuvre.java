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
@Table(name = "MarquageOeuvre", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarquageOeuvre.findMarqueByClient", query = "SELECT m.idOeuvre FROM MarquageOeuvre m where m.idTypeMarquage.code = :codeTypeMarquage and m.idClient.id = :idClient")
    , @NamedQuery(name = "MarquageOeuvre.findAll", query = "SELECT m FROM MarquageOeuvre m")
    , @NamedQuery(name = "MarquageOeuvre.findById", query = "SELECT m FROM MarquageOeuvre m WHERE m.id = :id")
    , @NamedQuery(name = "MarquageOeuvre.findByDateMarquage", query = "SELECT m FROM MarquageOeuvre m WHERE m.dateMarquage = :dateMarquage")
    , @NamedQuery(name = "MarquageOeuvre.findMarqueByClientAndOeuvre", query = "SELECT m FROM MarquageOeuvre m where m.idTypeMarquage.code = :codeTypeMarquage and m.idClient.id = :idClient and m.idOeuvre.id =:idOeuvre")})
public class MarquageOeuvre implements Serializable {

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
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Oeuvre idOeuvre;
    @JoinColumn(name = "idTypeMarquage", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TypeMarquage idTypeMarquage;

    public MarquageOeuvre() {
    }

    public MarquageOeuvre(Integer id) {
        this.id = id;
    }

    public MarquageOeuvre(Integer id, Date dateMarquage) {
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

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Oeuvre getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Oeuvre idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public TypeMarquage getIdTypeMarquage() {
        return idTypeMarquage;
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
        if (!(object instanceof MarquageOeuvre)) {
            return false;
        }
        MarquageOeuvre other = (MarquageOeuvre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.MarquageOeuvre[ id=" + id + " ]";
    }

}
