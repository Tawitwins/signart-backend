package sn.modelsis.signart.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        dto.setDateLivraison(entity.getDateLivraisonPrevue());
        dto.setId(entity.getIdCommande());
        dto.setIdEtatLivraison(entity.getIdEtatLivraison().getId());
        dto.setCodeEtatLivraison(entity.getIdEtatLivraison().getCode());
        dto.setLibelleEtatLivraison(entity.getIdEtatLivraison().getLibelle());
        dto.setIdModeLivraison(entity.getIdModeLivraison().getId());
        dto.setCodeModeLivraison(entity.getIdModeLivraison().getCode());
        dto.setLibelleModeLivraison(entity.getIdModeLivraison().getLibelle());
        dto.setAdresseFacturation(adresseConverter.entityToDto(entity.getIdAdresseFacturation()));
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
        LocalDate dateLivraisonPrevue = LocalDate.now().plusDays(Long.parseLong(delaiLivraison.getValue()));
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
