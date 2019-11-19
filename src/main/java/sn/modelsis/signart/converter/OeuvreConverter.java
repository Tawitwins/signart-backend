package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.dto.OeuvreDto;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.CouleurFacade;
import sn.modelsis.signart.facade.SousTechniqueFacade;
import sn.modelsis.signart.facade.TechniqueFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class OeuvreConverter {

    @Inject
    ArtisteFacade artisteFacade;
    @Inject
    TechniqueFacade techniqueFacade;
    @Inject
    SousTechniqueFacade sousTechniqueFacade;
    @Inject
    CouleurFacade couleurFacade;

    public OeuvreDto entityToDto(Oeuvre entity) {
        OeuvreDto dto = new OeuvreDto();
        dto.setArtiste(entity.getIdArtiste().getPrenom() + " " + entity.getIdArtiste().getNom());
        dto.setCouleur(entity.getIdCouleur().getLibelle());
        dto.setDateAjout(entity.getDateAjout());
        dto.setDescription(entity.getDescription());
        dto.setDimensions(entity.getDimensions());
        dto.setFraisLivraison(entity.getFraisLivraison());
        dto.setId(entity.getId());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        dto.setIdSousTechnique(entity.getIdSousTechnique().getId());
        dto.setIdTechnique(entity.getIdSousTechnique().getIdTechnique().getId());
        //dto.setImage(entity.getImage());
        dto.setMiniature(entity.getMiniature());
        dto.setNom(entity.getNom());
        dto.setNouveau(entity.getNouveau());
        dto.setPrix(entity.getPrix());
        dto.setTauxremise(entity.getTauxremise());
        dto.setTaxes(entity.getTaxes());
        dto.setTechnique(entity.getIdSousTechnique().getIdTechnique().getLibelle());
        dto.setSousTechnique(entity.getIdSousTechnique().getLibelle());
        return dto;
    }

    public Oeuvre dtoToEntity(OeuvreDto dto) {
        Oeuvre entity = new Oeuvre();
        entity.setIdCouleur(couleurFacade.find(dto.getIdCouleur()));
        entity.setDateAjout(dto.getDateAjout());
        entity.setDescription(dto.getDescription());
        entity.setDimensions(dto.getDimensions());
        entity.setFraisLivraison(dto.getFraisLivraison());
        entity.setId(dto.getId());
        entity.setIdArtiste(artisteFacade.find(dto.getIdArtiste()));
        entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique()));
        entity.setImage(dto.getImage());
        entity.setMiniature(dto.getMiniature());
        entity.setNom(dto.getNom());
        entity.setNouveau(dto.getNouveau());
        entity.setPrix(dto.getPrix());
        entity.setTauxremise(dto.getTauxremise());
        entity.setTaxes(dto.getTaxes());
        return entity;
    }
}
