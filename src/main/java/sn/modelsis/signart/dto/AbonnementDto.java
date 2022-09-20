/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author Pendaaa
 */
public class AbonnementDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer idAbonne;
    private Integer idTerminal;
    private Integer idDelai;
    private Integer idListeSelection;
    private Integer montantPaiement;
    private String precisions;
    private Integer etatAbonnement;


    public AbonnementDto() {
    }

    public AbonnementDto(Integer id, Integer idAbonne, Integer idTerminal, Integer idDelai, Integer idListeSelection, Integer montantPaiement, Integer etatAbonnement) {
        this.id = id;
        this.idAbonne = idAbonne;
        this.idTerminal = idTerminal;
        this.idDelai = idDelai;
        this.idListeSelection = idListeSelection;
        this.montantPaiement = montantPaiement;
        this.etatAbonnement = etatAbonnement;
    }

    public AbonnementDto(Integer idAbonne, Integer idTerminal, Integer idDelai, Integer idListeSelection, Integer montantPaiement, Integer etatAbonnement) {
        this.idAbonne = idAbonne;
        this.idTerminal = idTerminal;
        this.idDelai = idDelai;
        this.idListeSelection = idListeSelection;
        this.montantPaiement = montantPaiement;
        this.etatAbonnement = etatAbonnement;
    }
    
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(Integer idAbonne) {
        this.idAbonne = idAbonne;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }
    
    


    public Integer getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(Integer idTerminal) {
        this.idTerminal = idTerminal;
    }

    public Integer getIdDelai() {
        return idDelai;
    }

    public void setIdDelai(Integer idDelai) {
        this.idDelai = idDelai;
    }

    public Integer getIdListeSelection() {
        return idListeSelection;
    }

    public void setIdListeSelection(Integer idListeSelection) {
        this.idListeSelection = idListeSelection;
    }

    public Integer getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(Integer montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public Integer getEtatAbonnement() {
        return etatAbonnement;
    }

    public void setEtatAbonnement(Integer etatAbonnement) {
        this.etatAbonnement = etatAbonnement;
    }  
}
