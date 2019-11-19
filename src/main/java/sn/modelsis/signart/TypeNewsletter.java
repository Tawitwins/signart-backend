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
import javax.persistence.ManyToMany;
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
@Table(name = "TypeNewsletter", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeNewsletter.findAll", query = "SELECT t FROM TypeNewsletter t")
    , @NamedQuery(name = "TypeNewsletter.findById", query = "SELECT t FROM TypeNewsletter t WHERE t.id = :id")
    , @NamedQuery(name = "TypeNewsletter.findByPeriodicite", query = "SELECT t FROM TypeNewsletter t WHERE t.periodicite = :periodicite")
    , @NamedQuery(name = "TypeNewsletter.findByLibelle", query = "SELECT t FROM TypeNewsletter t WHERE t.libelle = :libelle")})
public class TypeNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "periodicite")
    private Integer periodicite;
    @Column(name = "libelle", length = 50)
    private String libelle;
    @ManyToMany(mappedBy = "typeNewsletterSet")
    private Set<Client> clientSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeNewsletter")
    private Set<Newsletter> newsletterSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeNewsletter")
    private Set<AbonnementNewsletter> abonnementNewsletterSet;

    public TypeNewsletter() {
    }

    public TypeNewsletter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Integer periodicite) {
        this.periodicite = periodicite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
    }

    @XmlTransient
    public Set<Newsletter> getNewsletterSet() {
        return newsletterSet;
    }

    public void setNewsletterSet(Set<Newsletter> newsletterSet) {
        this.newsletterSet = newsletterSet;
    }

    @XmlTransient
    public Set<AbonnementNewsletter> getAbonnementNewsletterSet() {
        return abonnementNewsletterSet;
    }

    public void setAbonnementNewsletterSet(Set<AbonnementNewsletter> abonnementNewsletterSet) {
        this.abonnementNewsletterSet = abonnementNewsletterSet;
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
        if (!(object instanceof TypeNewsletter)) {
            return false;
        }
        TypeNewsletter other = (TypeNewsletter) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TypeNewsletter[ id=" + id + " ]";
    }
    
}
