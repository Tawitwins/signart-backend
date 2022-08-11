package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Magasin;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.MagasinDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EmailFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MagasinConverter {

    @Inject EmailFacade emailFacade;
    public MagasinDto entityToDto(Magasin entity) throws SignArtException {
        MagasinDto dto = new MagasinDto();
        dto.setId(entity.getId());
        dto.setAdresse(entity.getAdresse());
        dto.setNom(entity.getNom());
        dto.setNomResp(entity.getNomResp());
        dto.setEmailResp(entity.getEmailResp());
        dto.setTelephoneResp(entity.getTelephoneResp());
        return dto;
    }

    public Magasin dtoToEntity(MagasinDto dto) {
        Magasin entity = new Magasin();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setNom(dto.getNom());
        entity.setNomResp(dto.getNomResp());
        entity.setEmailResp(dto.getEmailResp());
        entity.setTelephoneResp(dto.getTelephoneResp());
        return entity;
    }
}
