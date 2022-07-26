package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ModePaiement", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ModePaiement.findAll", query = "SELECT m FROM ModePaiement m")
    , @NamedQuery(name = "ModePaiement.findById", query = "SELECT m FROM ModePaiement m WHERE m.id = :id")
    , @NamedQuery(name = "ModePaiement.findByCode", query = "SELECT m FROM ModePaiement m WHERE m.code = :code")
    , @NamedQuery(name = "ModePaiement.findByLibelle", query = "SELECT m FROM ModePaiement m WHERE m.libelle = :libelle")})
public class ModePaiement implements Serializable {

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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "idModePaiement")
    private Set<Paiement> paiementSet;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "idModePaiement")
    private Set<LignePaiement> lignePaiementSet;

    public ModePaiement() {
    }

    public ModePaiement(Integer id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Set<Paiement> getPaiementSet() {
        return paiementSet;
    }

    public void setPaiementSet(Set<Paiement> paiementSet) {
        this.paiementSet = paiementSet;
    }

    @XmlTransient
    public Set<LignePaiement> getLignePaiementSet() {
        return lignePaiementSet;
    }

    public void setLignePaiementSet(Set<LignePaiement> lignePaiementSet) {
        this.lignePaiementSet = lignePaiementSet;
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
        if (!(object instanceof ModePaiement)) {
            return false;
        }
        ModePaiement other = (ModePaiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ModePaiement[ id=" + id + " ]";
    }

}
