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
import sn.modelsis.signart.Filmographie;
import sn.modelsis.signart.dto.FilmographieDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.FilmographieFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("filmographie")
public class FilmographieREST {

    @Inject
    FilmographieFacade filmographieFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FilmographieDto dto) {
        filmographieFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, FilmographieDto dto) {
        filmographieFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        filmographieFacade.remove(filmographieFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<FilmographieDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<FilmographieDto> listDto = new ArrayList<>();
        try {
            List<Filmographie> listEnt = filmographieFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FilmographieREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(filmographieFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private FilmographieDto entityToDto(Filmographie entity){
        FilmographieDto dto = new FilmographieDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setType(entity.getType());
        dto.setAuteur(entity.getAuteur());
        dto.setDuree(entity.getDuree());
        
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Filmographie dtoToEntity(FilmographieDto dto) {
        
        Filmographie entity = new Filmographie();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setType(dto.getType());
        entity.setAuteur(dto.getAuteur());
        entity.setDuree(dto.getDuree());
        return entity;
    }
}
