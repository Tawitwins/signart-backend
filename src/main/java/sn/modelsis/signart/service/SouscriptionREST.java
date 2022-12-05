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
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Biographie;
import sn.modelsis.signart.Souscription;
import sn.modelsis.signart.dto.BiographieDto;
import sn.modelsis.signart.dto.SouscriptionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.MagasinFacade;
import sn.modelsis.signart.facade.PaysFacade;
import sn.modelsis.signart.facade.SouscriptionFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("souscription")
public class SouscriptionREST {

    @Inject
    SouscriptionFacade souscriptionFacade;
    @Inject
    MagasinFacade magasinFacade;
    
    @Inject
    PaysFacade paysFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(SouscriptionDto dto) throws SignArtException {
        Souscription entity = dtoToEntity(dto);
        souscriptionFacade.create(entity);
        SouscriptionDto resDto = entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(resDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, SouscriptionDto dto) throws SignArtException {
        Souscription entity = dtoToEntity(dto);
        souscriptionFacade.edit(entity);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Souscription entity = souscriptionFacade.find(id);
        SouscriptionDto dtoRes = entityToDto(entity);
        souscriptionFacade.remove(entity);
        return Response.status(Response.Status.OK).entity(dtoRes).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public SouscriptionDto find(@PathParam("id") Integer id) {
        Souscription souscription = souscriptionFacade.find(id);
        return entityToDto(souscription);
    }
    


    /*@GET
    @Path("user/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public ArtisteDto findByUser(@PathParam("idUser") Integer idUser) throws SignArtException {
        if (idUser == null || idUser == 0) {
            throw new SignArtException("Utilisateur invalide!");
        }
        return this.entityToDto(artisteFacade.findByUser(idUser));
    }*/
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public List<SouscriptionDto> findAll() {
        List<SouscriptionDto> listDto = new ArrayList<>();
        List<Souscription> listEnt = souscriptionFacade.findAll();
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
    public List<SouscriptionDto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SouscriptionDto> listDto = new ArrayList<>();
        List<Souscription> listEnt = souscriptionFacade.findRange(new int[]{from, to});
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
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(souscriptionFacade.count());
    }

    
    
    public Souscription dtoToEntity(SouscriptionDto dto) throws SignArtException {
        Souscription entity = new Souscription();
        entity.setId(dto.getId());    
        entity.setNom(dto.getNom());
        entity.setCodePays(dto.getCodePays());
        entity.setPrenom(dto.getPrenom());
        entity.setTelephone(dto.getTelephone());
        entity.setVille(dto.getVille());
        entity.setEmail(dto.getEmail());
        entity.setGenre(dto.getGenre());    
        entity.setNomGalerie(dto.getNomGalerie());   
        entity.setAdrGalerie(dto.getAdrGalerie());  
        entity.setSpecialites(dto.getSpecialites());
        entity.setFormation(dto.getFormation());  
        entity.setExpositions(dto.getExpositions());
        if(dto.getIdMagasin() != null){
            entity.setIdMagasin(magasinFacade.findById(dto.getIdMagasin()));
        }
        return entity;
    }
            
    public SouscriptionDto entityToDto(Souscription entity) {
        SouscriptionDto dto = new SouscriptionDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setCodePays(entity.getCodePays());
        dto.setPrenom(entity.getPrenom());
        dto.setTelephone(entity.getTelephone());
        dto.setVille(entity.getVille());
        dto.setEmail(entity.getEmail());
        dto.setGenre(entity.getGenre());
        dto.setNomGalerie(entity.getNomGalerie());
        dto.setAdrGalerie(entity.getAdrGalerie());
        dto.setSpecialites(entity.getSpecialites());
        dto.setFormation(entity.getFormation());
        dto.setExpositions(entity.getExpositions());
        if(entity.getIdMagasin() != null){
            dto.setIdMagasin(entity.getIdMagasin().getId());
        }
        /*try {
            dto.setNbFans(artisteFacade.countMarqueArtiste("SUIV"));//TODO calculer
            dto.setNbOeuvres(artisteFacade.countOeuvre(entity.getId()));//TODO calculer
        } catch (SignArtException ex) {
            Logger.getLogger(SouscriptionREST.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return dto;
    }

    private BiographieDto entityToBiographieDto(Biographie entity) {

        if (entity == null) {
            return null;
        } else {
            BiographieDto dto = new BiographieDto();
            dto.setId(entity.getId());
            dto.setDateNaissance(entity.getDateNaissance());
            dto.setLieuNaissance(entity.getLieuNaissance());
            dto.setNationalite(entity.getNationalite());

            return dto;
        }
    }
}
