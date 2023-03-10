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

import sn.modelsis.signart.Technique;
import sn.modelsis.signart.converter.TechniqueConverter;
import sn.modelsis.signart.dto.TechniqueDto;
import sn.modelsis.signart.facade.TechniqueFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("technique")
public class TechniqueFacadeREST extends AbstractFacade<Technique> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    @Inject
    TechniqueFacade techniqueFacade;
    @Inject
    TechniqueConverter techniqueConverter;

    public TechniqueFacadeREST() {
        super(Technique.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Technique entity) {
        super.create(entity);
    }
    
    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TechniqueDto> findAllTechnique() {
        List<TechniqueDto> listDto = new ArrayList<>();
        List<Technique> listEnt = techniqueFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Technique entity) {
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
    public TechniqueDto find(@PathParam("id") Integer id) {
        return EntityToDTO(super.find(id));
    }
    public TechniqueDto EntityToDTO(Technique tech)
    {
        TechniqueDto dto = new TechniqueDto();
        dto.setId(tech.getId());
        dto.setLibelle(tech.getLibelle());
        dto.setIdMenu(tech.getMenu().getId());
        return dto;
    }
    @GET
    @Path("by-menu/{idMenu}")
    @Produces({MediaType.APPLICATION_JSON})
    public TechniqueDto findByMenu(@PathParam("idMenu") Integer idMenu) {
        return techniqueConverter.toDto(techniqueFacade.findByMenu(idMenu));
    }
    
   @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Technique> findAll() {
        return super.findAll();
    }
    
     private TechniqueDto entityToDto(Technique entity) {
        TechniqueDto dto = new TechniqueDto();
        dto.setIdMenu(entity.getMenu().getId());
        dto.setLibelle(entity.getLibelle());
        dto.setId(entity.getId());   
        return dto;
    }
     
     
     

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Technique> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
