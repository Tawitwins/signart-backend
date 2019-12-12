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
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.converter.OeuvreSouscriptionConverter;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;
import sn.modelsis.signart.facade.OeuvreSouscriptionFacade;

import javax.ws.rs.core.Response;

/**
 *
 * @author SNNGOM
 */
@Stateless
@Path("oeuvresouscription")
public class OeuvreSouscriptionFacadeREST {

    @Inject
    OeuvreSouscriptionFacade oeuvreSouscriptionFacade;
    @Inject
    OeuvreSouscriptionConverter oeuvreSouscriptionConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(OeuvreSouscriptionDto dto) {
        oeuvreSouscriptionFacade.create(oeuvreSouscriptionConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, OeuvreSouscriptionDto dto) {
        oeuvreSouscriptionFacade.edit(oeuvreSouscriptionConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        oeuvreSouscriptionFacade.remove(oeuvreSouscriptionFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreSouscriptionDto find(@PathParam("id") Integer id) {
        OeuvreSouscription oeuvre = oeuvreSouscriptionFacade.find(id);
        return oeuvreSouscriptionConverter.entityToDto(oeuvre);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreSouscriptionDto> findAll() {
        List<OeuvreSouscriptionDto> listDto = new ArrayList<>();
        List<OeuvreSouscription> listEnt = oeuvreSouscriptionFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity -> 
                oeuvreSouscriptionConverter.entityToDto(entity)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }

  


    @GET
    @Path("souscription/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreSouscriptionDto> findBySouscription(@PathParam("id") Integer idSouscription) {
        List<OeuvreSouscriptionDto> listDto = new ArrayList<>();
        List<OeuvreSouscription> listEnt = oeuvreSouscriptionFacade.findBySouscription(idSouscription);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreSouscriptionConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreSouscription> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return oeuvreSouscriptionFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(oeuvreSouscriptionFacade.count());
    }

}
