package sn.modelsis.signart.service;

import com.sun.jersey.multipart.FormDataParam;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.*;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.BiographieDto;
import sn.modelsis.signart.dto.ImageProfilDto;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("artiste")
public class ArtisteREST {

    @Inject
    ArtisteFacade artisteFacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    PaysFacade paysFacade;
    
    @Inject
    EtatArtisteFacade etatArtisteFacade;

    @Inject
    MagasinFacade magasinFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ArtisteDto dto) throws SignArtException {
        Artiste entity = dtoToEntity(dto);
        artisteFacade.create(entity);
        ArtisteDto dtoRes = entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ArtisteDto dto) throws SignArtException {
        Artiste artiste = artisteFacade.findById(id);
        artisteFacade.edit(dtoToEtityProfil(dto,artiste));
        return Response.status(Response.Status.OK).build();
    }
   
    @PUT
    @Path("updatePhoto/{idArtiste}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPhoto(@PathParam("idArtiste") Integer idArtiste, ImageProfilDto dto) throws SignArtException, IOException {
        Artiste artiste; 
            
            artiste = artisteFacade.findById(idArtiste);           
            //System.out.println(artiste+"+++++++++++++++++++++++++++++++++++++++artiste++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            String valeur = dto.getValue();
            //System.out.println(valeur+"+++++++++++++++++++++++++++++++++++++++valeur++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            final byte[] imageByte = Base64.decodeBase64(valeur.getBytes());
            //System.out.println(imageByte+"+++++++++++++++++++++++++++++++++++++++imageByte++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            //byte[] bytes = valeur.getBytes();
            //System.out.println(bytes+"+++++++++++++++++++++++++++++++++++++++bytes++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

           
            artiste.setPhoto(imageByte);
            artisteFacade.edit(artiste);
            return Response.status(Response.Status.OK).build();
       
    }
    
    /*@PUT
    @Path("updateprofil")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response upadteProfilArtiste(ArtisteDto dto) throws SignArtException {
        artisteFacade.updateProfil(dtoToEtityProfil(dto));
        return Response.status(Response.Status.OK).build();
    public Response edit(@PathParam("id") Integer id, ArtisteDto dto) {
        Artiste entity = dtoToEntity(dto);
        artisteFacade.edit(entity);
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    
    @PUT
    @Path("updatename")
    @Consumes({MediaType.APPLICATION_JSON})
    public void upadteNomArtiste(ArtisteDto dto) throws SignArtException {
        
        artisteFacade.updateNom(dtoToEtityProfil(dto));
        //return Response.status(Response.Status.OK).build();
    }*/

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
        System.out.println(artiste.getNom());
        return entityToDto(artiste);
    }
    
    @GET
    @Path("user/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ArtisteDto findByUser(@PathParam("id") Integer id) throws SignArtException {
        Utilisateur user = utilisateurFacade.find(id);
        Artiste artiste = artisteFacade.findByUserAdvanced(user.getId());
        return entityToDto(artiste);
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
    public List<ArtisteDto> findAll() throws SignArtException {
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
   /* @PUT
    @Path("updateAllIdentite/{idArtiste}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response replaceAllIdentite(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        Artiste entity = artisteFacade.findById(idArtiste);
            String nom = "";
            String prenom = "";
            String surnom = "";
            String identite = "";
            if(entity.getIdentite().equals("a")){
                prenom = entity.getPrenom().replaceAll("\\s","");
                nom = entity.getNom().replaceAll("\\s","");
                surnom = entity.getSurnom();
                if(entity.getSurnom().equals("a")){
                    surnom = "artiste000"+entity.getTelephone();
                }else{
                    surnom = entity.getSurnom().replaceAll("\\s","_");
                }
                identite = nom+"_"+prenom+"_"+surnom;
                entity.setIdentite(identite);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++"+entity.getIdentite()+"+++++++++++++++++++++++++++++++++++++++++++");
                artisteFacade.edit(entity);
            }
        
        return Response.status(Response.Status.OK).build();
    }*/

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

    
    
    public Artiste dtoToEntity(ArtisteDto dto) throws SignArtException {
        Artiste entity = new Artiste();
        entity.setAdresse(dto.getAdresse());
        entity.setBiographie(dto.getBiographie());
        entity.setIdEtatArtiste(etatArtisteFacade.findByLibelle(dto.getEtatArtiste()));
        entity.setId(dto.getId());
        entity.setIdUser(utilisateurFacade.findById(dto.getIdUser()));       
        entity.setNom(dto.getNom());
        if(dto.getPays() != null)
            entity.setIdPays(paysFacade.findByLibelle(dto.getPays()));
        entity.setPrenom(dto.getPrenom());
        entity.setSurnom(dto.getSurnom());

        if(dto.getIdMagasin() != null)
            entity.setIdMagasin(magasinFacade.findById(dto.getIdMagasin()));
        //entity.setIdMagasin(magasinFacade.findByNom(dto.getMagasin()));
        entity.setTelephone(dto.getTelephone());
        entity.setVille(dto.getVille());
        entity.setEmail(dto.getEmail());
        entity.setGenre(dto.getGenre());    
        entity.setNomGalerie(dto.getNomGalerie());   
        entity.setAdrGalerie(dto.getAdrGalerie());  
        entity.setVilleGalerie(dto.getVilleGalerie());  
        entity.setSpecialites(dto.getSpecialites());
        entity.setFormation(dto.getFormation());  
        entity.setExpositions(dto.getExpositions());
        entity.setProfession(dto.getProfession());
        String prenom = dto.getPrenom().replaceAll("\\s","");
        String nom = dto.getNom().replaceAll("\\s","");
        String surnom = "artiste00"+dto.getTelephone();
        String identite = nom+"_"+prenom+"_"+surnom;
        entity.setIdentite(identite);
        entity.setAnneeDebutCarrier(dto.getAnneeDebutCarrier());
        entity.setQualificationLevel(dto.getQualificationLevel());
        return entity;
    }
            
    public ArtisteDto entityToDto(Artiste entity) {

        ArtisteDto dto = new ArtisteDto();
        dto.setAdresse(entity.getAdresse());
        dto.setBiographie(entity.getBiographie());
        dto.setEtatArtiste(entity.getIdEtatArtiste().getLibelle());
        dto.setId(entity.getId());
        dto.setIdUser(entity.getIdUser().getId());
        dto.setNom(entity.getNom());
        dto.setPays(entity.getIdPays().getLibelle());
        dto.setPrenom(entity.getPrenom());
        dto.setSurnom(entity.getSurnom());
        if(entity.getIdMagasin() != null)
            dto.setIdMagasin(entity.getIdMagasin().getId());
        //dto.setMagasin(entity.getIdMagasin().getNom());
        dto.setTelephone(entity.getTelephone());
        dto.setVille(entity.getVille());
        dto.setEmail(entity.getEmail());
        dto.setGenre(entity.getGenre());
        dto.setNomGalerie(entity.getNomGalerie());
        dto.setAdrGalerie(entity.getAdrGalerie());
        dto.setVilleGalerie(entity.getVilleGalerie());
        dto.setSpecialites(entity.getSpecialites());
        dto.setFormation(entity.getFormation());
        dto.setExpositions(entity.getExpositions());
        dto.setIdentite(entity.getIdentite());
        dto.setAnneeDebutCarrier(entity.getAnneeDebutCarrier());
        dto.setQualificationLevel(entity.getQualificationLevel());
        
        
        dto.setProfession(entity.getProfession());
        try {
            dto.setNbFans(artisteFacade.countMarqueArtisteFan("SUIV",dto.getId()));//TODO calculer
            dto.setNbOeuvres(artisteFacade.countOeuvre(entity.getId()));//TODO calculer
        } catch (SignArtException ex) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }
    
    private Artiste dtoToEtityProfil(ArtisteDto dto, Artiste entity) throws SignArtException {
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setSurnom(dto.getSurnom());
        entity.setTelephone(dto.getTelephone());
        entity.setEmail(dto.getEmail());
        entity.setAdresse(dto.getAdresse());
        entity.setVille(dto.getVille());
        entity.setIdPays(findPays(dto.getPays()));
        entity.setIdMagasin(findMagasin(dto.getIdMagasin()));
        String prenom = dto.getPrenom().replaceAll("\\s","");
        String nom = dto.getNom().replaceAll("\\s","");
        String surnom = "artiste00"+dto.getTelephone();
        String identite = nom+"_"+prenom+"_"+surnom;
        entity.setIdentite(identite);
       
        
        return entity;
    }
    
    private Pays findPays(String libelle){
        return paysFacade.findByLibelle(libelle);
    }

    private Magasin findMagasin(Integer idMagasin) throws SignArtException {
        return magasinFacade.findById(idMagasin);
    }
    

    private BiographieDto entityToBiographieDto(Biographie entity) throws SignArtException {

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
    public BiographieDto findBiogByArtiste(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        Biographie bigraphie = new Biographie();
        try {
            bigraphie = artisteFacade.findBiographieByArtiste(idArtiste);

        } catch (SignArtException ex) {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entityToBiographieDto(bigraphie);
    }
}
