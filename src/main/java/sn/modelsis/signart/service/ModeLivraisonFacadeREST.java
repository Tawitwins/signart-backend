package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.ejb.Stateless;
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
import sn.modelsis.signart.ModeLivraison;
import sn.modelsis.signart.dto.ModeLivraisonDto;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("modelivraison")
public class ModeLivraisonFacadeREST extends AbstractFacade<ModeLivraison> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public ModeLivraisonFacadeREST() {
        super(ModeLivraison.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(ModeLivraison entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ModeLivraison entity) {
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
    public ModeLivraison find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("allMode")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModeLivraisonDto> findAlll() {
        //return clientFacade.findByArtiste(idArtiste);
        List<ModeLivraisonDto> listDto = new ArrayList<>();
        List<ModeLivraison> listEnt = super.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
        //return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModeLivraison> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    private ModeLivraisonDto entityToDto (ModeLivraison entity) {
        ModeLivraisonDto dto= new ModeLivraisonDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setLibelle(entity.getLibelle());
       return dto;
    }
    
}
