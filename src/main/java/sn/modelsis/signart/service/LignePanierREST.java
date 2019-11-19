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
import sn.modelsis.signart.LignePanier;
import sn.modelsis.signart.converter.LignePanierConverter;
import sn.modelsis.signart.converter.PanierConverter;
import sn.modelsis.signart.dto.LignePanierDto;
import sn.modelsis.signart.facade.EtatLignePanierFacade;
import sn.modelsis.signart.facade.LignePanierFacade;
import sn.modelsis.signart.facade.PanierFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("lignepanier")
public class LignePanierREST {

    @Inject
    LignePanierFacade lignePanierFacade;
    @Inject
    PanierFacade panierFacade;
    @Inject
    LignePanierConverter lignePanierConverter;
    @Inject
    PanierConverter panierConverter;
    @Inject
    EtatLignePanierFacade etatLigneanierFacade;

    public LignePanierREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LignePanierDto dto) {
        LignePanier entity = lignePanierConverter.dtoToEntity(dto);
        entity.setIdEtatLignePanier(etatLigneanierFacade.findByCode("NOUVEAU"));
        lignePanierFacade.create(entity);
        //return Response.status(Response.Status.CREATED).entity(lignePanierConverter.entityToDto(entity)).build();
        return Response.status(Response.Status.CREATED).entity(panierConverter.entityToDto(panierFacade.findByIdClient(dto.getIdClient()))).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LignePanierDto dto) {
        lignePanierFacade.edit(lignePanierConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            lignePanierFacade.remove(lignePanierFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LignePanierREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LignePanierDto find(@PathParam("id") Integer id) {
        LignePanier lignePanier = lignePanierFacade.find(id);
        return lignePanierConverter.entityToDto(lignePanier);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LignePanierDto> findAll() {
        List<LignePanierDto> listDto = new ArrayList<>();
        List<LignePanier> listEnt = lignePanierFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> lignePanierConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

    @GET
    @Path("client/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LignePanierDto> findByClient(@PathParam("id") Integer idClient) {
        List<LignePanierDto> listDto = new ArrayList<>();
        List<LignePanier> listEnt = lignePanierFacade.findByClient(idClient);
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> lignePanierConverter.entityToDto(entity)
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
        return String.valueOf(lignePanierFacade.count());
    }
}
