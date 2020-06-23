/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
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
import sn.modelsis.signart.ListeSelection;
import sn.modelsis.signart.Terminal;
import sn.modelsis.signart.dto.ListeSelectionDto;
import sn.modelsis.signart.dto.TerminalDto;
import sn.modelsis.signart.facade.TerminalFacade;

/**
 *
 * @author Pendaaa
 */

@Stateless
@Path("terminal")
public class TerminalREST {
    
    @Inject
    TerminalFacade terminalFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TerminalDto dto) {
        terminalFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public TerminalDto find(@PathParam("id") Integer id) {
        Terminal terminal = terminalFacade.find(id);
        return entityToDto(terminal);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test terminal rest";
    }
    
     @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TerminalDto> findByArtiste() {
        List<TerminalDto> listDto = new ArrayList<>();
            List<Terminal> listEnt = terminalFacade.findAll();
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        
    }
    
    private Terminal dtoToEntity(TerminalDto dto) {
        
        Terminal entity = new Terminal();
       // entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setDescription(dto.getDescription());
        entity.setPrix(dto.getPrix());
        return entity;
    }
    
    private TerminalDto entityToDto(Terminal entity){
        TerminalDto dto = new TerminalDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setDescription(entity.getDescription());
        dto.setPrix(entity.getPrix());
        return dto;
    }
    
}
