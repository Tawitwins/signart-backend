package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PromotionOeuvre", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionOeuvre.findAll", query = "SELECT p FROM PromotionOeuvre p")
    , @NamedQuery(name = "PromotionOeuvre.findById", query = "SELECT p FROM PromotionOeuvre p WHERE p.id = :id")
    , @NamedQuery(name = "PromotionOeuvre.findByDateDebut", query = "SELECT p FROM PromotionOeuvre p WHERE p.dateDebut = :dateDebut")
    , @NamedQuery(name = "PromotionOeuvre.findByDateFin", query = "SELECT p FROM PromotionOeuvre p WHERE p.dateFin = :dateFin")
    , @NamedQuery(name = "PromotionOeuvre.findByTauxReduction", query = "SELECT p FROM PromotionOeuvre p WHERE p.tauxReduction = :tauxReduction")
    , @NamedQuery(name = "PromotionOeuvre.findByMontantReduction", query = "SELECT p FROM PromotionOeuvre p WHERE p.montantReduction = :montantReduction")})
public class PromotionOeuvre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateDebut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Column(name = "tauxReduction")
    private Integer tauxReduction;
    @Column(name = "montantReduction", precision = 19, scale = 4)
    private BigDecimal montantReduction;
    @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Oeuvre idOeuvre;

    public PromotionOeuvre() {
    }

    public PromotionOeuvre(Integer id) {
        this.id = id;
    }

    public PromotionOeuvre(Integer id, Date dateDebut, Date dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(Integer tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public BigDecimal getMontantReduction() {
        return montantReduction;
    }

    public void setMontantReduction(BigDecimal montantReduction) {
        this.montantReduction = montantReduction;
    }

    public Oeuvre getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Oeuvre idOeuvre) {
        this.idOeuvre = idOeuvre;
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
        if (!(object instanceof PromotionOeuvre)) {
            return false;
        }
        PromotionOeuvre other = (PromotionOeuvre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.PromotionOeuvre[ id=" + id + " ]";
    }
    
}
