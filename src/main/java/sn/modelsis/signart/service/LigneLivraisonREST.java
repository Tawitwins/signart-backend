package sn.modelsis.signart.service;

import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.Livraison;
import sn.modelsis.signart.converter.LigneLivraisonConverter;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.LivraisonConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.LigneLivraisonFacade;
import sn.modelsis.signart.facade.LignePaiementFacade;
import sn.modelsis.signart.facade.LivraisonFacade;
import sn.modelsis.signart.facade.PaiementFacade;

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
@Path("lignelivraison")
public class LigneLivraisonREST {

    @Inject
    LigneLivraisonFacade ligneLivraisonFacade;
    @Inject
    LivraisonFacade livraisonFacade;
    @Inject
    LigneLivraisonConverter ligneLivraisonConverter;
    @Inject
    LivraisonConverter livraisonConverter;


    public LigneLivraisonREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LigneLivraisonDto dto) throws SignArtException {
        LigneLivraison entity = ligneLivraisonConverter.dtoToEntity(dto);
        ligneLivraisonFacade.create(entity);
        //return Response.status(Response.Status.CREATED).entity(lignePanierConverter.entityToDto(entity)).build();
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LigneLivraisonDto dto) throws SignArtException {
        LigneLivraison entity = ligneLivraisonConverter.dtoToEntity(dto);
        ligneLivraisonFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ligneLivraisonFacade.remove(ligneLivraisonFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LigneLivraisonREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LigneLivraisonDto find(@PathParam("id") Integer id) throws SignArtException {
        LigneLivraison ligneLivraison = ligneLivraisonFacade.find(id);
        return ligneLivraisonConverter.entityToDto(ligneLivraison);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LigneLivraisonDto> findAll() throws SignArtException{
        List<LigneLivraisonDto> listDto = new ArrayList<>();
        List<LigneLivraison> listEnt = ligneLivraisonFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneLivraisonConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    @GET
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LigneLivraisonDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LigneLivraisonDto> listDto = new ArrayList<>();
        List<LigneLivraison> listEntTmp = ligneLivraisonFacade.findAll();
        List<LigneLivraison> listEnt = new ArrayList<>();
        for (LigneLivraison ligneLivraison : listEntTmp) {
            if(ligneLivraison.getIdLigneCommande().getIdOeuvre().getIdMagasin().getId() == idMagasin){
                listEnt.add(ligneLivraison);
            }

        }
        listEnt.stream().map(entity -> ligneLivraisonConverter.entityToDto(entity)
        ).forEachOrdered(dto
                -> listDto.add(dto)
        );
        return listDto;
    }


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(ligneLivraisonFacade.count());
    }
}
