package sn.modelsis.signart.service;

import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.LignePanierDto;
import sn.modelsis.signart.facade.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("lignepanier")
public class LignePaiementREST {

    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    PaiementFacade paiementFacade;
    @Inject
    LignePaiementConverter lignePaiementConverter;
    @Inject
    PaiementConverter paiementConverter;


    public LignePaiementREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LignePaiementDto dto) {
        LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);
        lignePaiementFacade.create(entity);
        //return Response.status(Response.Status.CREATED).entity(lignePanierConverter.entityToDto(entity)).build();
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LignePaiementDto dto) {
        LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);
        lignePaiementFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            lignePaiementFacade.remove(lignePaiementFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LignePaiementREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LignePaiementDto find(@PathParam("id") Integer id) {
        LignePaiement lignePaiement = lignePaiementFacade.find(id);
        return lignePaiementConverter.entityToDto(lignePaiement);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LignePaiementDto> findAll() {
        List<LignePaiementDto> listDto = new ArrayList<>();
        List<LignePaiement> listEnt = lignePaiementFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> lignePaiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    /*@GET
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LignePaiementDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LignePaiementDto> listDto = new ArrayList<>();
        List<Paiement> listEntTmp = paiementFacade.findAll();
        List<LignePaiement> listEnt = new ArrayList<>();
        for (Paiement paiement : listEntTmp) {
            for (LignePaiement lignePaiement : paiement.getLignePaiementSet()) {
                if(lignePaiement.getIdOeuvre().getIdMagasin().getId() == idMagasin){
                    listEnt.add(ligneCommande);
                }
            }

        }
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneCommandeConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }*/


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(lignePaiementFacade.count());
    }
}
