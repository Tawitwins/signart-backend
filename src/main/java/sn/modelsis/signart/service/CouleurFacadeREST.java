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
import sn.modelsis.signart.Couleur;
import sn.modelsis.signart.dto.CouleurDto;
import sn.modelsis.signart.facade.CouleurFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("couleur")
public class CouleurFacadeREST extends AbstractFacade<Couleur> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
    @Inject
    CouleurFacade couleurFacade;

    public CouleurFacadeREST() {
        super(Couleur.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Couleur entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Couleur entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CouleurDto> findAllTechnique() {
        List<CouleurDto> listDto = new ArrayList<>();
        List<Couleur> listEnt = couleurFacade.findAll();
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
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Couleur find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Couleur> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Couleur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
     private CouleurDto entityToDto(Couleur entity) {
        CouleurDto dto = new CouleurDto();
        dto.setLibelle(entity.getLibelle());
        dto.setId(entity.getId());   
        return dto;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
