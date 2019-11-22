package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Paiement", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Paiement.findAll", query = "SELECT p FROM Paiement p")
    , @NamedQuery(name = "Paiement.findById", query = "SELECT p FROM Paiement p WHERE p.id = :id")
    , @NamedQuery(name = "Paiement.findByDate", query = "SELECT p FROM Paiement p WHERE p.datePaiement = :datePaiement")})
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "datePaiement", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaiement")
    private Set<LignePaiement> lignePaiementSet;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Commande commande;
    @JoinColumn(name = "idEtatPaiement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Integer idEtatPaiement;
    @JoinColumn(name = "idModePaiement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Integer idModePaiement;

    public Paiement() {
    }

    public Paiement(Integer id) {
        this.id = id;
    }

    public Paiement(Integer id, Date date) {
        this.id = id;
        this.datePaiement = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    @XmlTransient
    public Set<LignePaiement> getLignePaiementSet() {
        return lignePaiementSet;
    }

    public void setLignePaiementSet(Set<LignePaiement> lignePaiementSet) {
        this.lignePaiementSet = lignePaiementSet;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getIdEtatPaiement() {
        return idEtatPaiement;
    }

    public void setIdEtatPaiement(Integer idEtatPaiement) {
        this.idEtatPaiement = idEtatPaiement;
    }

    public Integer getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(Integer idModePaiement) {
        this.idModePaiement = idModePaiement;
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
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Paiement[ id=" + id + " ]";
    }
    
}
