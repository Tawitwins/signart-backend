package sn.modelsis.signart.service;

import sn.modelsis.signart.ServiceLivraison;
import sn.modelsis.signart.Tarification;
import sn.modelsis.signart.converter.ServiceLivraisonConverter;
import sn.modelsis.signart.converter.TarificationConverter;
import sn.modelsis.signart.dto.ServiceLivraisonDto;
import sn.modelsis.signart.dto.TarificationDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ServiceLivraisonFacade;
import sn.modelsis.signart.facade.TarificationFacade;
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
@Path("tarification")
public class TarificationREST {
    
    @Inject
    TarificationConverter tarificationConverter;
    
    @Inject
    TarificationFacade tarificationFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TarificationDto dto) throws SignArtException {
        Tarification entity = tarificationConverter.dtoToEntity(dto);
        tarificationFacade.create(entity);
        TarificationDto dtoRes = tarificationConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, TarificationDto dto) throws SignArtException {
        //Agent agent = agentFacade.findById(id);
        tarificationFacade.edit(tarificationConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        tarificationFacade.remove(tarificationFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public TarificationDto find(@PathParam("id") Integer id) throws SignArtException {
        Tarification tarification = tarificationFacade.find(id);
        return tarificationConverter.entityToDto(tarification);
    }
    
    @GET
    @Path("user/{zone}")
    @Produces({MediaType.APPLICATION_JSON})
    public TarificationDto findByNom(@PathParam("zone") String zone) throws SignArtException {
        Tarification tarification = tarificationFacade.findByZone(zone);
        return tarificationConverter.entityToDto(tarification);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TarificationDto> findAll() {
        List<TarificationDto> listDto = new ArrayList<>();
        List<Tarification> listEnt = tarificationFacade.findAll();
        
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                try {
                    return tarificationConverter.entityToDto(entity);
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
    public List<Tarification> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return tarificationFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(tarificationFacade.count());
    }
}
