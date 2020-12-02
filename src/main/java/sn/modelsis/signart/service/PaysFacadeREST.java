package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Asynchronous;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import sn.modelsis.signart.Pays;
import sn.modelsis.signart.converter.PaysConverter;
import sn.modelsis.signart.dto.PaysDto;
import sn.modelsis.signart.facade.PaysFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("pays")
public class PaysFacadeREST extends AbstractFacade<Pays> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    @Inject
    PaysConverter paysConverter;
    @Inject
    PaysFacade paysFacade;
    
    public PaysFacadeREST() {
        super(Pays.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Pays entity) {
        super.create(entity);
    }
    @POST
    @Path("postAll")
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PaysDto[] tabPays) {
        for(int i=0;i < tabPays.length;i++){
             super.create(paysConverter.toEntity(tabPays[i]));
        }
       
    }
    

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Pays entity) {
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
    public PaysDto find(@PathParam("id") Integer id) {
        return paysConverter.toDto(super.find(id));
    }

    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pays> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaysDto> findAllPays() {
        //return paysFacade.findAll();
        List<PaysDto> listDto = new ArrayList<>();
        List<Pays> listEnt = paysFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity -> 
                paysConverter.toDto(entity)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }

//    @GET
//    @Produces(value = {MediaType.APPLICATION_JSON})
//    @Asynchronous
//    public void findAll(@Suspended final AsyncResponse asyncResponse) {
//        asyncResponse.resume(doFindAll());
//    }
//
//    private List<PaysDto> doFindAll() {
//       
//        List<PaysDto> listDto = new ArrayList<>();
//        List<Pays> listEnt = super.findAll();
//        if (listEnt != null) {
//            listEnt.stream().map((entity) -> {
//                return paysConverter.toDto(entity);
//            }).forEachOrdered((dto) -> {
//                listDto.add(dto);
//            });
//        }
//        return listDto;
//    }
    
}
