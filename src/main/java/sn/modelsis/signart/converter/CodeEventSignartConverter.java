package sn.modelsis.signart.converter;

import sn.modelsis.signart.CodeEventSignart;
import sn.modelsis.signart.EvenementSignart;
import sn.modelsis.signart.dto.CodeEventSignartDto;
import sn.modelsis.signart.dto.EvenementSignartDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMENGUEO
 */
@Stateless
public class CodeEventSignartConverter {

    @Inject
    EvenementSignartFacade evenementSignartFacade;
    @Inject
    ArtisteFacade artisteFacade;
    @Inject
    ProfilFacade profilFacade;
    @Inject
    MagasinFacade magasinFacade;
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;
    public CodeEventSignartDto entityToDto(CodeEventSignart entity){
        CodeEventSignartDto dto = new CodeEventSignartDto();
        dto.setId(entity.getId());
        dto.setDateCreation(entity.getDateCreation());
        dto.setDateOfficielle(entity.getDateOfficielle());
        dto.setPrixCodeEvent(entity.getPrixCodeEvent());
        dto.setStatus(entity.getStatus());
        dto.setCode(entity.getCode());
        dto.setProprietaire(dto.getProprietaire());
        if(entity.getIdEvenementSignart() != null)
            dto.setIdEvenementSignart(entity.getIdEvenementSignart().getId());

        return dto;
    }

    public CodeEventSignart dtoToEntity(CodeEventSignartDto dto)  throws SignArtException {
        CodeEventSignart entity = new CodeEventSignart();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDateCreation(dto.getDateCreation());
        entity.setDateOfficielle(entity.getDateOfficielle());
        entity.setStatus(entity.getStatus());
        entity.setPrixCodeEvent(entity.getPrixCodeEvent());
        entity.setProprietaire(dto.getProprietaire());
        entity.setIdEvenementSignart(evenementSignartFacade.findById(dto.getIdEvenementSignart()));
        return entity;
    }
}
