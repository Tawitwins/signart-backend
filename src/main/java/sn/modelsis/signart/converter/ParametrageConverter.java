package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.ParametrageDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMENGUEO
 */
@Stateless
public class ParametrageConverter {

    @Inject
    UtilisateurFacade utilisateurFacade;
    public ParametrageDto entityToDto(Parametrage entity) throws SignArtException {
        ParametrageDto dto = new ParametrageDto();
        dto.setStatut(entity.getStatut());
        dto.setParamName(entity.getParamName());
        dto.setValue(entity.getValue());
        return dto;
    }

    public Parametrage dtoToEntity(ParametrageDto dto)  throws SignArtException {
        Parametrage entity = new Parametrage();
        entity.setStatut(dto.getStatut());
        entity.setValue(dto.getValue());
        entity.setParamName(dto.getParamName());
        return entity;
    }
}
