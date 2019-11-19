package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "EtatCommande", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "EtatCommande.findAll", query = "SELECT e FROM EtatCommande e")
    , @NamedQuery(name = "EtatCommande.findById", query = "SELECT e FROM EtatCommande e WHERE e.id = :id")
    , @NamedQuery(name = "EtatCommande.findByCode", query = "SELECT e FROM EtatCommande e WHERE e.code = :code")
    , @NamedQuery(name = "EtatCommande.findByLibelle", query = "SELECT e FROM EtatCommande e WHERE e.libelle = :libelle")})
public class EtatCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 15)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatCommande")
    private Set<Commande> commandeSet;

    public EtatCommande() {
    }

    public EtatCommande(Integer id) {
        this.id = id;
    }

    public EtatCommande(Integer id, String libelle) {
        this.id = id;
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
    public Set<Commande> getCommandeSet() {
        return commandeSet;
    }

    public void setCommandeSet(Set<Commande> commandeSet) {
        this.commandeSet = commandeSet;
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
        if (!(object instanceof EtatCommande)) {
            return false;
        }
        EtatCommande other = (EtatCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EtatCommande[ id=" + id + " ]";
    }

}
