package sn.modelsis.signart.converter;

import sn.modelsis.signart.ServiceLivraison;
import sn.modelsis.signart.Tarification;
import sn.modelsis.signart.dto.ServiceLivraisonDto;
import sn.modelsis.signart.dto.TarificationDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ServiceLivraisonFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMENGUEO
 */
@Stateless
public class TarificationConverter {

    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;

    public TarificationDto entityToDto(Tarification entity) throws SignArtException {
        TarificationDto dto = new TarificationDto();
        dto.setId(entity.getId());
        dto.setDelaiLivraison(entity.getDelaiLivraison());
        dto.setFraisAssurance(entity.getFraisAssurance());
        dto.setFraisLivraison(entity.getFraisLivraison());
        dto.setZone(entity.getZone());
        dto.setDistance(entity.getDistance());
        dto.setAccessibiliteZone(entity.getAccessibiliteZone());
        dto.setCategorieDistance(entity.getCategorieDistance());
        if(entity.getIdServiceLivraison() != null)
            dto.setIdServiceLivraison(entity.getIdServiceLivraison().getId());
        return dto;
    }

    public Tarification dtoToEntity(TarificationDto dto) throws SignArtException {
        Tarification entity = new Tarification();
        entity.setId(dto.getId());
        entity.setDelaiLivraison(dto.getDelaiLivraison());
        entity.setFraisAssurance(dto.getFraisAssurance());
        entity.setFraisLivraison(dto.getFraisLivraison());
        entity.setZone(dto.getZone());
        entity.setDistance(dto.getDistance());
        entity.setAccessibiliteZone(dto.getAccessibiliteZone());
        entity.setCategorieDistance(dto.getCategorieDistance());
        entity.setIdServiceLivraison(serviceLivraisonFacade.findById(dto.getIdServiceLivraison()));
        return entity;
    }
}
