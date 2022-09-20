/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.HistoriqueAbonnement;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.dto.EtatAbonnementDto;
import sn.modelsis.signart.dto.HistoriqueAbonnementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.HistoriqueAbonnementFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
@Path("historiqueAbonnement")
public class HistoriqueAbonnementREST {
    @Inject
    AbonnementFacade abonnementfacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    HistoriqueAbonnementFacade historiqueAbonnementFacade;
    
     
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(HistoriqueAbonnementDto dto) throws SignArtException {
        historiqueAbonnementFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public HistoriqueAbonnementDto find(@PathParam("id") Integer id) {
        HistoriqueAbonnement historique = historiqueAbonnementFacade.find(id);
        return entityToDto(historique);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test historique abonnement rest";
    }
    
    @GET
    @Path("abonnement/{idAbonnement}")
    @Produces({MediaType.APPLICATION_JSON})
    public HistoriqueAbonnementDto findByAbonnement(@PathParam("idAbonnement") Integer idAbonnement) throws SignArtException {
        HistoriqueAbonnementDto dto = new HistoriqueAbonnementDto();
        HistoriqueAbonnement historique = new HistoriqueAbonnement();
        try { 
             historique = historiqueAbonnementFacade.findByIdAbonnement(idAbonnement);
        }catch (final SignArtException e) {
            Logger.getLogger(AbonneREST.class.getName()).log(Level.SEVERE, "findByAbonnement/Exception", e);
            return dto;
        }
        return entityToDto(historique);
    }
    
   @PUT
    @Path("editHistoriqueAbonnement/{idHistorique}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPhoto(@PathParam("idHistorique") Integer idHistorique, HistoriqueAbonnementDto dto) throws SignArtException{
        HistoriqueAbonnement historique;
                historique = historiqueAbonnementFacade.findById(idHistorique);
                historique.setDateDebut(dto.getDateDebut());
                historique.setDateFin(dto.getDateFin());
                historique.setLibelle(dto.getLibelle());
                historiqueAbonnementFacade.edit(historique);             
                return Response.status(Response.Status.OK).entity(dto).build();
                 
    }
    
    @GET
    @Path("utilisateur/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
     public List<HistoriqueAbonnementDto> findAllByUtilisateur(@PathParam("idUser") Integer idUser) {
        List<HistoriqueAbonnementDto> listDto = new ArrayList<>();
        try {
            List<HistoriqueAbonnement> listEnt = historiqueAbonnementFacade.findAllByIdUtilisateur(idUser);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(FormationREST.class.getName()).log(Level.SEVERE, "findAllByUtilisateur/Exception", e);
            return listDto;
        }
    }
     
     private HistoriqueAbonnement dtoToEntity(HistoriqueAbonnementDto dto) throws SignArtException {
        
        HistoriqueAbonnement entity = new HistoriqueAbonnement();
       // entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setLibelle(dto.getLibelle());
        entity.setIdAbonnement(abonnementfacade.findById(dto.getIdAbonnement()));
        entity.setIdUtilisateur(utilisateurFacade.findById(dto.getIdUtilisateur()));
        return entity;
    }
    
    private HistoriqueAbonnementDto entityToDto(HistoriqueAbonnement entity){
        HistoriqueAbonnementDto dto = new HistoriqueAbonnementDto();
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setLibelle(entity.getLibelle());
        dto.setIdAbonnement(entity.getIdAbonnement().getId());
        dto.setIdUtilisateur(entity.getIdUtilisateur().getId());
        return dto;
    }
    
}
