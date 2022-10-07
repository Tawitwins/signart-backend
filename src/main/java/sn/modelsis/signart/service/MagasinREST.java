package sn.modelsis.signart.service;

import sn.modelsis.signart.Agent;
import sn.modelsis.signart.Magasin;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.converter.AgentConverter;
import sn.modelsis.signart.converter.MagasinConverter;
import sn.modelsis.signart.dto.AgentDto;
import sn.modelsis.signart.dto.MagasinDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AgentFacade;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.MagasinFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SNMBENGUO
 */
@Stateless
@Path("magasin")
public class MagasinREST {
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    MagasinConverter magasinConverter;
    
    @Inject
    MagasinFacade magasinFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(MagasinDto dto) throws SignArtException {
        Magasin entity = magasinConverter.dtoToEntity(dto);
        magasinFacade.create(entity);
        MagasinDto dtoRes = magasinConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, MagasinDto dto) throws SignArtException {
        //Agent agent = agentFacade.findById(id);
        magasinFacade.edit(magasinConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        magasinFacade.remove(magasinFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public MagasinDto find(@PathParam("id") Integer id) throws SignArtException {
        Magasin magasin = magasinFacade.find(id);
        return magasinConverter.entityToDto(magasin);
    }
    
    @GET
    @Path("user/{nom}")
    @Produces({MediaType.APPLICATION_JSON})
    public MagasinDto findByNom(@PathParam("nom") String nom) throws SignArtException {
        Magasin magasin = magasinFacade.findByNom(nom);
        return magasinConverter.entityToDto(magasin);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MagasinDto> findAll() {
        List<MagasinDto> listDto = new ArrayList<>();
        List<Magasin> listEnt = magasinFacade.findAll();
        
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                try {
                    return magasinConverter.entityToDto(entity);
                } catch (SignArtException e) {
                    throw new RuntimeException(e);
                }
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        
        return listDto;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Magasin> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return magasinFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(magasinFacade.count());
    }
}
