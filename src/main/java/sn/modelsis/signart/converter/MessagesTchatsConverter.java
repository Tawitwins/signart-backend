package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.MessagesTchats;
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.dto.ClientDto;
import sn.modelsis.signart.dto.MessagesTchatsDto;
import sn.modelsis.signart.dto.VisiteurDto;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.facade.EtatClientFacade;
import sn.modelsis.signart.facade.PaysFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class MessagesTchatsConverter {


    public MessagesTchatsDto entityToDto(MessagesTchats entity) {
        MessagesTchatsDto dto = new MessagesTchatsDto();
        dto.setIdMsg(entity.getIdMsg());
        dto.setIdSender(entity.getIdSender());
        dto.setIdReceiver(entity.getIdReceiver());
        dto.setUsername(entity.getUsername());
        dto.setContenu(entity.getContenu());
        dto.setProfilSender(entity.getProfilSender());
        dto.setFilename(entity.getFilename());
        dto.setUrlFile(entity.getUrlfile());
        dto.setMsgFile(entity.getMsgFile());
        dto.setMsgStateSender(entity.getMsgStateSender());
        dto.setMsgStateReceiver(entity.getMsgStateReceiver());
        dto.setDateEnvoi(entity.getDateEnvoi());
        return dto;
    }

    public MessagesTchats dtoToEntity(MessagesTchatsDto dto) {
        MessagesTchats entity = new MessagesTchats();
        entity.setIdMsg(dto.getIdMsg());
        entity.setIdSender(dto.getIdSender());
        entity.setIdReceiver(dto.getIdReceiver());
        entity.setUsername(dto.getUsername());
        entity.setContenu(dto.getContenu());
        entity.setProfilSender(dto.getProfilSender());
        entity.setFilename(dto.getFilename());
        entity.setUrlFile(dto.getUrlfile());
        entity.setMsgFile(dto.getMsgFile());
        entity.setMsgStateSender(dto.getMsgStateSender());
        entity.setMsgStateReceiver(dto.getMsgStateReceiver());
        entity.setDateEnvoi(dto.getDateEnvoi());
        return entity;
    }
}
