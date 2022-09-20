package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Couleur;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.Technique;
import sn.modelsis.signart.dto.OeuvreDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.CouleurFacade;
import sn.modelsis.signart.facade.StatutOeuvreFacade;
//import sn.modelsis.signart.facade.SousTechniqueFacade;
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
   /* @Inject
    SousTechniqueFacade sousTechniqueFacade;*/
    @Inject
    CouleurFacade couleurFacade;
    @Inject
    StatutOeuvreFacade statutOeuvreFacade;

    public OeuvreDto entityToDto(Oeuvre entity) {
        OeuvreDto dto = new OeuvreDto();
        dto.setArtiste(entity.getIdArtiste().getPrenom() + " " + entity.getIdArtiste().getNom());
      // dto.setCouleur(entity.getIdCouleur().getLibelle());
        dto.setFraisLivraison(entity.getFraisLivraison());
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        if(entity.getIdTechnique()!= null)
            dto.setIdTechnique(entity.getIdTechnique().getId());
        if(entity.getIdCouleur()!= null)
            dto.setIdCouleur(entity.getIdCouleur().getId());
        dto.setNouveau(entity.getNouveau());
        dto.setLithographie(entity.getLithographie());
        dto.setAuteur(entity.getAuteur());
        dto.setAnnee(entity.getAnnee());
        dto.setDimensions(entity.getDimensions());
        dto.setPrix(entity.getPrix());
        dto.setTauxremise(entity.getTauxremise());
        dto.setTaxes(entity.getTaxes());
        dto.setImage(entity.getImage());
        dto.setMiniature(entity.getMiniature());
        dto.setDateAjout(entity.getDateAjout());
        dto.setDescription(entity.getDescription());
        if(entity.getIdStatut() != null)
            dto.setIdStatus(entity.getIdStatut().getId());
        dto.setStock(entity.getStock());
        //dto.setIdSousTechnique(entity.getIdSousTechnique().getId());
        //dto.setTechnique(entity.getIdSousTechnique().getIdTechnique().getLibelle());
       // dto.setSousTechnique(entity.getIdSousTechnique().getLibelle());
        return dto;
    }

    public Oeuvre dtoToEntity(OeuvreDto dto) throws SignArtException {
        Oeuvre entity = new Oeuvre();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        
        
        
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        if(dto.getIdTechnique() != null)
            entity.setIdTechnique(recupTechnique(dto.getIdTechnique()));
        if(dto.getIdCouleur() != null)
            entity.setIdCouleur(recupCouleur(dto.getIdCouleur()));
        entity.setNouveau(dto.getNouveau());
        entity.setLithographie(dto.getLithographie());
        entity.setAuteur(dto.getAuteur());
        entity.setDimensions(dto.getDimensions());
        entity.setAnnee(dto.getAnnee());
        entity.setDateAjout(dateAjout);
        entity.setPrix(dto.getPrix());
        entity.setTauxremise(dto.getTauxremise());
        entity.setTaxes(dto.getTaxes());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        if(dto.getIdArtiste() != null)
            entity.setIdArtiste(recupArtiste(dto.getIdArtiste()));
        entity.setFraisLivraison(BigDecimal.valueOf(1500.00));
        entity.setMiniature(dto.getMiniature());
        if(dto.getIdStatus() != null)
            entity.setIdStatut(statutOeuvreFacade.find(dto.getIdStatus()));
        entity.setStock(dto.getStock());
        
        //entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique()));
        
        
        return entity;
    }
    
     public Oeuvre convertOueuvreSouscription(OeuvreSouscription dto) throws SignArtException {
        Oeuvre entity = new Oeuvre();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        
        
        
       // entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        if(dto.getIdTechnique() != null)
            entity.setIdTechnique(dto.getIdTechnique());
        if(dto.getIdCouleur() != null)
            entity.setIdCouleur(dto.getIdCouleur());
        entity.setNouveau(dto.getNouveau());
        entity.setLithographie(dto.getLithographie());
        entity.setAuteur(dto.getAuteur());
        entity.setDimensions(dto.getDimensions());
        entity.setAnnee(dto.getAnnee());
        entity.setDateAjout(dateAjout);
        entity.setPrix(dto.getPrix());
        entity.setTauxremise(dto.getTauxremise());
        entity.setTaxes(dto.getTaxes());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        if(dto.getIdArtiste() != null)
            entity.setIdArtiste(dto.getIdArtiste());
        entity.setFraisLivraison(BigDecimal.valueOf(1500.00));
            entity.setIdStatut(statutOeuvreFacade.find(1));
        entity.setStock(1);
        
        //entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique()));
        
        
        return entity;
    }
    
    public Artiste recupArtiste(Integer idArtiste) throws SignArtException{
        return artisteFacade.findById(idArtiste);     
    }
    
     public Technique recupTechnique(Integer idTech) throws SignArtException{
        return techniqueFacade.findById(idTech);     
    }
     
      public Couleur recupCouleur(Integer idCouleur) throws SignArtException{
        return couleurFacade.findById(idCouleur);     
    }
}
