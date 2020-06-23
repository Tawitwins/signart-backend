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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.ListeSelection_Oeuvres;
import sn.modelsis.signart.dto.ListeSelection_OeuvresDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.ListeSelection_OeuvresFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("listeSelectionImage")
public class ListeSelection_OeuvresREST {
    
    @Inject
    ListeSelection_OeuvresFacade listeSelectionOeuvresFacade;
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ListeSelection_OeuvresDto dto) {
        listeSelectionOeuvresFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ListeSelection_OeuvresDto find(@PathParam("id") Integer id) {
        ListeSelection_Oeuvres listeImg = listeSelectionOeuvresFacade.find(id);
        return entityToDto(listeImg);
    }
    
    @GET
    @Path("listeOeuvre/{idliste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ListeSelection_OeuvresDto> findByArtiste(@PathParam("idliste") Integer idliste) throws SignArtException {
        List<ListeSelection_OeuvresDto> listDto = new ArrayList<>();
            List<ListeSelection_Oeuvres> listEnt = listeSelectionOeuvresFacade.findByIdListe(idliste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test liste Oeuvre rest";
    }
    
    private ListeSelection_Oeuvres dtoToEntity(ListeSelection_OeuvresDto dto) {
        
        ListeSelection_Oeuvres entity = new ListeSelection_Oeuvres();
       // entity.setId(dto.getId());
        entity.setIdListe(listeSelectionFacade.find(dto.getIdListe()));
        entity.setNomOeuvre(dto.getNomOeuvre());
        return entity;
    }
    
    private ListeSelection_OeuvresDto entityToDto(ListeSelection_Oeuvres entity){
        ListeSelection_OeuvresDto dto = new ListeSelection_OeuvresDto();
        dto.setIdListe(entity.getIdListe().getId());
        dto.setNomOeuvre(entity.getNomOeuvre());
        dto.setId(entity.getId());
        return dto;
    }
    
    
}
