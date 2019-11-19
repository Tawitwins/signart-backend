package sn.modelsis.signart.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class PanierDto {
    private Integer id;
    private String numero;
    private BigDecimal total;
    private Integer nbTotal;
    private BigDecimal totalLivraison;
    private BigDecimal totalTaxes;
    private String codeDevise;
    private Integer idDevise;
    private Integer idEtatPanier;
    private String libelleEtatPanier;
    //private Integer idEtatLivraison;
    //private String libelleEtatLivraison;
    //private Integer idEtatPaiement;
    //private String libelleEtatPaiement;
    private Date dateCreation;
    private Date dateModification;
    private Date dateFin;
    private Integer idClient;
    private boolean risque;
    private String token;
    private Set<AdresseDto> billAdress;
    private Set<AdresseDto> paymentAdress;
    private Set<LignePanierDto> lignesPanier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getNbTotal() {
        return nbTotal;
    }

    public void setNbTotal(Integer nbTotal) {
        this.nbTotal = nbTotal;
    }

    public BigDecimal getTotalLivraison() {
        return totalLivraison;
    }

    public void setTotalLivraison(BigDecimal totalLivraison) {
        this.totalLivraison = totalLivraison;
    }

    public String getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
    }

    public Integer getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public Integer getIdEtatPanier() {
        return idEtatPanier;
    }

    public void setIdEtatPanier(Integer idEtatPanier) {
        this.idEtatPanier = idEtatPanier;
    }

    public String getLibelleEtatPanier() {
        return libelleEtatPanier;
    }

    public void setLibelleEtatPanier(String libelleEtatPanier) {
        this.libelleEtatPanier = libelleEtatPanier;
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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public boolean isRisque() {
        return risque;
    }

    public void setRisque(boolean risque) {
        this.risque = risque;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<AdresseDto> getBillAdress() {
        return billAdress;
    }

    public void setBillAdress(Set<AdresseDto> billAdress) {
        this.billAdress = billAdress;
    }

    public Set<AdresseDto> getPaymentAdress() {
        return paymentAdress;
    }

    public void setPaymentAdress(Set<AdresseDto> paymentAdress) {
        this.paymentAdress = paymentAdress;
    }

    public Set<LignePanierDto> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(Set<LignePanierDto> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
}
