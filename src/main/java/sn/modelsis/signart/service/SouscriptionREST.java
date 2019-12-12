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
import sn.modelsis.signart.Souscription;
import sn.modelsis.signart.converter.SouscriptionConverter;
import sn.modelsis.signart.dto.SouscriptionDto;
import sn.modelsis.signart.facade.SouscriptionFacade;

/**
 *
 * @author SNNGOM
 */
@Stateless
@Path("souscription")
public class SouscriptionREST {

    @Inject
    SouscriptionFacade souscriptionFacade;
    @Inject
    SouscriptionConverter souscriptionConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(SouscriptionDto dto) {
        souscriptionFacade.create(souscriptionConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, SouscriptionDto dto) {
        souscriptionFacade.edit(souscriptionConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        souscriptionFacade.remove(souscriptionFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public SouscriptionDto find(@PathParam("id") Integer id) {
        Souscription souscription = souscriptionFacade.find(id);
        return souscriptionConverter.entityToDto(souscription);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<SouscriptionDto> findAll() {
        List<SouscriptionDto> listDto = new ArrayList<>();
        List<Souscription> listEnt = souscriptionFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return souscriptionConverter.entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }
    


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Souscription> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return souscriptionFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(souscriptionFacade.count());
    }   

}
