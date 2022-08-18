package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Email;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

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
    @Inject
    ProfilFacade profilFacade;
    @Inject
    MagasinFacade magasinFacade;
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;
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
        if(entity.getIdMagasin()!=null)
            dto.setIdMagasin(entity.getIdMagasin().getId());
        if(entity.getIdServiceLivraison()!=null)
            dto.setIdServiceLivraison(entity.getIdServiceLivraison().getId());
        if(entity.getIdProfil()!=null)
            dto.setIdProfil(entity.getIdProfil().getId());
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
        entity.setIdProfil(profilFacade.find(dto.getIdProfil()));
        entity.setIdMagasin(magasinFacade.find(dto.getIdMagasin()));
        entity.setIdServiceDeLivraison(serviceLivraisonFacade.find(dto.getIdServiceLivraison()));
        return entity;
    }
}
