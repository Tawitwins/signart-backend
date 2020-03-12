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
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Couleur;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.Technique;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.CouleurFacade;
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
   /* @Inject
    SousTechniqueFacade sousTechniqueFacade;*/
    @Inject
    CouleurFacade couleurFacade;

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
        entity.setIdTechnique(recupTechnique(dto.getIdTechnique()));
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
        entity.setIdArtiste(recupArtiste(dto.getIdArtiste()));
        
        //entity.setIdSousTechnique(sousTechniqueFacade.find(dto.getIdSousTechnique())); 
        return entity;
    }
    
    
    public OeuvreSouscriptionDto entityToDto(OeuvreSouscription entity) {
        OeuvreSouscriptionDto dto = new OeuvreSouscriptionDto();
         dto.setId(entity.getId());
        dto.setNom(entity.getNom());
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
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setIdArtiste(entity.getIdArtiste().getId());
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
    
    
}
