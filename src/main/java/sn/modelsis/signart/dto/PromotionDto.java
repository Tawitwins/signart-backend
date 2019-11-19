package sn.modelsis.signart.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class PromotionDto {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private Integer tauxReduction;
    private BigDecimal montantReduction;
    private Integer idOeuvre;
    private Integer idSousTechnique;

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

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Integer getIdSousTechnique() {
        return idSousTechnique;
    }

    public void setIdSousTechnique(Integer idSousTechnique) {
        this.idSousTechnique = idSousTechnique;
    }
}
