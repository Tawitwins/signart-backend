package sn.modelsis.signart.dto;

import sn.modelsis.signart.LigneCommande;


/**
 *
 * @author SNMBENGUO
 */
public class LigneComLivPaieDto {
    private LigneCommandeDto ligneCommandeDto;
    private LigneLivraisonDto ligneLivraisonDto;
    private LignePaiementDto lignePaiementDto;


    public LigneComLivPaieDto() {
    }
    public LigneComLivPaieDto(LigneCommandeDto ligneCommandeDto, LigneLivraisonDto ligneLivraisonDto, LignePaiementDto lignePaiementDto) {
        this.ligneCommandeDto = ligneCommandeDto;
        this.ligneLivraisonDto = ligneLivraisonDto;
        this.lignePaiementDto = lignePaiementDto;
    }

    public LigneCommandeDto getLigneCommandeDto() {
        return ligneCommandeDto;
    }

    public void setLigneCommandeDto(LigneCommandeDto ligneCommandeDto) {
        this.ligneCommandeDto = ligneCommandeDto;
    }

    public LigneLivraisonDto getLigneLivraisonDto() {
        return ligneLivraisonDto;
    }

    public void setLigneLivraisonDto(LigneLivraisonDto ligneLivraisonDto) {
        this.ligneLivraisonDto = ligneLivraisonDto;
    }

    public LignePaiementDto getLignePaiementDto() {
        return lignePaiementDto;
    }

    public void setLignePaiementDto(LignePaiementDto lignePaiementDto) {
        this.lignePaiementDto = lignePaiementDto;
    }

}
