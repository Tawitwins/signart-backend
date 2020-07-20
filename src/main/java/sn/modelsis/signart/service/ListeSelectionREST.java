/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.ListeSelection;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AbonneDto;
import sn.modelsis.signart.dto.ListeSelectionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("listeSelection")
public class ListeSelectionREST {
    @Inject
    ListeSelectionFacade listeSelectionFacade;
            
    @Inject
    UtilisateurFacade utilisateurFacade;     
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ListeSelectionDto dto) {
        listeSelectionFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ListeSelectionDto find(@PathParam("id") Integer id) {
        ListeSelection listeSelection = listeSelectionFacade.find(id);
        return entityToDto(listeSelection);
    }
    
    @GET
    @Path("name/{nomListe}")
    @Produces({MediaType.APPLICATION_JSON})
    public ListeSelectionDto find(@PathParam("nomListe") String nomListe) throws SignArtException {
        ListeSelection listeSelection = listeSelectionFacade.findByName(nomListe);
        return entityToDto(listeSelection);
    }
    
    @GET
    @Path("utilisateur/{idUtilisateur}")
    @Produces({MediaType.APPLICATION_JSON})
    public ListeSelectionDto findByUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur) throws SignArtException {
       ListeSelection listeSelection = new ListeSelection();
       ListeSelectionDto listDto = new ListeSelectionDto();
        try { 
           listeSelection = listeSelectionFacade.findByIdUtilisateur(idUtilisateur);
       
       } catch (final SignArtException e) {
            Logger.getLogger(ListeSelectionREST.class.getName()).log(Level.SEVERE, "findByUtilisateur/Exception", e);
            return listDto;
        }
       
        return entityToDto(listeSelection);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test liste rest";
    }
    
    private ListeSelection dtoToEntity(ListeSelectionDto dto) {
        
        ListeSelection entity = new ListeSelection();
       // entity.setId(dto.getId());
        entity.setIdUtilisateur(findUtilisateur(dto.getIdUtilisateur()));
        entity.setNomListe(dto.getNomListe());
        return entity;
    }
    
    private ListeSelectionDto entityToDto(ListeSelection entity){
        ListeSelectionDto dto = new ListeSelectionDto();
        dto.setId(entity.getId());
        dto.setNomListe(entity.getNomListe());
        dto.setIdUtilisateur(entity.getIdUtilisateur().getId());
        return dto;
    }
    
    private Utilisateur findUtilisateur(Integer idUtilisateur){
        return utilisateurFacade.find(idUtilisateur);
    }
    
    
}
