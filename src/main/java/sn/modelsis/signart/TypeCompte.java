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
@Table(name = "TypeCompte", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TypeCompte.findAll", query = "SELECT t FROM TypeCompte t")
    , @NamedQuery(name = "TypeCompte.findById", query = "SELECT t FROM TypeCompte t WHERE t.id = :id")
    , @NamedQuery(name = "TypeCompte.findByLibelle", query = "SELECT t FROM TypeCompte t WHERE t.libelle = :libelle")})
public class TypeCompte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", length = 70)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeCompte")
    private Set<Compte> compteSet;

    public TypeCompte() {
    }

    public TypeCompte(Integer id) {
        this.id = id;
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
    public Set<Compte> getCompteSet() {
        return compteSet;
    }

    public void setCompteSet(Set<Compte> compteSet) {
        this.compteSet = compteSet;
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
        if (!(object instanceof TypeCompte)) {
            return false;
        }
        TypeCompte other = (TypeCompte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TypeCompte[ id=" + id + " ]";
    }
    
}
