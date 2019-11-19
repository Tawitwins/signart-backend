package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.dto.ClientDto;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.facade.EtatClientFacade;
import sn.modelsis.signart.facade.PaysFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class ClientConverter {

    @Inject
    EtatClientFacade etatClientFacade;
    @Inject
    PaysFacade paysFacade;
    @Inject
    UtilisateurFacade utilisateurFacade;

    public ClientDto entityToDto(Client entity) {
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setEtatClient(entity.getIdEtatClient().getLibelle());
        dto.setIdEtatClient(entity.getIdEtatClient().getId());
        dto.setPays(entity.getIdPays().getLibelle());
        dto.setIdUser(entity.getIdUser().getId());
        dto.setSexe(entity.getSexe());
        dto.setVille(entity.getVille());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }

    public Client dtoToEntity(ClientDto dto) {
        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setIdEtatClient(etatClientFacade.find(dto.getIdEtatClient()));
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setIdPays(paysFacade.find(dto.getIdPays()));
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        entity.setVille(dto.getVille());
        entity.setSexe(dto.getSexe());
        entity.setTelephone(dto.getTelephone());
        entity.setIdUser(utilisateurFacade.find(dto.getIdUser()));
        return entity;
    }
}
