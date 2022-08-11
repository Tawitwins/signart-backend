package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Email;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EmailFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMENGUEO
 */
@Stateless
public class AgentConverter {

    @Inject
    UtilisateurFacade utilisateurFacade;
    public AgentDto entityToDto(Agent entity){
        AgentDto dto = new AgentDto();
        dto.setId(entity.getId());
        dto.setAdresse(entity.getAdresse());
        dto.setEmail(entity.getEmail());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setTelephone(entity.getTelephone());
        dto.setSurnom(entity.getSurnom());
        dto.setVille(entity.getVille());
        if(entity.getIdUser() != null)
            dto.setIdUser(entity.getIdUser().getId());
        return dto;
    }

    public Agent dtoToEntity(AgentDto dto)  throws SignArtException {
        Agent entity = new Agent();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setEmail(dto.getEmail());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setSurnom(dto.getSurnom());
        entity.setVille(dto.getVille());
        entity.setTelephone(dto.getTelephone());
        entity.setIdUser(utilisateurFacade.findById((dto.getIdUser())));
        return entity;
    }
}
