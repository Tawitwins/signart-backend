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
@Table(name = "EtatPanier", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "EtatPanier.findAll", query = "SELECT e FROM EtatPanier e")
    , @NamedQuery(name = "EtatPanier.findById", query = "SELECT e FROM EtatPanier e WHERE e.id = :id")
    , @NamedQuery(name = "EtatPanier.findByCode", query = "SELECT e FROM EtatPanier e WHERE e.code = :code")
    , @NamedQuery(name = "EtatPanier.findByLibelle", query = "SELECT e FROM EtatPanier e WHERE e.libelle = :libelle")})
public class EtatPanier implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatPanier")
    private Set<Panier> panierSet;

    public EtatPanier() {
    }

    public EtatPanier(Integer id) {
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
    public Set<Panier> getPanierSet() {
        return panierSet;
    }

    public void setPanierSet(Set<Panier> panierSet) {
        this.panierSet = panierSet;
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
        if (!(object instanceof EtatPanier)) {
            return false;
        }
        EtatPanier other = (EtatPanier) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EtatPanier[ id=" + id + " ]";
    }

}
