package sn.modelsis.signart.dto;

import sn.modelsis.signart.EtatPaiement;

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


}
