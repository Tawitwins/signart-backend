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
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.ArtisteFormation;
import sn.modelsis.signart.Formation;
import sn.modelsis.signart.dto.ArtisteFormationDto;
import sn.modelsis.signart.dto.FormationDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.ArtisteFormationFacade;
import sn.modelsis.signart.facade.FonctionFacade;
import sn.modelsis.signart.facade.FormationFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("formation")
public class FormationREST {

    @Inject
    FormationFacade formationFacade;

    @Inject
    ArtisteFormationFacade artisteFormationFacade;
    
     @Inject
    ArtisteFacade artisteFacade;

   /* @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FormationDto dto) {
        formationFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, FormationDto dto) {
        formationFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        formationFacade.remove(formationFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public ArtisteFormationDto find(@PathParam("id") Integer id) {
//        Formation formation = formationFacade.find(id);
//        return entityToDto(formation);
//    }
    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ArtisteFormationDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<ArtisteFormationDto> listDto = new ArrayList<>();
        try {
            List<ArtisteFormation> listEnt = artisteFormationFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(formationFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
   /* private ArtisteFormationDto entityToDto(ArtisteFormation entity){
        ArtisteFormationDto dto = new ArtisteFormationDto();
        Formation form = new Formation();

        try {
            form = formationFacade.findById(entity.getArtisteFormationPK().getIdFormation());
        } catch (SignArtException ex) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (form != null) {
            dto.setLieu(form.getLieu());
            dto.setSigle(form.getSigle());
            dto.setLibelle(form.getLibelle());
        }
        dto.setSpecialisation(entity.getSpecialisation());
        dto.setAnneeDebut(entity.getAnneeDebut());
        dto.setAnneeFin(entity.getAnneeFin());
        
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
   /* private Formation dtoToEntity(FormationDto dto) {
        
        Formation entity = new Formation();
        entity.setId(dto.getId());
        entity.setLieu(dto.getLieu());
        entity.setSigle(dto.getSigle());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }*/
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FormationDto dto) {
        formationFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, FormationDto dto) {
        formationFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        formationFacade.remove(formationFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public FormationDto find(@PathParam("id") Integer id) {
        Formation formation = formationFacade.find(id);
        return entityToDto(formation);
    }
    
    
    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<FormationDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<FormationDto> listDto = new ArrayList<>();
        try {
            List<Formation> listEnt = formationFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(formationFacade.count());
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     
    private ArtisteFormationDto entityToDto(ArtisteFormation entity){
        ArtisteFormationDto dto = new ArtisteFormationDto();
        Formation form = new Formation();

        try {
            form = formationFacade.findById(entity.getArtisteFormationPK().getIdFormation());
        } catch (SignArtException ex) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (form != null) {
            dto.setLieu(form.getLieu());
            dto.setSigle(form.getSigle());
            dto.setLibelle(form.getLibelle());
        }
        dto.setSpecialisation(entity.getSpecialisation());
        dto.setAnneeDebut(entity.getAnneeDebut());
        dto.setAnneeFin(entity.getAnneeFin());
        
        return dto;
    }*/

    /**
     *
     * @param dto
     * @return
     */
    private Formation dtoToEntity(FormationDto dto) {
        
        Formation entity = new Formation();
       // entity.setId(dto.getId());
        entity.setLieu(dto.getLieu());
        entity.setSigle(dto.getSigle());
        entity.setLibelle(dto.getLibelle());
        entity.setEtatPublication(dto.getEtatPublication());
        entity.setSpecialisation(dto.getSpecialisation());
        entity.setAnneeDebut(dto.getAnneeDebut());
        entity.setAnneeFin(dto.getAnneeFin());
        entity.setIdArtiste(findArtiste(dto.getIdArtiste()));
        
        return entity;
    }
    
    private FormationDto entityToDto(Formation entity){
        FormationDto dto = new FormationDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setLieu(entity.getLieu());
        dto.setSigle(entity.getSigle());
        dto.setEtatPublication(entity.getEtatPublication());
        dto.setSpecialisation(entity.getSpecialisation());
        dto.setAnneeDebut(entity.getAnneeDebut());
        dto.setAnneeFin(entity.getAnneeFin());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        return dto;
    }
    
    private Artiste findArtiste(Integer idArtiste){
        return artisteFacade.find(idArtiste);
    }
    
}
