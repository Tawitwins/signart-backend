package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "LignePaiement", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LignePaiement.findAll", query = "SELECT l FROM LignePaiement l")
    , @NamedQuery(name = "LignePaiement.findById", query = "SELECT l FROM LignePaiement l WHERE l.id = :id")
    , @NamedQuery(name = "LignePaiement.findByDatePaiement", query = "SELECT l FROM LignePaiement l WHERE l.datePaiement = :datePaiement")
    , @NamedQuery(name = "LignePaiement.findByMontant", query = "SELECT l FROM LignePaiement l WHERE l.montant = :montant")
    , @NamedQuery(name = "LignePaiement.findByTokenPaiement", query = "SELECT l FROM LignePaiement l WHERE l.tokenPaiement = :tokenPaiement")
})

public class LignePaiement implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "montant", nullable = false, precision = 19, scale = 4)
    private BigDecimal montant;
    @JoinColumn(name = "idModePaiement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ModePaiement idModePaiement;
    @JoinColumn(name = "idPaiement", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Paiement idPaiement;

    @JoinColumn(name = "idLigneCommande", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LigneCommande idLigneCommande;

    @JoinColumn(name = "idEtatPaiement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatPaiement idEtatPaiement;

    @JoinColumn(name = "idPaymentDetails", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private PaymentDetails idPaymentDetails;

    @Column(name = "tokenPaiement", length = 200)
    private String tokenPaiement;

    public LignePaiement() {
    }

    public LignePaiement(Integer id) {
        this.id = id;
    }

    public LignePaiement(Integer id, Date datePaiement, BigDecimal montant) {
        this.id = id;
        this.datePaiement = datePaiement;
        this.montant = montant;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public ModePaiement getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(ModePaiement idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public Paiement getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Paiement idPaiement) {
        this.idPaiement = idPaiement;
    }
    public LigneCommande getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(LigneCommande idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }
    public EtatPaiement getIdEtatPaiement() {
        return idEtatPaiement;
    }

    public void setIdEtatPaiement(EtatPaiement idEtatPaiement) {
        this.idEtatPaiement = idEtatPaiement;
    }

    public PaymentDetails getIdPaymentDetails() {
        return idPaymentDetails;
    }

    public void setIdPaymentDetails(PaymentDetails idPaymentDetails) {
        this.idPaymentDetails = idPaymentDetails;
    }

    public String getTokenPaiement() {
        return tokenPaiement;
    }

    public void setTokenPaiement(String tokenPaiement) {
        this.tokenPaiement = tokenPaiement;
    }

    /* @Override
            public int hashCode() {
                int hash = 0;
                hash += (id != null ? id.hashCode() : 0);
                return hash;
            }*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.datePaiement);
        hash = 79 * hash + Objects.hashCode(this.idPaiement);
        hash = 79 * hash + Objects.hashCode(this.montant);
        hash = 79 * hash + Objects.hashCode(this.idLigneCommande);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LignePaiement)) {
            return false;
        }
        LignePaiement other = (LignePaiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.LignePaiement[ id=" + id + " ]";
    }
    
}
