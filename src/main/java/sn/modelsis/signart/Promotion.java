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
@Table(name = "Promotion", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findByOeuvre", query = "SELECT p FROM Promotion p Join p.idTechnique o where o.id = :idOeuvre")
    ,
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p")
    , @NamedQuery(name = "Promotion.findById", query = "SELECT p FROM Promotion p WHERE p.id = :id")
    , @NamedQuery(name = "Promotion.findByDateDebut", query = "SELECT p FROM Promotion p WHERE p.dateDebut = :dateDebut")
    , @NamedQuery(name = "Promotion.findByDateFin", query = "SELECT p FROM Promotion p WHERE p.dateFin = :dateFin")
    , @NamedQuery(name = "Promotion.findByTauxReduction", query = "SELECT p FROM Promotion p WHERE p.tauxReduction = :tauxReduction")
    , @NamedQuery(name = "Promotion.findByMontantReduction", query = "SELECT p FROM Promotion p WHERE p.montantReduction = :montantReduction")})
public class Promotion implements Serializable {

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
    @JoinColumn(name = "Technique", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Technique idTechnique;

    public Promotion() {
    }

    public Promotion(Integer id) {
        this.id = id;
    }

    public Promotion(Integer id, Date dateDebut, Date dateFin) {
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

    public Technique getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Technique idTechnique) {
        this.idTechnique = idTechnique;
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
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Promotion[ id=" + id + " ]";
    }
    
}
