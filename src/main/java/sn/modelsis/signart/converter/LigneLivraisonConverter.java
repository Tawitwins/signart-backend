package sn.modelsis.signart.converter;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.Livraison;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.dto.LivraisonCommandeDto;
import sn.modelsis.signart.facade.EtatLivraisonFacade;
import sn.modelsis.signart.facade.LigneCommandeFacade;
import sn.modelsis.signart.facade.ModeLivraisonFacade;
import sn.modelsis.signart.facade.LivraisonFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LigneLivraisonConverter {

    @Inject
    LivraisonFacade livraisonFacade;
    @Inject
    ModeLivraisonFacade modeLivraisonFacade;
    @Inject
    EtatLivraisonFacade etatLivraisonFacade;
    @Inject
    LigneCommandeFacade ligneCommandeFacade;

    /**
     * Converts an ligneLivraison entity to DTO
     * @param entity
     * @return
     */
    public LigneLivraisonDto entityToDto(LigneLivraison entity) {
        LigneLivraisonDto dto = new LigneLivraisonDto();
        dto.setId(entity.getId());
        dto.setIdLigneCommande(entity.getIdLigneCommande().getId());
        dto.setIdLivraison(entity.getIdLivraison().getIdCommande());
        dto.setDateLivraison(entity.getDateLivraison());
        
        dto.setIdModeLivraison(entity.getIdModeLivraison().getId());
        dto.setCodeModeLivraison(entity.getIdModeLivraison().getCode());
        dto.setLibelleModeLivraison(entity.getIdModeLivraison().getLibelle());
        
        dto.setIdEtatLivraison(entity.getIdEtatLivraison().getId());
        dto.setCodeEtatLivraison(entity.getIdEtatLivraison().getCode());
        dto.setLibelleEtatLivraison(entity.getIdEtatLivraison().getLibelle());
        return dto;
    }

    /**
     * 
     * @param dto
     * @return 
     */
    public LigneLivraison dtoToEntity(LigneLivraisonDto dto) {
        LigneLivraison entity = new LigneLivraison();
        entity.setId(dto.getId());
        entity.setIdModeLivraison(modeLivraisonFacade.findByCode(dto.getCodeModeLivraison()));
        entity.setIdEtatLivraison(etatLivraisonFacade.findByCode(dto.getCodeEtatLivraison()));
        entity.setDateLivraison(dto.getDateLivraison());
        entity.setIdLivraison(livraisonFacade.find(dto.getIdLivraison()));
        entity.setIdLigneCommande(ligneCommandeFacade.find(dto.getIdLigneCommande()));
        return entity;
    }

    public LigneLivraison ligneCommandeToLigneLivraison(LigneCommandeDto dto, Livraison livraison){
        LigneLivraison entity = new LigneLivraison();
        entity.setIdModeLivraison(livraison.getIdModeLivraison());
        entity.setIdEtatLivraison(livraison.getIdEtatLivraison());        
        entity.setDateLivraison(livraison.getDateLivraisonPrevue());
        entity.setIdLivraison(livraisonFacade.find(livraison.getIdCommande()));
        entity.setIdLigneCommande(ligneCommandeFacade.find(dto.getId()));
        return entity;
    }
}
