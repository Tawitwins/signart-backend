package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.Date;
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
import sn.modelsis.signart.MarquageArtiste;
import sn.modelsis.signart.dto.MarquageArtisteDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.MarquageArtisteFacade;
import sn.modelsis.signart.facade.TypeMarquageFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("marquageartiste")
public class MarquageArtisteFacadeREST {

    @Inject
    MarquageArtisteFacade marquageArtisteFacade;
    @Inject
    TypeMarquageFacade typeMarquageFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    ArtisteFacade artisteFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(MarquageArtisteDto dto) {
        MarquageArtiste entity = dtoToEntity(dto);
        if (null == entity.getDateMarquage()) {
            entity.setDateMarquage(new Date());
        }
        marquageArtisteFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, MarquageArtisteDto dto) {
        marquageArtisteFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            marquageArtisteFacade.remove(marquageArtisteFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(MarquageArtisteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    //@Path("")
    public Response remove(@QueryParam("idClient") Integer idClient,
            @QueryParam("idArtiste") Integer idArtiste,
            @QueryParam("codeTypeMarquage") String codeTypeMarquage) {
        try {
            /*MarquageArtisteDto dto = new MarquageArtisteDto();
             dto.setCodeTypeMarquage(codeTypeMarquage);
             dto.setIdArtiste(idArtiste);
             dto.setIdClient(idClient);
             marquageArtisteFacade.remove(marquageArtisteFacade.find(dtoToEntity(dto)));*/
            marquageArtisteFacade.remove(marquageArtisteFacade.findMarqueByClientAndArtiste(codeTypeMarquage, idClient, idArtiste));
            return Response.status(Response.Status.OK).build();
        } catch (SignArtException ex) {
            Logger.getLogger(MarquageArtisteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("marquage")
    @Produces({MediaType.APPLICATION_JSON})
    public MarquageArtisteDto findMarquage(@QueryParam("idClient") Integer idClient,
            @QueryParam("idArtiste") Integer idArtiste,
            @QueryParam("codeTypeMarquage") String codeTypeMarquage) {
        MarquageArtiste artiste;
        try {
            artiste = marquageArtisteFacade.findMarqueByClientAndArtiste(codeTypeMarquage, idClient, idArtiste);
            return entityToDto(artiste);
        } catch (SignArtException ex) {
            Logger.getLogger(MarquageArtisteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public MarquageArtisteDto find(@PathParam("id") Integer id) {
        MarquageArtiste artiste = marquageArtisteFacade.find(id);
        return entityToDto(artiste);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MarquageArtisteDto> findAll() {
        List<MarquageArtisteDto> listDto = new ArrayList<>();
        List<MarquageArtiste> listEnt = marquageArtisteFacade.findAll();
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
    public List<MarquageArtiste> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return marquageArtisteFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(marquageArtisteFacade.count());
    }

    /**
     *
     * @param entity
     * @return
     */
    private MarquageArtisteDto entityToDto(MarquageArtiste entity) {
        if (entity == null) {
            return null;
        } else {
            MarquageArtisteDto dto = new MarquageArtisteDto();
            dto.setIdTypeMarquage(entity.getIdTypeMarquage().getId());
            dto.setCodeTypeMarquage(entity.getIdTypeMarquage().getCode());
            dto.setIdArtiste(entity.getIdArtiste().getId());
            dto.setIdClient(entity.getIdClient().getId());
            dto.setDateMarquage(entity.getDateMarquage());
            return dto;
        }
    }

    /**
     *
     * @param dto
     * @return
     */
    private MarquageArtiste dtoToEntity(MarquageArtisteDto dto) {
        MarquageArtiste entity = new MarquageArtiste();
        if (dto.getDateMarquage() != null) {
            entity.setDateMarquage(dto.getDateMarquage());
        }
        entity.setIdArtiste(artisteFacade.find(dto.getIdArtiste()));
        entity.setIdClient(clientFacade.find(dto.getIdClient()));
        entity.setIdTypeMarquage(typeMarquageFacade.findByCode(dto.getCodeTypeMarquage()));
        return entity;
    }
}
