/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.converter;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Couleur;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.Technique;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.CouleurDto;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;
import sn.modelsis.signart.dto.TechniqueDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.CouleurFacade;
import sn.modelsis.signart.facade.SouscriptionFacade;
import sn.modelsis.signart.facade.TechniqueFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
public class OeuvreSouscriptionConverter {
    
    @Inject
    ArtisteFacade artisteFacade;
    @Inject
    TechniqueFacade techniqueFacade;
    @Inject
    SouscriptionFacade souscriptionFacade;
   /* @Inject
    SousTechniqueFacade sousTechniqueFacade;*/
    @Inject
    CouleurFacade couleurFacade;
    
    @Inject
    TechniqueConverter techniqueConverter;

    /*public OeuvreSouscriptionDto entityToDto(OeuvreSouscriptionDto entity) {
        OeuvreSouscriptionDto dto = new OeuvreSouscriptionDto();
        dto.setDateAjout(entity.getDateAjout());
        dto.setDescription(entity.getDescription());
        dto.setDimensions(entity.getDimensions());
        dto.setFraisLivraison(entity.getFraisLivraison());
        dto.setId(entity.getId());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        dto.setMiniature(entity.getMiniature());
        dto.setNom(entity.getNom());
        dto.setNouveau(entity.getNouveau());
        dto.setNouveau(entity.getNouveau());
        dto.setPrix(entity.getPrix());
        dto.setTauxremise(entity.getTauxremise());
        dto.setTaxes(entity.getTaxes());
        return dto;
    }*/

    public OeuvreSouscription dtoToEntity(OeuvreSouscriptionDto dto) throws SignArtException {
        OeuvreSouscription entity = new OeuvreSouscription();
        Calendar calendar = Calendar.getInstance();
        java.util.Date dateAjout = calendar.getTime();
        //java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());  
       // entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        //if(dto.getIdTechnique()!= null)
        //    entity.setIdTechnique(recupTechnique(dto.getIdTechnique().getId()));
        //if(dto.getIdCouleur()!= null)
        //    entity.setIdCouleur(recupCouleur(dto.getIdCouleur().getId()));
        entity.setIdTechnique(techniqueFacade.findById(dto.getIdTechnique()));
        entity.setIdCouleur(couleurFacade.findById(dto.getIdCouleur()));
        entity.setNouveau(dto.getNouveau());
        entity.setLithographie(dto.getLithographie());
        entity.setAuteur(dto.getAuteur());
        entity.setDimensions(dto.getDimensions());
        entity.setAnnee(dto.getAnnee());
        entity.setDateAjout(dateAjout);
        entity.setPrix(dto.getPrix());
        entity.setTauxremise(dto.getTauxremise());
        entity.setTaxes(dto.getTaxes());
        entity.setDescription(dto.getDescription());
        if(dto.getIdArtiste() != null)
            entity.setIdArtiste(recupArtiste(dto.getIdArtiste()));
        if(dto.getIdSouscription() != null)
            entity.setIdSouscription(souscriptionFacade.find(dto.getIdSouscription()));
        
        String imageValue = dto.getImage().getValue();
        //System.out.println(imageValue+"+++++++++++++++++++++++++++++++++++++++++++++++imageValue++++++++++++++++++++++++++++++++++++++++++++++");
        final byte[] image = Base64.decodeBase64(imageValue.getBytes());
        entity.setImage(image);
        //System.out.println(entity.getImage()+"+++++++++++++++++++++++++++++++++++++++++++++++entity image++++++++++++++++++++++++++++++++++++++++++++++");
        //entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique())); 
        return entity;
    }
    
    
    public OeuvreSouscriptionDto entityToDto(OeuvreSouscription entity) {
        OeuvreSouscriptionDto dto = new OeuvreSouscriptionDto();
         dto.setId(entity.getId());
        dto.setNom(entity.getNom());
       /* if(entity.getIdTechnique() != null)
            dto.setIdTechnique(techniqueConverter.toDto(entity.getIdTechnique()));
        if(entity.getIdCouleur() != null)
            dto.setIdCouleur(entityToDto(entity.getIdCouleur()));*/
        dto.setIdTechnique(entity.getIdTechnique().getId());
        dto.setIdCouleur(entity.getIdCouleur().getId());
        dto.setNouveau(entity.getNouveau());
        dto.setLithographie(entity.getLithographie());
        dto.setAnnee(entity.getAnnee());
        dto.setDimensions(entity.getDimensions());
        dto.setAuteur(entity.getAuteur());
        dto.setPrix(entity.getPrix());
        dto.setTauxremise(entity.getTauxremise());
        dto.setTaxes(entity.getTaxes());
        dto.setDescription(entity.getDescription());
        if(entity.getIdArtiste()!=null)
            dto.setIdArtiste(entity.getIdArtiste().getId());
        if(entity.getIdSouscription() != null)
            dto.setIdSouscription(entity.getIdSouscription().getId());
        dto.setDateAjout(entity.getDateAjout());
       // dto.setArtiste(entity.getIdArtiste().getPrenom() + " " + entity.getIdArtiste().getNom());
       // dto.setFraisLivraison(entity.getFraisLivraison());
        //dto.setIdSousTechnique(entity.getIdSousTechnique().getId());
        //dto.setMiniature(entity.getMiniature());
        //dto.setTechnique(entity.getIdSousTechnique().getIdTechnique().getLibelle());
       // dto.setSousTechnique(entity.getIdSousTechnique().getLibelle());
        return dto;
    }
 
     
     public Artiste recupArtiste(@PathParam("id") Integer id) {
        Artiste artiste = artisteFacade.find(id);
        return artiste;
    }
    
     public Technique recupTechnique(Integer idTech) throws SignArtException{
        return techniqueFacade.findById(idTech);     
    }
     
      public Couleur recupCouleur(Integer idCouleur) throws SignArtException{
        return couleurFacade.findById(idCouleur);     
    }
     public CouleurDto entityToDtoCouleur(Couleur entity) {
        CouleurDto dto = new CouleurDto();
        dto.setLibelle(entity.getLibelle());
        dto.setId(entity.getId());   
        return dto;
    }
     public TechniqueDto entityToDtoTechnique(Technique entity) {
        TechniqueDto dto = new TechniqueDto();
        dto.setLibelle(entity.getLibelle());
        dto.setId(entity.getId());
        dto.setIdMenu(entity.getMenu().getId());
        dto.setMenu(entity.getMenu());
        return dto;
    }
    
    
}
