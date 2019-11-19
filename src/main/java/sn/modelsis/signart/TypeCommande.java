package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "TypeCommande", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TypeCommande.findAll", query = "SELECT t FROM TypeCommande t")
    , @NamedQuery(name = "TypeCommande.findById", query = "SELECT t FROM TypeCommande t WHERE t.id = :id")
    , @NamedQuery(name = "TypeCommande.findByLibelle", query = "SELECT t FROM TypeCommande t WHERE t.libelle = :libelle")})
public class TypeCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    public TypeCommande() {
    }

    public TypeCommande(Integer id) {
        this.id = id;
    }

    public TypeCommande(Integer id, String libelle) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeCommande)) {
            return false;
        }
        TypeCommande other = (TypeCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TypeCommande[ id=" + id + " ]";
    }
    
}
