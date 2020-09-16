package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
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
import sn.modelsis.signart.Biographie;
import sn.modelsis.signart.dto.BiographieDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.BiographieFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("biographie")
public class BiographieREST {

    @Inject
    BiographieFacade biographieFacade;
    
    @Inject
    ArtisteFacade artisteFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(BiographieDto dto) throws SignArtException {
        biographieFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, BiographieDto dto) throws SignArtException {
        biographieFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        biographieFacade.remove(biographieFacade.find(id)); 
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("artisteAll/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<BiographieDto> findAllByArtiste(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        //Presentation dto = presentationFacade.findByArtiste(idArtiste);
         List<BiographieDto> listDto = new ArrayList<>();
         List<Biographie> listEnt = biographieFacade.findAllByArtiste(idArtiste);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }
    

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(biographieFacade.count());
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public BiographieDto find(@PathParam("id") Integer id) {
         Biographie biographie = biographieFacade.findById(id);
        return entityToDto(biographie);
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private BiographieDto entityToDto(Biographie entity){
        BiographieDto dto = new BiographieDto();
        dto.setId(entity.getId());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setLieuNaissance(entity.getLieuNaissance());
        dto.setNationalite(entity.getNationalite());
        dto.setLibelle(entity.getLibelle());
        dto.setEtatBiographie(entity.getEtatBiographie());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Biographie dtoToEntity(BiographieDto dto) throws SignArtException {
        
        Biographie entity = new Biographie();
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setLieuNaissance(dto.getLieuNaissance());
        entity.setNationalite(dto.getNationalite());
        // entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setEtatBiographie(dto.getEtatBiographie());
        entity.setIdArtiste(artisteFacade.findById(dto.getIdArtiste()));
        return entity;
    }
}
