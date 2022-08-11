package sn.modelsis.signart.service;

import sn.modelsis.signart.Magasin;
import sn.modelsis.signart.ServiceLivraison;
import sn.modelsis.signart.converter.MagasinConverter;
import sn.modelsis.signart.converter.ServiceLivraisonConverter;
import sn.modelsis.signart.dto.MagasinDto;
import sn.modelsis.signart.dto.ServiceLivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.MagasinFacade;
import sn.modelsis.signart.facade.ServiceLivraisonFacade;
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
@Path("serviceLivraison")
public class ServiceLivraisonREST {
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    ServiceLivraisonConverter serviceLivraisonConverter;
    
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ServiceLivraisonDto dto) throws SignArtException {
        ServiceLivraison entity = serviceLivraisonConverter.dtoToEntity(dto);
        serviceLivraisonFacade.create(entity);
        ServiceLivraisonDto dtoRes = serviceLivraisonConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ServiceLivraisonDto dto) throws SignArtException {
        //Agent agent = agentFacade.findById(id);
        serviceLivraisonFacade.edit(serviceLivraisonConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        serviceLivraisonFacade.remove(serviceLivraisonFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ServiceLivraisonDto find(@PathParam("id") Integer id) throws SignArtException {
        ServiceLivraison serviceLivraison = serviceLivraisonFacade.find(id);
        return serviceLivraisonConverter.entityToDto(serviceLivraison);
    }
    
    @GET
    @Path("user/{nom}")
    @Produces({MediaType.APPLICATION_JSON})
    public ServiceLivraisonDto findByNom(@PathParam("nom") String nom) throws SignArtException {
        ServiceLivraison serviceLivraison = serviceLivraisonFacade.findByNom(nom);
        return serviceLivraisonConverter.entityToDto(serviceLivraison);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceLivraisonDto> findAll() {
        List<ServiceLivraisonDto> listDto = new ArrayList<>();
        List<ServiceLivraison> listEnt = serviceLivraisonFacade.findAll();
        
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                try {
                    return serviceLivraisonConverter.entityToDto(entity);
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
    public List<ServiceLivraison> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return serviceLivraisonFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(serviceLivraisonFacade.count());
    }
}
