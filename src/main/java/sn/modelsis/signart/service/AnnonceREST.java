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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Annonce;
import sn.modelsis.signart.dto.AnnonceDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AnnonceFacade;
import sn.modelsis.signart.facade.ArtisteFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("annonce")
public class AnnonceREST {

    @Inject
    AnnonceFacade annonceFacade;
    
    @Inject
    ArtisteFacade artisteFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AnnonceDto dto) throws SignArtException {
        annonceFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, AnnonceDto dto) throws SignArtException {
        annonceFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        annonceFacade.remove(annonceFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AnnonceDto find(@PathParam("id") Integer id) {
        Annonce annonce = annonceFacade.find(id);
        return entityToDto(annonce);
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AnnonceDto> findAll() {
        List<AnnonceDto> listDto = new ArrayList<>();
        List<Annonce> listEnt = annonceFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    
    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AnnonceDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<AnnonceDto> listDto = new ArrayList<>();
        try {
            List<Annonce> listEnt = annonceFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(AnnonceREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(annonceFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private AnnonceDto entityToDto(Annonce entity) {
        AnnonceDto dto = new AnnonceDto();
        dto.setDescription(entity.getDescription());
        dto.setTitre(entity.getTitre());
        dto.setLieu(entity.getLieu());
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        dto.setEtatPublication(entity.getEtatPublication());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Annonce dtoToEntity(AnnonceDto dto) throws SignArtException {
        Annonce entity = new Annonce();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setTitre(dto.getTitre());
        entity.setLieu(dto.getLieu());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setIdArtiste(artisteFacade.findById(dto.getIdArtiste()));
        entity.setEtatPublication(dto.getEtatPublication());
        return entity;
    }
}
