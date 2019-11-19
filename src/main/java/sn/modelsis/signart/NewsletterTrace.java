package sn.modelsis.signart;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "NewsletterTrace", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewsletterTrace.findAll", query = "SELECT n FROM NewsletterTrace n")
    , @NamedQuery(name = "NewsletterTrace.findById", query = "SELECT n FROM NewsletterTrace n WHERE n.id = :id")
    , @NamedQuery(name = "NewsletterTrace.findByStatut", query = "SELECT n FROM NewsletterTrace n WHERE n.statut = :statut")})
public class NewsletterTrace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "statut", nullable = false, length = 20)
    private String statut;
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "idNewsletter", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Newsletter idNewsletter;

    public NewsletterTrace() {
    }

    public NewsletterTrace(Integer id) {
        this.id = id;
    }

    public NewsletterTrace(Integer id, String statut) {
        this.id = id;
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Newsletter getIdNewsletter() {
        return idNewsletter;
    }

    public void setIdNewsletter(Newsletter idNewsletter) {
        this.idNewsletter = idNewsletter;
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
        if (!(object instanceof NewsletterTrace)) {
            return false;
        }
        NewsletterTrace other = (NewsletterTrace) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.NewsletterTrace[ id=" + id + " ]";
    }
    
}
