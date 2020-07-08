package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.MessagesTchats;
import sn.modelsis.signart.dto.MessagesTchatsDto;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MessagesTchatsConverter {

    @Inject
    UtilisateurFacade utilisateurFacade;

    public MessagesTchatsDto entityToDto(MessagesTchats entity) {
        MessagesTchatsDto dto = new MessagesTchatsDto();
        dto.setIdMsg(entity.getIdMsg());
        dto.setIdSender(entity.getIdSender());
        dto.setIdReceiver(entity.getIdReceiver());
        dto.setUsername(entity.getUsername());
        dto.setContenu(entity.getContenu());
        //dto.setContenu(new String(Base64.decodeBase64(entity.getContenu())));
        if(dto.getIdReceiver() != null)
            dto.setProfilReceiver(utilisateurFacade.findById(dto.getIdReceiver()).getUserType());
        if(dto.getIdSender() != null)
            dto.setProfilSender(utilisateurFacade.findById(dto.getIdSender()).getUserType());
        dto.setFilename(entity.getFilename());
        dto.setUrlFile(entity.getUrlfile());
        dto.setMsgFile(entity.getMsgFile());
        dto.setMsgStateSender(entity.getMsgStateSender());
        dto.setMsgStateReceiver(entity.getMsgStateReceiver());
        dto.setDateEnvoi(entity.getDateEnvoi());
        dto.setShowDate(entity.getShowDate());
        return dto;
    }

    public MessagesTchats dtoToEntity(MessagesTchatsDto dto) {
        MessagesTchats entity = new MessagesTchats();
        entity.setIdMsg(dto.getIdMsg());
        entity.setIdSender(dto.getIdSender());
        entity.setIdReceiver(dto.getIdReceiver());
        entity.setUsername(dto.getUsername());
        entity.setContenu(dto.getContenu());
        //entity.setContenu(Base64.encodeBase64String(dto.getContenu().getBytes()));
//        entity.setProfilSender(dto.getProfilSender());
//        entity.setProfilReceiver(dto.getProfilReceiver());
        entity.setFilename(dto.getFilename());
        entity.setUrlFile(dto.getUrlfile());
        entity.setMsgFile(dto.getMsgFile());
        entity.setMsgStateSender(dto.getMsgStateSender());
        entity.setMsgStateReceiver(dto.getMsgStateReceiver());
        entity.setDateEnvoi(dto.getDateEnvoi());
        return entity;
    }
}
