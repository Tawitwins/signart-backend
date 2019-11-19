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
@Table(name = "EtatLignePanier", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "EtatLignePanier.findAll", query = "SELECT e FROM EtatLignePanier e")
    , @NamedQuery(name = "EtatLignePanier.findById", query = "SELECT e FROM EtatLignePanier e WHERE e.id = :id")
    , @NamedQuery(name = "EtatLignePanier.findByCode", query = "SELECT e FROM EtatLignePanier e WHERE e.code = :code")
    , @NamedQuery(name = "EtatLignePanier.findByLibelle", query = "SELECT e FROM EtatLignePanier e WHERE e.libelle = :libelle")})
public class EtatLignePanier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 15)
    private String code;
    @Column(name = "libelle", length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatLignePanier")
    private Set<LignePanier> lignePanierSet;

    public EtatLignePanier() {
    }

    public EtatLignePanier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public Set<LignePanier> getLignePanierSet() {
        return lignePanierSet;
    }

    public void setLignePanierSet(Set<LignePanier> lignePanierSet) {
        this.lignePanierSet = lignePanierSet;
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
        if (!(object instanceof EtatLignePanier)) {
            return false;
        }
        EtatLignePanier other = (EtatLignePanier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EtatLignePanier[ id=" + id + " ]";
    }

}
