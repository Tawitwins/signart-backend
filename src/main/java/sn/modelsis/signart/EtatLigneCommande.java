package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "EtatLigneCommande", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatLigneCommande.findAll", query = "SELECT e FROM EtatLigneCommande e")
    , @NamedQuery(name = "EtatLigneCommande.findById", query = "SELECT e FROM EtatLigneCommande e WHERE e.id = :id")
    , @NamedQuery(name = "EtatLigneCommande.findByCode", query = "SELECT e FROM EtatLigneCommande e WHERE e.code = :code")
    , @NamedQuery(name = "EtatLigneCommande.findByLibelle", query = "SELECT e FROM EtatLigneCommande e WHERE e.libelle = :libelle")})
public class EtatLigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 15)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatLigneCommande")
    private Set<LigneCommande> ligneCommandeSet;

    public EtatLigneCommande() {
    }

    public EtatLigneCommande(Integer id) {
        this.id = id;
    }

    public EtatLigneCommande(Integer id, String code, String libelle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
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
    public Set<LigneCommande> getLigneCommandeSet() {
        return ligneCommandeSet;
    }

    public void setLigneCommandeSet(Set<LigneCommande> ligneCommandeSet) {
        this.ligneCommandeSet = ligneCommandeSet;
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
        if (!(object instanceof EtatLigneCommande)) {
            return false;
        }
        EtatLigneCommande other = (EtatLigneCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EtatLigneCommande[ id=" + id + " ]";
    }
    
}
