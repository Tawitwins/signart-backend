/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

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
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.Licence;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.DelaiFacade;
import sn.modelsis.signart.facade.LicenceFacade;
import sn.penda.signart.dto.LicenceDto;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("terminal")
public class LicenceREST {
    
    @Inject
    LicenceFacade licenceFacade;
    @Inject
    AbonnementFacade abonnementFacade;
    @Inject
    AbonneFacade abonneFacade;
    @Inject
    DelaiFacade delaiFacade;
    
   /* @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LicenceDto dto) {
        licenceFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LicenceDto find(@PathParam("id") Integer id) {
        Licence licence = licenceFacade.find(id);
        return entityToDto(licence);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test terminal rest";
    }
    
    private Licence dtoToEntity(LicenceDto dto) throws SignArtException {
        
        Licence entity = new Licence();
        Abonnement abonnement = abonnementFacade.findById(dto.getIdAbonnement());
        Abonne abonne = abonneFacade.findById(abonnement.getIdAbonne().getId());
        Delai delai = delaiFacade.findById(abonnement.getIdDelai().getId());
        String username = abonne.getPrenom()+""+abonne.getNom()+""+abonne.getTelephone();
       
        int dateExp = delai.getNbMois();
        
       // entity.setId(dto.getId());
        //entity.setIdAbonnement(dto.getIdAbonnement());
        
        return entity;
    }
    
   /* private TerminalDto entityToDto(Terminal entity){
        TerminalDto dto = new TerminalDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setDescription(entity.getDescription());
        dto.setPrix(entity.getPrix());
        return dto;
    }*/
}
