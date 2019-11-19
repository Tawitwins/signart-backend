package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
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
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.facade.PaiementFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("paiement")
public class PaiementREST {

    @Inject
    PaiementFacade paiementFacade;
    @Inject
    PaiementConverter paiementConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PaiementDto dto) {
        paiementFacade.create(paiementConverter.dtoToEntity(dto));
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PaiementDto dto) {
        paiementFacade.edit(paiementConverter.dtoToEntity(dto));
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        paiementFacade.remove(paiementFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PaiementDto find(@PathParam("id") Integer id) {
        return paiementConverter.entityToDto(paiementFacade.find(id));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaiementDto> findAll() {
        List<PaiementDto> listDto = new ArrayList<>();
        List<Paiement> listEnt = paiementFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> paiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Paiement> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return paiementFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(paiementFacade.count());
    }

}
