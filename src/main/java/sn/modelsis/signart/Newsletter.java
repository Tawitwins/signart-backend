package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Newsletter", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Newsletter.findAll", query = "SELECT n FROM Newsletter n")
    , @NamedQuery(name = "Newsletter.findById", query = "SELECT n FROM Newsletter n WHERE n.id = :id")
    , @NamedQuery(name = "Newsletter.findByEnvoye", query = "SELECT n FROM Newsletter n WHERE n.envoye = :envoye")
    , @NamedQuery(name = "Newsletter.findByDateEnvoi", query = "SELECT n FROM Newsletter n WHERE n.dateEnvoi = :dateEnvoi")})
public class Newsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "contenu", nullable = false, length = 2147483647)
    private String contenu;
    @Basic(optional = false)
    @Column(name = "envoye", nullable = false)
    private boolean envoye;
    @Basic(optional = false)
    @Column(name = "dateEnvoi", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;
    @JoinColumn(name = "idTypeNewsletter", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TypeNewsletter idTypeNewsletter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNewsletter")
    private Set<NewsletterTrace> newsletterTraceSet;

    public Newsletter() {
    }

    public Newsletter(Integer id) {
        this.id = id;
    }

    public Newsletter(Integer id, String contenu, boolean envoye, Date dateEnvoi) {
        this.id = id;
        this.contenu = contenu;
        this.envoye = envoye;
        this.dateEnvoi = dateEnvoi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public boolean getEnvoye() {
        return envoye;
    }

    public void setEnvoye(boolean envoye) {
        this.envoye = envoye;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public TypeNewsletter getIdTypeNewsletter() {
        return idTypeNewsletter;
    }

    public void setIdTypeNewsletter(TypeNewsletter idTypeNewsletter) {
        this.idTypeNewsletter = idTypeNewsletter;
    }

    @XmlTransient
    public Set<NewsletterTrace> getNewsletterTraceSet() {
        return newsletterTraceSet;
    }

    public void setNewsletterTraceSet(Set<NewsletterTrace> newsletterTraceSet) {
        this.newsletterTraceSet = newsletterTraceSet;
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
        if (!(object instanceof Newsletter)) {
            return false;
        }
        Newsletter other = (Newsletter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Newsletter[ id=" + id + " ]";
    }
    
}
