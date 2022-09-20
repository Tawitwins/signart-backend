package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.dto.ClientDto;
import sn.modelsis.signart.dto.VisiteurDto;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.facade.EtatClientFacade;
import sn.modelsis.signart.facade.PaysFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class VisiteurConverter {

    @Inject
    EtatClientFacade etatClientFacade;
    @Inject
    PaysFacade paysFacade;
    @Inject
    PaysConverter paysConverter;
    @Inject
    UtilisateurFacade utilisateurFacade;

    public VisiteurDto entityToDto(Visiteur entity) {
        VisiteurDto dto = new VisiteurDto();
        dto.setId(entity.getId());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setRaisonSociale(entity.getRaisonSociale());
        dto.setTypeVisiteur(entity.getTypeVisiteur());
        dto.setPays(paysConverter.toDto(paysFacade.find(entity.getPays().getId())));
        dto.setIdPays(entity.getPays().getId());

        return dto;
    }

    public Visiteur dtoToEntity(VisiteurDto dto) {
        Visiteur entity = new Visiteur();
        entity.setId(dto.getId());
        entity.setPays(paysFacade.find(dto.getIdPays()));
        //entity.setIdPays();
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        entity.setRaisonSociale(dto.getRaisonSociale());
        entity.setTypeVisiteur(dto.getTypeVisiteur());
        return entity;
    }
}
