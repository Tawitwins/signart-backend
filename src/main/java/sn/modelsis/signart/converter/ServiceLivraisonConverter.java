package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.ServiceLivraison;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.ServiceLivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMENGUEO
 */
@Stateless
public class ServiceLivraisonConverter {

    @Inject
    UtilisateurFacade utilisateurFacade;
    public ServiceLivraisonDto entityToDto(ServiceLivraison entity) throws SignArtException {
        ServiceLivraisonDto dto = new ServiceLivraisonDto();
        dto.setId(entity.getId());
        dto.setAdresse(entity.getAdresse());
        dto.setEmail(entity.getEmail());
        dto.setNom(entity.getNom());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }

    public ServiceLivraison dtoToEntity(ServiceLivraisonDto dto) {
        ServiceLivraison entity = new ServiceLivraison();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setEmail(dto.getEmail());
        entity.setNom(dto.getNom());
        entity.setTelephone(dto.getTelephone());
        return entity;
    }
}
