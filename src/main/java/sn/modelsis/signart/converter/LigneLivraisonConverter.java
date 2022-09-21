package sn.modelsis.signart.converter;

import java.time.LocalDate;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sn.modelsis.signart.Commande;
import sn.modelsis.signart.LigneCommande;
import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.Livraison;
import sn.modelsis.signart.dto.*;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LigneLivraisonConverter {

    @Inject
    LivraisonFacade livraisonFacade;
    @Inject
    LivraisonConverter livraisonConverter;
    @Inject
    LigneCommandeConverter ligneCommandeConverter;
    @Inject
    CommandeConverter commandeConverter;
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    EtatLivraisonFacade etatLivraisonFacade;
    @Inject
    EtatLivraisonFacade etatLivraisonConverter;
    @Inject
    LigneCommandeFacade ligneCommandeFacade;
    @Inject
    AgentFacade agentFacade;
    @Inject
    AgentConverter agentConverter;

    /**
     * Converts an ligneLivraison entity to DTO
     * @param entity
     * @return
     */
    public LigneLivraisonDto entityToDto(LigneLivraison entity,boolean setLigneCommande) {
        LigneLivraisonDto dto = new LigneLivraisonDto();
        dto.setId(entity.getId());
        dto.setIdLigneCommande(entity.getIdLigneCommande().getId());
        dto.setIdLivraison(entity.getIdLivraison().getIdCommande());
        dto.setDateLivraison(entity.getDateLivraison());
        dto.setPreuvePourLivraison(entity.getPreuvePourLivraison());
        if(entity.getIdAgent() != null) {
            dto.setIdAgent(entity.getIdAgent().getId());
            dto.setAgent(agentConverter.entityToDto(entity.getIdAgent()));
        }
        
        //dto.setIdModeLivraison(entity.getIdModeLivraison().getId());
        //dto.setCodeModeLivraison(entity.getIdModeLivraison().getCode());
        //dto.setLibelleModeLivraison(entity.getIdModeLivraison().getLibelle());
        
        dto.setIdEtatLivraison(entity.getIdEtatLivraison().getId());
        dto.setCodeEtatLivraison(entity.getIdEtatLivraison().getCode());
        dto.setLibelleEtatLivraison(entity.getIdEtatLivraison().getLibelle());
        // le ligne qui suit crée une boucle de convertion entre livraison et ligne livraison
        //dto.setLivraison(livraisonConverter.entityToDto(entity.getIdLivraison()));
        if(entity.getIdEtatLivraison() != null){
            ModeLivraisonDto modeliv = new ModeLivraisonDto();
            modeliv.setCode(entity.getIdModeLivraison().getCode());
            modeliv.setId(entity.getIdModeLivraison().getId());
            modeliv.setLibelle(entity.getIdModeLivraison().getLibelle());
            dto.setModeLivraison(modeliv);
        }

       if(entity.getIdEtatLivraison() != null){
           EtatLivraisonDto etat = new EtatLivraisonDto();
           etat.setCode(entity.getIdEtatLivraison().getCode());
           etat.setId(entity.getIdEtatLivraison().getId());
           etat.setLibelle(entity.getIdEtatLivraison().getLibelle());
           dto.setEtatLivraison(etat);
        }
       if(entity.getIdLigneCommande() != null && setLigneCommande){
           dto.setLigneCommande(ligneCommandeConverter.entityToDto(entity.getIdLigneCommande()));
       }
        return dto;
    }

    /**
     * 
     * @param dto
     * @return 
     */
    public LigneLivraison dtoToEntity(LigneLivraisonDto dto) throws SignArtException {
        LigneLivraison entity = new LigneLivraison();
        entity.setId(dto.getId());
        //entity.setIdModeLivraison(modeLivraisonFacade.findByCode(dto.getCodeModeLivraison()));
        entity.setIdEtatLivraison(etatLivraisonFacade.findByCode(dto.getCodeEtatLivraison()));
        entity.setDateLivraison(dto.getDateLivraison());
        entity.setIdLivraison(livraisonFacade.find(dto.getIdLivraison()));
        entity.setIdLigneCommande(ligneCommandeFacade.find(dto.getIdLigneCommande()));
        if(dto.getIdAgent() != null)
            entity.setIdAgent(agentFacade.findById(dto.getIdAgent()));
        return entity;
    }

    public LigneLivraison ligneCommandeToLigneLivraison(LigneCommandeDto dto, Livraison livraison){
        LigneLivraison entity = new LigneLivraison();
        entity.setIdModeLivraison(livraison.getIdModeLivraison());
        entity.setIdEtatLivraison(livraison.getIdEtatLivraison());        
        entity.setDateLivraison(livraison.getDateLivraisonPrevue());
        entity.setIdLivraison(livraisonFacade.find(livraison.getIdCommande()));
        Commande mycom = commandeFacade.findById(livraison.getIdCommande());
        LigneCommande lc = new LigneCommande();
        for (LigneCommande ligneCommande : mycom.getLigneCommandeSet()) {
            if(Objects.equals(ligneCommande.getId(), dto.getId())){
                lc =ligneCommande;
            }
        }
        //mycom.getLigneCommandeSet().stream().filter(ligneC -> ligneC.getId() == dto.getId()).findFirst().get();
        entity.setIdLigneCommande(lc);
        //entity.setIdLigneCommande(ligneCommandeFacade.findByid(dto.getId()));
        //entity.setIdLigneCommande(ligneCommandeConverter.dtoToEntity(dto));
        return entity;
    }
}
