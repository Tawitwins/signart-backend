package sn.modelsis.signart.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class LignePaiementDto {

    private Integer id;
    private Date datePaiement;
    private BigDecimal montant;
    private Integer idModePaiement;
    private String codeModePaiement;
    private String libelleModePaiement;
    private Integer idPaiement;
    private LigneCommandeDto ligneCommande;

    private Integer idEtatPaiement;

    private String codeEtatPaiement;

    private String libelleEtatPaiement;

    private String stringPaymentDate;

    private Integer idPaymentDetails;
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

    public Integer getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(Integer idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public String getCodeModePaiement() {
        return codeModePaiement;
    }

    public void setCodeModePaiement(String codeModePaiement) {
        this.codeModePaiement = codeModePaiement;
    }

    public String getLibelleModePaiement() {
        return libelleModePaiement;
    }

    public void setLibelleModePaiement(String libelleModePaiement) {
        this.libelleModePaiement = libelleModePaiement;
    }

    public Integer getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }
    public LigneCommandeDto getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommandeDto ligneCommande) {
        this.ligneCommande = ligneCommande;
    }
    public Integer getIdEtatPaiement() {
        return idEtatPaiement;
    }

    public void setIdEtatPaiement(Integer idEtatPaiement) {
        this.idEtatPaiement = idEtatPaiement;
    }

    public String getCodeEtatPaiement() {
        return codeEtatPaiement;
    }

    public void setCodeEtatPaiement(String codeEtatPaiement) {
        this.codeEtatPaiement = codeEtatPaiement;
    }

    public String getLibelleEtatPaiement() {
        return libelleEtatPaiement;
    }

    public void setLibelleEtatPaiement(String libelleEtatPaiement) {
        this.libelleEtatPaiement = libelleEtatPaiement;
    }

    public Integer getIdPaymentDetails() {
        return idPaymentDetails;
    }

    public void setIdPaymentDetails(Integer idPaymentDetails) {
        this.idPaymentDetails = idPaymentDetails;
    }

    public String getStringPaymentDate() {
        return stringPaymentDate;
    }

    public void setStringPaymentDate(String stringPaymentDate) {
        this.stringPaymentDate = stringPaymentDate;
    }
}
