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
import sn.modelsis.signart.ArtisteFonction;
import sn.modelsis.signart.Fonction;
import sn.modelsis.signart.dto.ArtisteFonctionDto;
import sn.modelsis.signart.dto.FonctionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFonctionFacade;
import sn.modelsis.signart.facade.FonctionFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("fonction")
public class FonctionREST {

    @Inject
    FonctionFacade fonctionFacade;

    @Inject
    ArtisteFonctionFacade artisteFonctionFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FonctionDto dto) {
        fonctionFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, FonctionDto dto) {
        fonctionFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        fonctionFacade.remove(fonctionFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ArtisteFonctionDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<ArtisteFonctionDto> listDto = new ArrayList<>();
        try {
            List<ArtisteFonction> listEnt = artisteFonctionFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FonctionREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(fonctionFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private ArtisteFonctionDto entityToDto(ArtisteFonction entity){
        ArtisteFonctionDto dto = new ArtisteFonctionDto();
        Fonction fonct = new Fonction();

        try {
            fonct = fonctionFacade.findById(entity.getArtisteFonctionPK().getIdFonction());
        } catch (SignArtException ex) {
            Logger.getLogger(FonctionREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (fonct != null) {
            dto.setId(fonct.getId());
            dto.setLibelle(fonct.getLibelle());
        }
        dto.setAnneeDebut(entity.getAnneeDebut());
        dto.setAnneeFin(entity.getAnneeFin());
        
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Fonction dtoToEntity(FonctionDto dto) {
        
        Fonction entity = new Fonction();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }
}
