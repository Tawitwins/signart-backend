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
import sn.modelsis.signart.Exposition;
import sn.modelsis.signart.dto.ExpositionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.ExpositionFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("exposition")
public class ExpositionREST {

    @Inject
    ExpositionFacade expositionFacade;
    
    @Inject
    ArtisteFacade artisteFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ExpositionDto dto) throws SignArtException {
        expositionFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ExpositionDto dto) throws SignArtException {
        expositionFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        expositionFacade.remove(expositionFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ExpositionDto findById(@PathParam("id") Integer id) {
        Exposition exposition = expositionFacade.find(id);
        
        return entityToDto(exposition);
    }

    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ExpositionDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<ExpositionDto> listDto = new ArrayList<>();
        try {
            List<Exposition> listEnt = expositionFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(ExpositionREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(expositionFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private ExpositionDto entityToDto(Exposition entity) {
        ExpositionDto dto = new ExpositionDto();
        dto.setId(entity.getId());
        dto.setAdresse(entity.getAdresse());
        dto.setTitre(entity.getTitre());
        dto.setDescription(entity.getDescription());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setType(entity.getType());
        dto.setEtatExposition(entity.getEtatExposition());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Exposition dtoToEntity(ExpositionDto dto) throws SignArtException {
        Exposition entity = new Exposition();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setType(dto.getType());
        entity.setEtatExposition(dto.getEtatExposition());
        entity.setIdArtiste(artisteFacade.findById(dto.getIdArtiste()));
        return entity;
    }
}
