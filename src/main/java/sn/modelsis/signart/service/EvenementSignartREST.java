package sn.modelsis.signart.service;

import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.EtatClient;
import sn.modelsis.signart.EvenementSignart;
import sn.modelsis.signart.ParametreAlgo;
import sn.modelsis.signart.converter.EvenementSignartConverter;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.dto.EvenementSignartDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EvenementSignartFacade;
import sn.modelsis.signart.facade.MagasinFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("evenementSignart")
public class EvenementSignartREST {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    @Inject
    EvenementSignartFacade evenementSignartFacade;
    @Inject
    EvenementSignartConverter evenementSignartConverter;

    public EvenementSignartREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(EvenementSignartDto dto) throws SignArtException {
        dto.setCodeEvenement("");
        dto.setNbrCodeAchete(0);
        evenementSignartFacade.create(evenementSignartConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, EvenementSignartDto dto) throws SignArtException {
        evenementSignartFacade.edit(evenementSignartConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean remove(@PathParam("id") Integer id) {
        EvenementSignart entity = evenementSignartFacade.find(id);
        evenementSignartFacade.remove(entity);
        return true;
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public EvenementSignartDto find(@PathParam("id") Integer id) {
        return evenementSignartConverter.entityToDto(evenementSignartFacade.find(id));
    }

    @GET
    @Path("byStatus/{status}")
    @Produces({MediaType.APPLICATION_JSON})
    public EvenementSignartDto findByStatus(@PathParam("status") String status) {
        return evenementSignartConverter.entityToDto(evenementSignartFacade.findByStatus(status));
    }
    @GET
    @Path("byIdAdmin/{idAdmin}")
    @Produces({MediaType.APPLICATION_JSON})
    public EvenementSignartDto findByAdmin(@PathParam("idAdmin") Integer idAdmin) {
        return evenementSignartConverter.entityToDto(evenementSignartFacade.findByAdmin(idAdmin));
    }
    @GET
    @Path("byIdArtiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public EvenementSignartDto findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        return evenementSignartConverter.entityToDto(evenementSignartFacade.findByArtiste(idArtiste));
    }
    @GET
    @Path("byCode/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public EvenementSignartDto findByCodeEvenement(@PathParam("code") String code) {
        return evenementSignartConverter.entityToDto(evenementSignartFacade.findByCodeEvenement(code));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<EvenementSignartDto> findAll() {
        List<EvenementSignartDto> dtoList = new ArrayList<>();
        List<EvenementSignart> entityList = evenementSignartFacade.findAll();
        if (entityList != null) {
            entityList.stream().map(entity
                    -> evenementSignartConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> dtoList.add(dto)
            );
        }
        Collections.reverse(dtoList);
        return dtoList;
        //return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EvenementSignart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return evenementSignartFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(evenementSignartFacade.count());
    }

    protected EntityManager getEntityManager() {
        return em;
    }
    
}
