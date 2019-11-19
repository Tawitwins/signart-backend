package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.MarquageOeuvre;
import sn.modelsis.signart.dto.MarquageOeuvreDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.MarquageOeuvreFacade;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.TypeMarquageFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("marquageoeuvre")
public class MarquageOeuvreFacadeREST {

    @Inject
    MarquageOeuvreFacade marquageOeuvreFacade;
    @Inject
    TypeMarquageFacade typeMarquageFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    OeuvreFacade oeuvreFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(MarquageOeuvreDto dto) {
        marquageOeuvreFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, MarquageOeuvreDto dto) {
        marquageOeuvreFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        marquageOeuvreFacade.remove(marquageOeuvreFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    public Response remove(@QueryParam("idClient") Integer idClient
            , @QueryParam("idOeuvre") Integer idOeuvre
            , @QueryParam("codeTypeMarquage") String codeTypeMarquage) {
        try {
            marquageOeuvreFacade.remove(marquageOeuvreFacade.findMarqueByClientAndOeuvre(codeTypeMarquage, idClient, idOeuvre));
            return Response.status(Response.Status.OK).build();
        } catch (SignArtException ex) {
            Logger.getLogger(MarquageArtisteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public MarquageOeuvreDto find(@PathParam("id") Integer id) {
        MarquageOeuvre entity = marquageOeuvreFacade.find(id);
        return entityToDto(entity);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MarquageOeuvreDto> findAll() {
        List<MarquageOeuvreDto> listDto = new ArrayList<>();
        List<MarquageOeuvre> listEnt = marquageOeuvreFacade.findAll();
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
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<MarquageOeuvre> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return marquageOeuvreFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(marquageOeuvreFacade.count());
    }
    
    /**
     * 
     * @param entity
     * @return 
     */
    private MarquageOeuvreDto entityToDto(MarquageOeuvre entity) {
        MarquageOeuvreDto dto = new MarquageOeuvreDto();
        dto.setIdTypeMarquage(entity.getIdTypeMarquage().getId());
        dto.setCodeTypeMarquage(entity.getIdTypeMarquage().getCode());
        dto.setIdOeuvre(entity.getIdOeuvre().getId());
        dto.setIdClient(entity.getIdClient().getId());
        dto.setDateMarquage(entity.getDateMarquage());
        return dto;
    }
    
    /**
     * 
     * @param dto
     * @return 
     */
    private MarquageOeuvre dtoToEntity(MarquageOeuvreDto dto){
        MarquageOeuvre entity = new MarquageOeuvre();
        entity.setDateMarquage(dto.getDateMarquage());
        entity.setIdOeuvre(oeuvreFacade.find(dto.getIdOeuvre()));
        entity.setIdClient(clientFacade.find(dto.getIdClient()));
        entity.setIdTypeMarquage(typeMarquageFacade.findByCode(dto.getCodeTypeMarquage()));
        return entity;
    }
}
