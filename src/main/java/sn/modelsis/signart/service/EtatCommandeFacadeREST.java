package sn.modelsis.signart.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sn.modelsis.signart.EtatAbonnement;
import sn.modelsis.signart.EtatCommande;
import sn.modelsis.signart.dto.EtatCommandeDto;
import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("etatcommande")
public class EtatCommandeFacadeREST extends AbstractFacade<EtatCommande> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;

    public EtatCommandeFacadeREST() {
        super(EtatCommande.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    //@Consumes({MediaType.APPLICATION_XML})
    public void create(EtatCommande entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, EtatCommande entity) {
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
    public EtatCommande find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Path("code/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public EtatCommandeDto findByCode(@PathParam("code") String code) throws SignArtException {
        return entityToDto(this.findByEtatCommandeCode(code));
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<EtatCommande> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EtatCommande> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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


    public EtatCommande findByEtatCommandeCode(String code) throws SignArtException {
        try {
            final TypedQuery<EtatCommande> query = getEntityManager().createNamedQuery("EtatCommande.findByCode",
                    EtatCommande.class);
            query.setParameter("code", code);
            //query.setMaxResults(1);
            final List<EtatCommande> etats = query.getResultList();
            if (etats.isEmpty()) {
                return null;
            }
            return etats.get(0);
        } catch (Exception e) {
            throw new SignArtException(e.getMessage(), e);
        }
    }

    public EtatCommandeDto entityToDto(EtatCommande entity){
        EtatCommandeDto dto = new EtatCommandeDto();

        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setLibelle(entity.getLibelle());

        return dto;
    }
}
