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
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.dto.DelaiDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.DelaiFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("delai")
public class DelaiREST {
    
    @Inject
    DelaiFacade delaiFacade;
    

    

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(DelaiDto dto) {
        delaiFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public DelaiDto find(@PathParam("id") Integer id) {
        Delai delai = delaiFacade.find(id);
        return entityToDto(delai);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test(@PathParam("id") Integer id) {
        return "test delai rest";
    }
    
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DelaiDto> findByArtiste() {
        List<DelaiDto> listDto = new ArrayList<>();
            List<Delai> listEnt = delaiFacade.findAll();
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        
    }
    
    private Delai dtoToEntity(DelaiDto dto) {
        
        Delai entity = new Delai();
       // entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setNbMois(dto.getNbMois());
        entity.setDescription(dto.getDescription());
        entity.setPrix(dto.getPrix());
        return entity;
    }
    
    private DelaiDto entityToDto(Delai entity){
        DelaiDto dto = new DelaiDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setNbMois(entity.getNbMois());
        dto.setDescription(entity.getDescription());
        dto.setPrix(entity.getPrix());
        return dto;
    }
    
    
}
