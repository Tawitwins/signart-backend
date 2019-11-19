package sn.modelsis.signart.dto;

import java.util.Date;
import java.util.Set;

public class LivraisonCommandeDto {
    private Integer id;
    private Date dateLivraisonPrevue;
    private Date dateLivraisonEffective;
    private Integer idAdresseLivraison;
    private Integer idModeLivraison;
    private String codeEtatLivraison;
    private Set<LigneCommandeDto> lignesCommande;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAdresseLivraison() {
        return idAdresseLivraison;
    }

    public void setIdAdresseLivraison(Integer idAdresseLivraison) {
        this.idAdresseLivraison = idAdresseLivraison;
    }

    public Integer getIdModeLivraison() {
        return idModeLivraison;
    }

    public void setIdModeLivraison(Integer idModeLivraison) {
        this.idModeLivraison = idModeLivraison;
    }

    public String getCodeEtatLivraison() {
        return codeEtatLivraison;
    }

    public void setCodeEtatLivraison(String codeEtatLivraison) {
        this.codeEtatLivraison = codeEtatLivraison;
    }

    public Set<LigneCommandeDto> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(Set<LigneCommandeDto> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public Date getDateLivraisonEffective() {
        return dateLivraisonEffective;
    }

    public void setDateLivraisonEffective(Date dateLivraisonEffective) {
        this.dateLivraisonEffective = dateLivraisonEffective;
    }

    public Date getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public void setDateLivraisonPrevue(Date dateLivraisonPrevue) {
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }
}