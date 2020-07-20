/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.ListeSelection;
import sn.modelsis.signart.Terminal;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AbonneDto;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.DelaiFacade;
import sn.modelsis.signart.facade.EtatAbonnementFacade;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.TerminalFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("abonnement")
public class AbonnementREST {
    
    @Inject
    AbonnementFacade abonnementfacade;
    
    @Inject
    TerminalFacade terminalFacade;
    
    @Inject
    DelaiFacade delaiFacade;
    
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    AbonneFacade abonneFacade;
    
    @Inject
    EtatAbonnementFacade etatAbonnementFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AbonnementDto dto) throws SignArtException {
        abonnementfacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Path("editEtatAbonnement/{idAbonnement}")
    public Response editPhoto(@PathParam("idAbonnement") Integer idAbonnement, Integer idEtat) throws SignArtException{
        Abonnement abonnement;
                abonnement = abonnementfacade.findById(idAbonnement);
                abonnement.setEtatAbonnement(etatAbonnementFacade.findById(idEtat));      
                abonnementfacade.edit(abonnement);
              //  AbonnementDto dto = entityToDto(abonnement);
                return Response.status(Response.Status.OK).build();
                 
    }
    
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonnementDto find(@PathParam("id") Integer id) {
        Abonnement abonnement = abonnementfacade.find(id);
        return entityToDto(abonnement);
    }
    
    @GET
    @Path("abonne/{idAbonne}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AbonnementDto> findByAbonne(@PathParam("idAbonne") Integer idAbonne) throws SignArtException {
         List<AbonnementDto> dtoList = new ArrayList<>();
         List<Abonnement> entityList = abonnementfacade.findByIdAbonne(idAbonne);
            if (entityList != null) {
                entityList.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> dtoList.add(dto)
                );
            }
        return dtoList;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test abonnement rest";
    }
    
    @GET
    @Path("loadAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AbonnementDto> findAll(){
            List<AbonnementDto> dtoList = new ArrayList<>();
            List<Abonnement> entityList = abonnementfacade.findAll();
            if (entityList != null) {
                entityList.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> dtoList.add(dto)
                );
            }
            return dtoList;   
    }
    
    private Abonnement dtoToEntity(AbonnementDto dto) throws SignArtException {
        
        Abonnement entity = new Abonnement();
       // entity.setId(dto.getId());
        entity.setIdDelai(delaiFacade.findById(dto.getIdDelai()));
        entity.setIdListeSelection(listeSelectionFacade.findById(dto.getIdListeSelection()));
        entity.setIdTerminal(terminalFacade.findById(dto.getIdTerminal()));
        entity.setIdAbonne(abonneFacade.findById(dto.getIdAbonne()));
        entity.setMontantPaiement(dto.getMontantPaiement());
        entity.setPrecisions(dto.getPrecisions());
        entity.setEtatAbonnement(etatAbonnementFacade.findById(dto.getEtatAbonnement()));
        return entity;
    }
    
    private AbonnementDto entityToDto(Abonnement entity){
        AbonnementDto dto = new AbonnementDto();
        dto.setIdDelai(entity.getIdDelai().getId());
        dto.setIdListeSelection(entity.getIdListeSelection().getId());
        dto.setIdTerminal(entity.getIdTerminal().getId());
        dto.setIdAbonne(entity.getIdAbonne().getId());
        dto.setMontantPaiement(entity.getMontantPaiement());
        dto.setPrecisions(entity.getPrecisions());
        dto.setEtatAbonnement(entity.getEtatAbonnement().getId());
        dto.setId(entity.getId());
        return dto;
    }
    
    private Utilisateur findUtilisateur(Integer idUtilisateur){
        return utilisateurFacade.find(idUtilisateur);
    }
    
    private Abonne findAbonne(Integer idAbonne){
        return abonneFacade.find(idAbonne);
    }
    
    private Delai findDelai(Integer idDelai){
        return delaiFacade.find(idDelai);
    }
    
    private Terminal findTerminal(Integer idTerminal){
        return terminalFacade.find(idTerminal);
    }
    
    private ListeSelection findListe(Integer idList) throws SignArtException{
        
        return listeSelectionFacade.findById(idList);
    }
    
    
}
