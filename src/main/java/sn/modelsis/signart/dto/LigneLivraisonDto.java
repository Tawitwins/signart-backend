package sn.modelsis.signart.dto;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.LigneCommande;

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

    private LivraisonDto livraison;
    private LocalDate dateLivraison;
    private Integer idEtatLivraison;
    private EtatLivraisonDto etatLivraison;


    private LigneCommandeDto ligneCommande;
    private String codeEtatLivraison;
    private String libelleEtatLivraison;
    private Integer idModeLivraison;
    private ModeLivraisonDto modeLivraison;
    private String codeModeLivraison;
    private String libelleModeLivraison;
    private Integer idAgent;
    private AgentDto agent;
    private String preuvePourLivraison;

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
    public Integer getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Integer idAgent) {
        this.idAgent = idAgent;
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
    public EtatLivraisonDto getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(EtatLivraisonDto etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public LigneCommandeDto getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommandeDto ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public ModeLivraisonDto getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(ModeLivraisonDto modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    public AgentDto getAgent() {
        return agent;
    }

    public void setAgent(AgentDto agent) {
        this.agent = agent;
    }

    public LivraisonDto getLivraison() {
        return livraison;
    }

    public void setLivraison(LivraisonDto livraison) {
        this.livraison = livraison;
    }

    public String getPreuvePourLivraison() {
        return preuvePourLivraison;
    }

    public void setPreuvePourLivraison(String preuvePourLivraison) {
        this.preuvePourLivraison = preuvePourLivraison;
    }

}
