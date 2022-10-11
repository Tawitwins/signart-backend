package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNMBENGUEO
 */
@Entity
@Table(name = "ParametreAlgo", catalog = "signart", schema = "dbo")
@NamedQueries({
        @NamedQuery(name = "ParametreAlgo.findAll", query = "SELECT p FROM Panier p")
        , @NamedQuery(name = "ParametreAlgo.findById", query = "SELECT p FROM Panier p WHERE p.id = :id")
        , @NamedQuery(name = "ParametreAlgo.findByNiveau", query = "SELECT p FROM ParametreAlgo p WHERE p.niveau = :niveau")})
public class ParametreAlgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "niveau")
    private String niveau;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "note")
    private Integer note;
    @Column(name = "baseNote")
    private Integer baseNote;
    @Column(name = "pourcentReduction")
    private Integer pourcentReduction;
    @Column(name = "borneSup")
    private Float borneSup;
    @Column(name = "borneInf")
    private Float borneInf;


    @JoinColumn(name = "idCoefficientParam", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private  CoefficientParametrage coefficientParam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getBaseNote() {
        return baseNote;
    }

    public void setBaseNote(Integer baseNote) {
        this.baseNote = baseNote;
    }

    public Integer getPourcentReduction() {
        return pourcentReduction;
    }

    public void setPourcentReduction(Integer pourcentReduction) {
        this.pourcentReduction = pourcentReduction;
    }

    public Float getBorneSup() {
        return borneSup;
    }

    public void setBorneSup(Float borneSup) {
        this.borneSup = borneSup;
    }

    public Float getBorneInf() {
        return borneInf;
    }

    public void setBorneInf(Float borneInf) {
        this.borneInf = borneInf;
    }

    public ParametreAlgo() {
    }

    public CoefficientParametrage getCoefficientParam() {
        return coefficientParam;
    }

    public void setCoefficientParam(CoefficientParametrage coefficientParam) {
        this.coefficientParam = coefficientParam;
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
        if (!(object instanceof ParametreAlgo)) {
            return false;
        }
        ParametreAlgo other = (ParametreAlgo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ParametreAlgo[ id=" + id + " ]";
    }

}
