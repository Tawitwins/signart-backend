package sn.modelsis.signart.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class CommandeDto {
    /*item_total: string;
    total: string;
    ship_total: string;
    state: string;
    adjustment_total: string;
    user_id: string;
    created_at: string;
    updated_at: string;
    completed_at: string;
    payment_total: string;
    shipment_state: string;
    payment_state: string;
    email: string;
    special_instructions: string;
    channel: string;
    included_tax_total: string;
    additional_tax_total: string;
    display_included_tax_total: string;
    display_additional_tax_total: string;
    tax_total: string;
    currency: string;
    canceler_id: string;
    total_quantity: string;*/
    private Integer id;
    private String numero;
    private BigDecimal total;
    private BigDecimal montant;

    private Integer nbTotal;
    private BigDecimal totalLivraison;
    private BigDecimal totalTaxes;
    private String codeDevise;
    private Integer idDevise;
    private Integer idEtatCommande;
    private String libelleEtatCommande;
    private Integer idEtatLivraison;
    private String libelleEtatLivraison;
    private Integer idEtatPaiement;
    private String libelleEtatPaiement;
    private String state;
    private Date dateCreation;
    private Date dateModification;
    private Date dateCommande;

    private Date dateFin;
    private Integer idClient;
    private Integer idMagasin;
    private Integer idServiceLivraison;
    private Integer idTarification;
    private boolean risque;
    private String token;
    private Set<AdresseDto> billAdress;
    private Set<AdresseDto> paymentAdress;
    private Set<LigneCommandeDto> lignesCommande;
    private Set<PaiementDto> payments;

    private BigDecimal fraisLivraison;

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

    public Integer getIdEtatCommande() {
        return idEtatCommande;
    }

    public void setIdEtatCommande(Integer idEtatCommande) {
        this.idEtatCommande = idEtatCommande;
    }

    public Integer getIdEtatLivraison() {
        return idEtatLivraison;
    }

    public void setIdEtatLivraison(Integer idEtatLivraison) {
        this.idEtatLivraison = idEtatLivraison;
    }

    public Integer getIdEtatPaiement() {
        return idEtatPaiement;
    }

    public void setIdEtatPaiement(Integer idEtatPaiement) {
        this.idEtatPaiement = idEtatPaiement;
    }

    public String getLibelleEtatCommande() {
        return libelleEtatCommande;
    }

    public void setLibelleEtatCommande(String libelleEtatCommande) {
        this.libelleEtatCommande = libelleEtatCommande;
    }

    public String getLibelleEtatLivraison() {
        return libelleEtatLivraison;
    }

    public void setLibelleEtatLivraison(String libelleEtatLivraison) {
        this.libelleEtatLivraison = libelleEtatLivraison;
    }

    public String getLibelleEtatPaiement() {
        return libelleEtatPaiement;
    }

    public void setLibelleEtatPaiement(String libelleEtatPaiement) {
        this.libelleEtatPaiement = libelleEtatPaiement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Set<LigneCommandeDto> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(Set<LigneCommandeDto> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public Set<PaiementDto> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaiementDto> payments) {
        this.payments = payments;
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

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Integer getIdServiceLivraison() {
        return idServiceLivraison;
    }

    public void setIdServiceLivraison(Integer idServiceLivraison) {
        this.idServiceLivraison = idServiceLivraison;
    }

    public Integer getIdTarification() {
        return idTarification;
    }

    public void setIdTarification(Integer idTarification) {
        this.idTarification = idTarification;
    }

    public BigDecimal getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(BigDecimal fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
}
