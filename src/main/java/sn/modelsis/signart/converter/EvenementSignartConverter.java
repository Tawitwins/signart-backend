package sn.modelsis.signart.converter;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.EvenementSignart;
import sn.modelsis.signart.dto.AgentDto;
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
public class EvenementSignartConverter {

    @Inject
    UtilisateurFacade utilisateurFacade;
    @Inject
    ArtisteFacade artisteFacade;
    @Inject
    ProfilFacade profilFacade;
    @Inject
    MagasinFacade magasinFacade;
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;
    public EvenementSignartDto entityToDto(EvenementSignart entity){
        EvenementSignartDto dto = new EvenementSignartDto();
        dto.setId(entity.getId());
        dto.setCodeEvenement(entity.getCodeEvenement());
        dto.setContact(entity.getContact());
        dto.setDateCreation(entity.getDateCreation());
        dto.setDateOfficielle(entity.getDateOfficielle());
        dto.setDescription(entity.getDescription());
        dto.setLieu(entity.getLieu());
        dto.setLienVideo(entity.getLienVideo());
        dto.setNbrCodeAchete(entity.getNbrCodeAchete());
        dto.setPrixCodeEvent(entity.getPrixCodeEvent());
        dto.setResponsable(entity.getResponsable());
        dto.setTitre(entity.getTitre());
        dto.setStatus(entity.getStatus());
        if(entity.getIdArtiste() != null)
            dto.setIdArtiste(entity.getIdArtiste().getId());
        if(entity.getIdUtilisateur() != null)
            dto.setIdUtilisateur(entity.getIdUtilisateur().getId());
        return dto;
    }

    public EvenementSignart dtoToEntity(EvenementSignartDto dto)  throws SignArtException {
        EvenementSignart entity = new EvenementSignart();
        entity.setId(dto.getId());
        entity.setCodeEvenement(dto.getCodeEvenement());
        entity.setContact(dto.getContact());
        entity.setDateCreation(dto.getDateCreation());
        entity.setDateOfficielle(dto.getDateOfficielle());
        entity.setLieu(dto.getLieu());
        entity.setDescription(dto.getDescription());
        entity.setResponsable(dto.getResponsable());
        entity.setStatus(dto.getStatus());
        entity.setLienVideo(dto.getLienVideo());
        entity.setNbrCodeAchete(dto.getNbrCodeAchete());
        entity.setPrixCodeEvent(dto.getPrixCodeEvent());
        entity.setTitre(dto.getTitre());
        entity.setIdUtilisateur(utilisateurFacade.findById(dto.getIdUtilisateur()));
        entity.setIdArtiste(artisteFacade.findById(dto.getIdArtiste()));
        return entity;
    }
}
