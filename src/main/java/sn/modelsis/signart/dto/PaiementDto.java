package sn.modelsis.signart.dto;

import sn.modelsis.signart.LigneCommande;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class PaiementDto {
    private Integer id;
    private BigDecimal montant;
    private Integer idCommande;
    private Integer idModePaiement;
    private Integer idEtatPaiement;
    private String codeModePaiement;
    private String codeEtatPaiement;
    private String libelleModePaiement;
    private String libelleEtatPaiement;
    private Date datePaiement;
    private Date dateCreation;
    private Date dateModification;
    private Set<LignePaiementDto> lignePaiements;

    private String stringPaymentDate;

    private String stringCreationDate;

    private String stringModificationDate;

    private BigDecimal montantTotal;

    private CommandeDto commande;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }
    public Integer getIdCommande() {
        return idCommande;
    }
    public void setIdModePaiement(Integer idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public Integer getIdEtatPaiement() {
        return idEtatPaiement;
    }

    public void setIdEtatPaiement(Integer idEtatPaiement) {
        this.idEtatPaiement = idEtatPaiement;
    }

    public String getCodeModePaiement() {
        return codeModePaiement;
    }

    public void setCodeModePaiement(String codeModePaiement) {
        this.codeModePaiement = codeModePaiement;
    }

    public String getCodeEtatPaiement() {
        return codeEtatPaiement;
    }

    public void setCodeEtatPaiement(String codeEtatPaiement) {
        this.codeEtatPaiement = codeEtatPaiement;
    }

    public String getLibelleModePaiement() {
        return libelleModePaiement;
    }

    public void setLibelleModePaiement(String libelleModePaiement) {
        this.libelleModePaiement = libelleModePaiement;
    }

    public String getLibelleEtatPaiement() {
        return libelleEtatPaiement;
    }

    public void setLibelleEtatPaiement(String libelleEtatPaiement) {
        this.libelleEtatPaiement = libelleEtatPaiement;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public String getStringPaymentDate() {
        return stringPaymentDate;
    }

    public void setStringPaymentDate(String stringPaymentDate) {
        this.stringPaymentDate = stringPaymentDate;
    }

    public String getStringCreationDate() {
        return stringCreationDate;
    }

    public void setStringCreationDate(String stringCreationDate) {
        this.stringCreationDate = stringCreationDate;
    }

    public String getStringModificationDate() {
        return stringModificationDate;
    }

    public void setStringModificationDate(String stringModificationDate) {
        this.stringModificationDate = stringModificationDate;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Set<LignePaiementDto> getLignePaiements() {
        return lignePaiements;
    }

    public void setLignePaiements(Set<LignePaiementDto> lignePaiements) {
        this.lignePaiements = lignePaiements;
    }

    public CommandeDto getCommande() {
        return commande;
    }

    public void setCommande(CommandeDto commande) {
        this.commande = commande;
    }
}
