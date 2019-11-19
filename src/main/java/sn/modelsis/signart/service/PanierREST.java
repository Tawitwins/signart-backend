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
import sn.modelsis.signart.Panier;
import sn.modelsis.signart.converter.PanierConverter;
import sn.modelsis.signart.dto.PanierDto;
import sn.modelsis.signart.facade.LignePanierFacade;
import sn.modelsis.signart.facade.PanierFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("panier")
public class PanierREST {

    @Inject
    PanierFacade panierFacade;
    @Inject
    LignePanierFacade lignePanierFacade;
    @Inject
    PanierConverter panierConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PanierDto dto) {
        panierFacade.create(panierConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, PanierDto dto) {
        panierFacade.edit(panierConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        panierFacade.remove(panierFacade.find(id));
        try {
            panierFacade.remove(panierFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LignePanierREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PanierDto find(@PathParam("id") Integer id) {
        return panierConverter.entityToDto(panierFacade.find(id));
    }
    
    @GET
    @Path("client/{idClient}")
    @Produces({MediaType.APPLICATION_JSON})
    public PanierDto findByIdClient(@PathParam("idClient") Integer idClient) {
        return panierConverter.entityToDto(panierFacade.findByIdClient(idClient));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PanierDto> findAll() {
        //return panierFacade.findAll();
        List<PanierDto> listDto = new ArrayList<>();
        List<Panier> listEnt = panierFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> panierConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(panierFacade.count());
    }

}
