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
import sn.modelsis.signart.Theme;
import sn.modelsis.signart.dto.ThemeDto;
import sn.modelsis.signart.facade.ThemeFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("theme")
public class ThemeFacadeREST extends AbstractFacade<Theme> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    @Inject
    ThemeFacade themeFacade;

    public ThemeFacadeREST() {
        super(Theme.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Theme entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Theme entity) {
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
    public Theme find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ThemeDto> findAllTechnique() {
        List<ThemeDto> listDto = new ArrayList<>();
        List<Theme> listEnt = themeFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return entityToDto(entity); 
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Theme> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Theme> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

   
    
     private ThemeDto entityToDto(Theme entity) {
        ThemeDto dto = new ThemeDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    
     @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
