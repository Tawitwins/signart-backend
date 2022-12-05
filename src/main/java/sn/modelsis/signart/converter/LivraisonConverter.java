package sn.modelsis.signart.converter;


import java.util.Date;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

import sn.modelsis.signart.Commande;
import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.Livraison;
import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.dto.LivraisonCommandeDto;
import sn.modelsis.signart.dto.LivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LivraisonConverter {

    @Inject
    EtatLivraisonFacade etatLivraisonFacade;
    @Inject
    ModeLivraisonFacade modeLivraisonFacade;
    @Inject
    AdresseFacade adresseFacade;
    @Inject
    LigneLivraisonConverter ligneLivraisonConverter;
    @Inject
    AdresseConverter adresseConverter;
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    ParametrageFacade parametrageFacade;
    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    public LivraisonDto entityToDto(Livraison entity)  {
        LivraisonDto dto = new LivraisonDto();
        if(entity.getDateLivraisonPrevue() != null)
            dto.setDateLivraison(entity.getDateLivraisonPrevue());
        if(entity.getIdCommande() != null)
            dto.setId(entity.getIdCommande());
        if(entity.getIdEtatLivraison().getId() != null)
            dto.setIdEtatLivraison(entity.getIdEtatLivraison().getId());
        if(entity.getIdEtatLivraison().getCode() != null)
            dto.setCodeEtatLivraison(entity.getIdEtatLivraison().getCode());
        if(entity.getIdEtatLivraison().getLibelle() != null)
            dto.setLibelleEtatLivraison(entity.getIdEtatLivraison().getLibelle());
        if(entity.getIdModeLivraison().getId() != null)
            dto.setIdModeLivraison(entity.getIdModeLivraison().getId());
        if(entity.getIdModeLivraison().getCode() != null)
            dto.setCodeModeLivraison(entity.getIdModeLivraison().getCode());
        if(entity.getIdModeLivraison().getLibelle() != null)
            dto.setLibelleModeLivraison(entity.getIdModeLivraison().getLibelle());
        if(adresseConverter.entityToDto(entity.getIdAdresseFacturation()) != null)
            dto.setAdresseFacturation(adresseConverter.entityToDto(entity.getIdAdresseFacturation()));
        if(adresseConverter.entityToDto(entity.getIdAdresseLivraison()) != null)
            dto.setAdresseLivraison(adresseConverter.entityToDto(entity.getIdAdresseLivraison()));
        Set<LigneLivraison> ligneLivraisonSet = entity.getLigneLivraisonSet();
        if (ligneLivraisonSet != null && !ligneLivraisonSet.isEmpty()) {
            Set<LigneLivraisonDto> ligneLivraisonDtoSet = new HashSet<>();
            LigneLivraisonDto ligneLivraisonDto;
            for (LigneLivraison ligneLivraison : ligneLivraisonSet) {
                ligneLivraisonDto = ligneLivraisonConverter.entityToDto(ligneLivraison,false);
                ligneLivraisonDtoSet.add(ligneLivraisonDto);
            }
            dto.setLigneLivraisons(ligneLivraisonDtoSet);
        }
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public Livraison dtoToEntity(LivraisonDto dto) throws SignArtException {
        Livraison entity = new Livraison();
        if(dto.getId() != null)
            entity.setIdCommande(dto.getId());
        entity.setIdEtatLivraison(etatLivraisonFacade.findByCode(dto.getCodeEtatLivraison()));
        entity.setIdModeLivraison(modeLivraisonFacade.findByCode(dto.getCodeModeLivraison()));
        entity.setIdAdresseFacturation(adresseFacade.find(dto.getIdAdresseFacturation()));
        entity.setIdAdresseLivraison(adresseFacade.find(dto.getIdAdresseLivraison()));
        entity.setDateLivraisonPrevue(dto.getDateLivraison());
        Set<LigneLivraison> ligneLivraisonSet = new HashSet();
        LigneLivraison ligneLivraison;
        if (dto.getLigneLivraisons() != null && !dto.getLigneLivraisons().isEmpty()) {
            for (LigneLivraisonDto ligneLivraisonDto : dto.getLigneLivraisons()) {
                ligneLivraison = ligneLivraisonConverter.dtoToEntity(ligneLivraisonDto);
                ligneLivraisonSet.add(ligneLivraison);
            }
            entity.setLigneLivraisonSet(ligneLivraisonSet);
        }
        return entity;
    }

    public Livraison livraisonCommandeDtoToEntity(LivraisonCommandeDto dto) throws SignArtException {

        Livraison entity = new Livraison();
        entity.setIdCommande(dto.getId());
        entity.setIdEtatLivraison(etatLivraisonFacade.findByCode(dto.getCodeEtatLivraison()));
        entity.setIdModeLivraison(modeLivraisonFacade.find(dto.getIdModeLivraison()));
        entity.setIdAdresseFacturation(adresseFacade.find(dto.getIdAdresseLivraison()));
        entity.setIdAdresseLivraison(adresseFacade.find(dto.getIdAdresseLivraison()));
        Commande commande = commandeFacade.find(dto.getId());
        Parametrage delaiLivraison = parametrageFacade.findByParamName("DelaiLivraison");
        Date now= new Date();

        Date dateLivraisonPrevue = new Date(now.getTime() + (1000*60*60*24)*Long.parseLong(delaiLivraison.getValue()));
        //commande.getDateCommande().plusDays(commande.getDelaiLivraison());
        
        entity.setDateLivraisonPrevue(dateLivraisonPrevue);
        
        return entity;
    }

    public Set<LigneLivraison> ligneLivraisonDtoToEntity(Set<LigneCommandeDto> lignesCommande, Livraison liv){

        Set<LigneLivraison> ligneLivraisonSet = new HashSet();
        LigneLivraison ligneLivraison;
        if (lignesCommande != null && !lignesCommande.isEmpty()) {
            for (LigneCommandeDto ligneCommandeDto : lignesCommande) {
                ligneLivraison = ligneLivraisonConverter.ligneCommandeToLigneLivraison(ligneCommandeDto, liv);
                ligneLivraisonSet.add(ligneLivraison);
            }
        }
        return ligneLivraisonSet;
    }
}
