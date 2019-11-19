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
@Table(name = "ModeLivraison", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ModeLivraison.findAll", query = "SELECT m FROM ModeLivraison m")
    , @NamedQuery(name = "ModeLivraison.findById", query = "SELECT m FROM ModeLivraison m WHERE m.id = :id")
    , @NamedQuery(name = "ModeLivraison.findByCode", query = "SELECT m FROM ModeLivraison m WHERE m.code = :code")
    , @NamedQuery(name = "ModeLivraison.findByLibelle", query = "SELECT m FROM ModeLivraison m WHERE m.libelle = :libelle")})
public class ModeLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 15)
    private String code;
    @Column(name = "libelle", length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModeLivraison")
    private Set<Livraison> livraisonSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModeLivraison")
    private Set<LigneLivraison> ligneLivraisonSet;

    public ModeLivraison() {
    }

    public ModeLivraison(Integer id) {
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
    public Set<Livraison> getLivraisonSet() {
        return livraisonSet;
    }

    public void setLivraisonSet(Set<Livraison> livraisonSet) {
        this.livraisonSet = livraisonSet;
    }

    @XmlTransient
    public Set<LigneLivraison> getLigneLivraisonSet() {
        return ligneLivraisonSet;
    }

    public void setLigneLivraisonSet(Set<LigneLivraison> ligneLivraisonSet) {
        this.ligneLivraisonSet = ligneLivraisonSet;
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
        if (!(object instanceof ModeLivraison)) {
            return false;
        }
        ModeLivraison other = (ModeLivraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ModeLivraison[ id=" + id + " ]";
    }

}
