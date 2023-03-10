/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.ListeSelection;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AbonneDto;
import sn.modelsis.signart.dto.DelaiDto;
import sn.modelsis.signart.dto.ListeSelectionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("abonne")
public class AbonneREST {
    
    @Inject
    AbonneFacade abonnefacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AbonneDto dto) {
        abonnefacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonneDto find(@PathParam("id") Integer id) {
        Abonne abonne = abonnefacade.find(id);
        return entityToDto(abonne);
    }
    
    @GET
    @Path("utilisateur/{idUtilisateur}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonneDto findByUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur) throws SignArtException {
        AbonneDto listDto = new AbonneDto();
        Abonne abonne = new Abonne();
        try { 
             abonne = abonnefacade.findByUtilisateur(idUtilisateur);
        }catch (final SignArtException e) {
            Logger.getLogger(AbonneREST.class.getName()).log(Level.SEVERE, "findByIdUtilisateur/Exception", e);
            return listDto;
        }
        return entityToDto(abonne);
    }
    
  
    
    @GET
    @Path("getAllAbonne/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
     public List<AbonneDto> findAllByUtilisateur(@PathParam("idUser") Integer idUser) {
        List<AbonneDto> listDto = new ArrayList<>();
        try {
            List<Abonne> listEnt = abonnefacade.findAllByIdUtilisateur(idUser);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            Collections.reverse(listDto);
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, "findAllByUtilisateur/Exception", e);
            return listDto;
        }
    }
    
    @GET
    @Path("listeSelection/{idListeSelection}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonneDto findByListe(@PathParam("idListeSelection") Integer idListeSelection) throws SignArtException {
        AbonneDto listDto = new AbonneDto();
        Abonne abonne = new Abonne();
        try { 
             abonne = abonnefacade.findByIdListeSelection(idListeSelection);
        }catch (final SignArtException e) {
            Logger.getLogger(AbonneREST.class.getName()).log(Level.SEVERE, "findByIdListeSelection/Exception", e);
            return listDto;
        }
        return entityToDto(abonne);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test abonne rest";
    }
    
    private Abonne dtoToEntity(AbonneDto dto) {
        
        Abonne entity = new Abonne();
       // entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setPays(dto.getPays());
        entity.setRegion(dto.getRegion());
        entity.setVille(dto.getVille());
        entity.setAdresse(dto.getAdresse());
        entity.setIdListeSelection(findListeSelection(dto.getIdListeSelection()));
        entity.setIdUtilisateur(findUtilisateur(dto.getIdUtilisateur()));
        return entity;
    }
    
    private AbonneDto entityToDto(Abonne entity){
        AbonneDto dto = new AbonneDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setPays(entity.getPays());
        dto.setRegion(entity.getRegion());
        dto.setVille(entity.getVille());
        dto.setAdresse(entity.getAdresse());
        dto.setIdListeSelection(entity.getIdListeSelection().getId());
        dto.setIdUtilisateur(entity.getIdUtilisateur().getId());
        return dto;
    }
    
    private Utilisateur findUtilisateur(Integer idUtilisateur){
        return utilisateurFacade.find(idUtilisateur);
    }
    
    private ListeSelection findListeSelection(Integer idListeSelection){
        return listeSelectionFacade.find(idListeSelection);
    }
    
    
}
