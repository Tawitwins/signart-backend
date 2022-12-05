package sn.modelsis.signart.service;

import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.AgentConverter;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.BiographieDto;
import sn.modelsis.signart.dto.ImageProfilDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SNMBENGUO
 */
@Stateless
@Path("agent")
public class AgentREST {

    @Inject
    ArtisteFacade artisteFacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    AgentConverter agentConverter;
    
    @Inject
    AgentFacade agentFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AgentDto dto) throws SignArtException {
        Agent entity = agentConverter.dtoToEntity(dto);
        agentFacade.create(entity);
        AgentDto dtoRes = agentConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, AgentDto dto) throws SignArtException {
        //Agent agent = agentFacade.findById(id);
        agentFacade.edit(agentConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        agentFacade.remove(agentFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AgentDto find(@PathParam("id") Integer id) throws SignArtException {
        Agent agent = agentFacade.find(id);
        return agentConverter.entityToDto(agent);
    }
    
    @GET
    @Path("user/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AgentDto findByUser(@PathParam("id") Integer id) throws SignArtException {
        Utilisateur user = utilisateurFacade.find(id);
        Agent agent = agentFacade.findByUserAdvanced(user.getId());
        return agentConverter.entityToDto(agent);
    }
    @GET
    @Path("profil/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AgentDto> findByRole(@PathParam("code") String code) throws SignArtException {
        //Utilisateur user = utilisateurFacade.find(id);
        List<AgentDto> response = new ArrayList<>();
        List<Agent> agent = agentFacade.findByRole(code);
        if (agent != null) {
            agent.stream().map((entity) -> agentConverter.entityToDto(entity)).forEachOrdered((dto) -> {
                response.add(dto);
            });
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<AgentDto> findAll() {
        List<AgentDto> listDto = new ArrayList<>();
        List<Agent> listEnt = agentFacade.findAll();
        
        if (listEnt != null) {
            listEnt.stream().map((entity) -> agentConverter.entityToDto(entity)).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        
        return listDto;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Agent> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return agentFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(agentFacade.count());
    }
}
