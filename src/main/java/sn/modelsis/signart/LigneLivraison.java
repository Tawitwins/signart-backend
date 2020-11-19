package sn.modelsis.signart;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "LigneLivraison", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneLivraison.findAll", query = "SELECT l FROM LigneLivraison l")
    , @NamedQuery(name = "LigneLivraison.findById", query = "SELECT l FROM LigneLivraison l WHERE l.id = :id")
    , @NamedQuery(name = "LigneLivraison.findByDateLivraison", query = "SELECT l FROM LigneLivraison l WHERE l.dateLivraison = :dateLivraison")})
public class LigneLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateLivraison", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dateLivraison;
    @JoinColumn(name = "idEtatLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatLivraison idEtatLivraison;
    @JoinColumn(name = "idLigneCommande", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LigneCommande idLigneCommande;
    @JoinColumn(name = "idLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Livraison idLivraison;
    @JoinColumn(name = "idModeLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ModeLivraison idModeLivraison;

    public LigneLivraison() {
    }

    public LigneLivraison(Integer id) {
        this.id = id;
    }

    public LigneLivraison(Integer id, LocalDate dateLivraison) {
        this.id = id;
        this.dateLivraison = dateLivraison;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public EtatLivraison getIdEtatLivraison() {
        return idEtatLivraison;
    }

    public void setIdEtatLivraison(EtatLivraison idEtatLivraison) {
        this.idEtatLivraison = idEtatLivraison;
    }

    public LigneCommande getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(LigneCommande idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public Livraison getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Livraison idLivraison) {
        this.idLivraison = idLivraison;
    }

    public ModeLivraison getIdModeLivraison() {
        return idModeLivraison;
    }

    public void setIdModeLivraison(ModeLivraison idModeLivraison) {
        this.idModeLivraison = idModeLivraison;
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
        if (!(object instanceof LigneLivraison)) {
            return false;
        }
        LigneLivraison other = (LigneLivraison) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.LigneLivraison[ id=" + id + " ]";
    }
    
}
