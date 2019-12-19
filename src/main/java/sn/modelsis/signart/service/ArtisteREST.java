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
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Biographie;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.BiographieDto;
import sn.modelsis.signart.facade.ArtisteFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("artiste")
public class ArtisteREST {

    @Inject
    ArtisteFacade artisteFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Artiste entity) {
        artisteFacade.create(entity);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Artiste entity) {
        artisteFacade.edit(entity);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        artisteFacade.remove(artisteFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ArtisteDto find(@PathParam("id") Integer id) {
        Artiste artiste = artisteFacade.find(id);
        return entityToDto(artiste);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ArtisteDto> findAll() {
        List<ArtisteDto> listDto = new ArrayList<>();
        List<Artiste> listEnt = artisteFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    /*@GET
     @Path("artiste/{id}")
     //@Override
     @Produces({MediaType.APPLICATION_JSON})
     public List<ArtisteDto> findByArtiste(@PathParam("id") Integer id) {
     List<ArtisteDto> listDto = new ArrayList<>();
     List<Artiste> listEnt = artisteFacade.find(id);
     if (listEnt != null) {
     listEnt.stream().map((artiste) -> {
     return entityToDto(artiste);
     }).forEachOrdered((dto) -> {
     listDto.add(dto);
     });
     }
     return listDto;
     }*/
    @GET
    @Path("marque/{codeTypeMarquage}/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ArtisteDto> findMarqueByClient(@PathParam("codeTypeMarquage") String codeTypeMarquage, @PathParam("id") Integer idClient) {
        List<ArtisteDto> listDto = new ArrayList<>();
        try {
            List<Artiste> listEnt = artisteFacade.findMarqueByClient(codeTypeMarquage, idClient);
            if (listEnt != null) {
                listEnt.stream().map((artiste) -> {
                    return entityToDto(artiste);
                }).forEachOrdered((dto) -> {
                    listDto.add(dto);
                });
            }
            return listDto;
        } catch (SignArtException e) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, "Artiste/findMarqueByClient/SignArtException", e);
            return listDto;
        } catch (final Exception e) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, "Artiste/findMarqueByClient/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Artiste> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return artisteFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(artisteFacade.count());
    }

    private ArtisteDto entityToDto(Artiste entity) {
        ArtisteDto dto = new ArtisteDto();
        dto.setAdresse(entity.getAdresse());
        dto.setBiographie(entity.getBiographie());
        dto.setEtatArtiste(entity.getIdEtatArtiste().getLibelle());
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPays(entity.getIdPays().getLibelle());
        dto.setPrenom(entity.getPrenom());
        dto.setSurnom(entity.getSurnom());
        dto.setTelephone(entity.getTelephone());
        dto.setVille(entity.getVille());
        dto.setProfession(entity.getProfession());
        try {
            dto.setNbFans(artisteFacade.countMarqueArtiste("SUIV"));//TODO calculer
            dto.setNbOeuvres(artisteFacade.countOeuvre(entity.getId()));//TODO calculer
        } catch (SignArtException ex) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @GET
    @Path("biographie/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public BiographieDto findBiogByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        Biographie bigraphie = new Biographie();
        try {
            bigraphie = artisteFacade.findBiographieByArtiste(idArtiste);

        } catch (SignArtException ex) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entityToBiographieDto(bigraphie);
    }
}
