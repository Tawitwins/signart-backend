package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Email;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EmailFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class EmailConverter {

    @Inject EmailFacade emailFacade;
    public EmailDto entityToDto(Email entity) throws SignArtException {
        EmailDto dto = new EmailDto();
        dto.setId(entity.getId());
        dto.setTo(entity.getTo());
        dto.setObjet(entity.getObjet());
        dto.setContent(entity.getContent());
        dto.setDateEnvoi(entity.getDateEnvoi());
        dto.setNbrEmail(emailFacade.findByTo(entity.getTo()).size());
        //dto.setPj(entity.getPj());

        return dto;
    }

    public Email dtoToEntity(EmailDto dto) {
        Email entity = new Email();
        entity.setId(dto.getId());
        entity.setTo(dto.getTo());
        entity.setObjet(dto.getObjet());
        entity.setContent(dto.getContent());
        entity.setDateEnvoi(dto.getDateEnvoi());
        //entity.setPj(dto.getPj());
        return entity;
    }
}
