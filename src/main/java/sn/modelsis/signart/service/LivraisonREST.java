package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.Livraison;
import sn.modelsis.signart.converter.LivraisonConverter;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.dto.LivraisonCommandeDto;
import sn.modelsis.signart.dto.LivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.LigneLivraisonFacade;
import sn.modelsis.signart.facade.LivraisonFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("livraison")
public class LivraisonREST {

    @Inject
    LivraisonFacade livraisonFacade;
    @Inject
    LigneLivraisonFacade ligneLivraisonFacade;
    @Inject
    LivraisonConverter livraisonConverter;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public void create(LivraisonDto dto) throws SignArtException {
        livraisonFacade.create(livraisonConverter.dtoToEntity(dto));
    }

    @POST
    @Path("commande")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response createLivraisonCommande(LivraisonCommandeDto dto) {
        try {
            Livraison result = livraisonFacade.add(livraisonConverter.livraisonCommandeDtoToEntity(dto));
            Set<LigneLivraison> listLignesLivraison = ligneLivraisonFacade
                    .addAll(livraisonConverter.ligneLivraisonDtoToEntity(dto.getLignesCommande(), result));
            result.setLigneLivraisonSet(listLignesLivraison);
            result = livraisonFacade.save(result);
            //return Response.status(Response.Status.CREATED).entity(result).build();
            return Response.status(Response.Status.CREATED).entity(livraisonConverter.entityToDto(result)).build();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(LigneCommandeREST.class.getName()).log(Level.SEVERE, "create/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR : "+e.getMessage()).build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public void edit(@PathParam("id") Integer id, LivraisonDto dto) throws SignArtException {
        livraisonFacade.edit(livraisonConverter.dtoToEntity(dto));
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        livraisonFacade.remove(livraisonFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public LivraisonDto find(@PathParam("id") Integer id) throws SignArtException {
        Livraison entity = livraisonFacade.find(id);
        LivraisonDto dto = new LivraisonDto();
        if(entity != null)
            dto = livraisonConverter.entityToDto(entity);
        return dto;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<LivraisonDto> findAll() {
        List<LivraisonDto> listDto = new ArrayList<>();
        List<Livraison> listEnt = livraisonFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity -> livraisonConverter.entityToDto(entity)
                    ).forEachOrdered(dto -> listDto.add(dto));
        }
        return listDto;
    }
    @GET
    @Path("magasin/{idMagasin}/{idLivreur}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LivraisonDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin, @PathParam("idLivreur") Integer idLivreur) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LivraisonDto> listDto = new ArrayList<>();
        List<LigneLivraison> listEntTmp = ligneLivraisonFacade.findAll();
        Set<Livraison> listEnt = new HashSet<>();
        for (LigneLivraison ligneLivraison : listEntTmp) {
            if(ligneLivraison.getIdLigneCommande().getIdOeuvre().getIdMagasin().getId() == idMagasin &&
                    ligneLivraison.getIdAgent() !=null && ligneLivraison.getIdAgent().getId() == idLivreur){
                listEnt.add(ligneLivraison.getIdLivraison());
            }

        }
        listEnt.stream().map(entity -> livraisonConverter.entityToDto(entity)
        ).forEachOrdered(dto
                -> listDto.add(dto)
        );
        return listDto;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Livraison> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return livraisonFacade.findRange(new int[] { from, to });
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(livraisonFacade.count());
    }

}
