package sn.modelsis.signart.service;

import sn.modelsis.signart.CodeEventSignart;
import sn.modelsis.signart.EvenementSignart;
import sn.modelsis.signart.converter.CodeEventSignartConverter;
import sn.modelsis.signart.converter.EvenementSignartConverter;
import sn.modelsis.signart.dto.CodeEventSignartDto;
import sn.modelsis.signart.dto.EvenementSignartDto;
import sn.modelsis.signart.facade.CodeEventSignartFacade;
import sn.modelsis.signart.facade.EvenementSignartFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("codeEventSignart")
public class CodeEventSignartREST extends AbstractFacade<CodeEventSignart> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    @Inject
    CodeEventSignartFacade codeEventSignartFacade;
    @Inject
    CodeEventSignartConverter codeEventSignartConverter;

    public CodeEventSignartREST() {
        super(CodeEventSignart.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(CodeEventSignart entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, CodeEventSignart entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CodeEventSignart find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("byStatus/{status}")
    @Produces({MediaType.APPLICATION_JSON})
    public CodeEventSignartDto findByStatus(@PathParam("status") Boolean status) {
        return codeEventSignartConverter.entityToDto(codeEventSignartFacade.findByStatus(status));
    }
    @GET
    @Path("byIdEvenement/{idEvent}")
    @Produces({MediaType.APPLICATION_JSON})
    public CodeEventSignartDto findByEvenement(@PathParam("idEvent") Integer idEvent) {
        return codeEventSignartConverter.entityToDto(codeEventSignartFacade.findByEvenement(idEvent));
    }
    @GET
    @Path("byCode/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public CodeEventSignartDto findByCode(@PathParam("code") String code) {
        return codeEventSignartConverter.entityToDto(codeEventSignartFacade.findByCode(code));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<CodeEventSignart> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CodeEventSignart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
