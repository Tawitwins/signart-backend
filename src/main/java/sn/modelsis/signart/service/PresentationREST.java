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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Formation;
import sn.modelsis.signart.Presentation;
import sn.modelsis.signart.dto.FormationDto;
import sn.modelsis.signart.dto.PresentationDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.PresentationFacade;
import sn.modelsis.signart.facade.ArtisteFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("presentation")
public class PresentationREST {
    
    @Inject
    PresentationFacade presentationFacade;
    
    @Inject
    ArtisteFacade artisteFacade;
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PresentationDto dto) {
        presentationFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, PresentationDto dto) {
        presentationFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        presentationFacade.remove(presentationFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PresentationDto find(@PathParam("id") Integer id) {
        Presentation presentation = presentationFacade.find(id);
        return entityToDto(presentation);
    }
    
    @GET
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public String findtest() {
        return "test presentation";
    }
    
    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public PresentationDto findByArtiste(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        Presentation dto = presentationFacade.findByArtiste(idArtiste);
        return entityToDto(dto);
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(presentationFacade.count());
    }
    
    private Presentation dtoToEntity(PresentationDto dto) {
        
        Presentation entity = new Presentation();
       // entity.setId(dto.getId());
       entity.setLibelle(dto.getLibelle());
       entity.setVideoId(dto.getVideoId());
       entity.setEtatPubPresentation(dto.getEtatPubPresentation());
       entity.setIdArtiste(findArtiste(dto.getIdArtiste())); 
        return entity;
    }
    
    private PresentationDto entityToDto(Presentation entity){
        PresentationDto dto = new PresentationDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setVideoId(entity.getVideoId());
        dto.setEtatPubPresentation(entity.getEtatPubPresentation());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        return dto;
    }
    
    
    
    private Artiste findArtiste(Integer idArtiste){
        return artisteFacade.find(idArtiste);
    }
    
}
