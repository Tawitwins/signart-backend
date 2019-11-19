package sn.modelsis.signart.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class LigneLivraisonDto {
    private Integer id;
    private Integer idLigneCommande;
    private Integer idLivraison;
    private LocalDate dateLivraison;
    private Integer idEtatLivraison;
    private String codeEtatLivraison;
    private String libelleEtatLivraison;
    private Integer idModeLivraison;
    private String codeModeLivraison;
    private String libelleModeLivraison;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(Integer idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public Integer getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Integer getIdEtatLivraison() {
        return idEtatLivraison;
    }

    public void setIdEtatLivraison(Integer idEtatLivraison) {
        this.idEtatLivraison = idEtatLivraison;
    }

    public String getCodeEtatLivraison() {
        return codeEtatLivraison;
    }

    public void setCodeEtatLivraison(String codeEtatLivraison) {
        this.codeEtatLivraison = codeEtatLivraison;
    }

    public String getLibelleEtatLivraison() {
        return libelleEtatLivraison;
    }

    public void setLibelleEtatLivraison(String libelleEtatLivraison) {
        this.libelleEtatLivraison = libelleEtatLivraison;
    }

    public Integer getIdModeLivraison() {
        return idModeLivraison;
    }

    public void setIdModeLivraison(Integer idModeLivraison) {
        this.idModeLivraison = idModeLivraison;
    }

    public String getCodeModeLivraison() {
        return codeModeLivraison;
    }

    public void setCodeModeLivraison(String codeModeLivraison) {
        this.codeModeLivraison = codeModeLivraison;
    }

    public String getLibelleModeLivraison() {
        return libelleModeLivraison;
    }

    public void setLibelleModeLivraison(String libelleModeLivraison) {
        this.libelleModeLivraison = libelleModeLivraison;
    }
    
}
