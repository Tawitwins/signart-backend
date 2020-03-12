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
 * @author SNLOM
 */
@Entity
@Table(name = "Pays", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p"),
    @NamedQuery(name = "Pays.findById", query = "SELECT p FROM Pays p WHERE p.id = :id"),
    @NamedQuery(name = "Pays.findByCode", query = "SELECT p FROM Pays p WHERE p.code = :code"),
    @NamedQuery(name = "Pays.findByLibelle", query = "SELECT p FROM Pays p WHERE p.libelle = :libelle")})
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPays")
    private Set<Artiste> artisteSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPays")
    private Set<Client> clientSet;
    //fetch = FetchType.EAGER
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Pays")
    private Set<Visiteur> VisiteurSet;

    public Pays() {
    }

    public Pays(Integer id) {
        this.id = id;
    }

    public Pays(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
    }
    @XmlTransient
    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
    }
    
    @XmlTransient
    public Set<Visiteur> getVisiteurSet() {
        return VisiteurSet;
    }

    public void setVisiteurSet(Set<Visiteur> VisiteurSet) {
        this.VisiteurSet = VisiteurSet;
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
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Pays[ id=" + id + " ]";
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
