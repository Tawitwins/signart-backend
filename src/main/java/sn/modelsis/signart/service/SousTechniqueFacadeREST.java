package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*import sn.modelsis.signart.SousTechnique;
import sn.modelsis.signart.Technique;
import sn.modelsis.signart.converter.SousTechniqueConverter;
import sn.modelsis.signart.converter.TechniqueConverter;
import sn.modelsis.signart.dto.SousTechniqueDto;
import sn.modelsis.signart.dto.TechniqueDto;
//import sn.modelsis.signart.facade.SousTechniqueFacade;
import sn.modelsis.signart.facade.TechniqueFacade;

/**
 *
 * @author SNLOM

@Stateless
@Path("soustechnique")
public class SousTechniqueFacadeREST extends AbstractFacade<SousTechnique> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    @Inject
    SousTechniqueFacade sousTechniqueFacade;
    @Inject
    SousTechniqueConverter sousTechniqueConverter;

    public SousTechniqueFacadeREST() {
        super(SousTechnique.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(SousTechnique entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, SousTechnique entity) {
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
    public SousTechnique find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("by-technique/{idTechnique}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SousTechniqueDto> findAllByTechnique(@PathParam("idTechnique") Integer idTechnique) {
        List<SousTechniqueDto> listDto = new ArrayList<>();
        List<SousTechnique> list = sousTechniqueFacade.findByTechnique(idTechnique);
        if(list!=null && !list.isEmpty()){
            for (SousTechnique sousTechnique : list) {
                listDto.add(sousTechniqueConverter.toDto(sousTechnique));
            }
        }
        return listDto;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<SousTechnique> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SousTechnique> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
 */