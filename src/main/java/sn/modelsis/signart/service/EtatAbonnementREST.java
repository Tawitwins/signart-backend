/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
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
import sn.modelsis.signart.EtatAbonnement;
import sn.modelsis.signart.dto.EtatAbonnementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EtatAbonnementFacade;

/**
 *
 * @author Pendaaa
 */

@Stateless
@Path("etatAbonnement")
public class EtatAbonnementREST {
    
    @Inject
    EtatAbonnementFacade etatAbonnementFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(EtatAbonnementDto dto) {
        etatAbonnementFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public EtatAbonnementDto find(@PathParam("id") Integer id) {
        EtatAbonnement delai = etatAbonnementFacade.find(id);
        return entityToDto(delai);
    }
    
    @GET
    @Path("libelle/{libelle}")
    @Produces({MediaType.APPLICATION_JSON})
    public EtatAbonnementDto findByLibelle(@PathParam("libelle") String libelle) throws SignArtException {
        EtatAbonnement delai = etatAbonnementFacade.findByLibelle(libelle);
        return entityToDto(delai);
    }
    
     @GET
    @Path("code/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public EtatAbonnementDto findByCode(@PathParam("code") String code) throws SignArtException {
        EtatAbonnement delai = etatAbonnementFacade.findByCode(code);
        return entityToDto(delai);
    }
    
    @GET
    public String test(@PathParam("id") Integer id) {
        return "test etat abonnement rest";
    }
    
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<EtatAbonnementDto> findAll() {
        List<EtatAbonnementDto> listDto = new ArrayList<>();
            List<EtatAbonnement> listEnt = etatAbonnementFacade.findAll();
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        
    }
    
    private EtatAbonnement dtoToEntity(EtatAbonnementDto dto) {
        
        EtatAbonnement entity = new EtatAbonnement();
       // entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        return entity;
    }
    
    private EtatAbonnementDto entityToDto(EtatAbonnement entity){
        EtatAbonnementDto dto = new EtatAbonnementDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    
}
