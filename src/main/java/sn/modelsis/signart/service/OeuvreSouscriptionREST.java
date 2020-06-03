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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.converter.OeuvreSouscriptionConverter;
import sn.modelsis.signart.dto.OeuvreDto;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.OeuvreSouscriptionFacade;

/**
 *
 * @author snfayemp
 */

@Stateless
@Path("oeuvreSouscription")
public class OeuvreSouscriptionREST {
    
    @Inject
    OeuvreSouscriptionFacade oeuvreSouscriptionFacade;
    @Inject
    OeuvreSouscriptionConverter oeuvreSouscriptionConverter;
    
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(OeuvreSouscriptionDto dto) throws SignArtException {
        oeuvreSouscriptionFacade.create(oeuvreSouscriptionConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        OeuvreSouscription entity = oeuvreSouscriptionFacade.findById(id); 
        OeuvreSouscriptionDto dtoRes = oeuvreSouscriptionConverter.entityToDto(entity);
        oeuvreSouscriptionFacade.remove(entity);
        return Response.status(Response.Status.OK).entity(dtoRes).build();
    }
    
    @GET
    @Path("artiste/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreSouscriptionDto> findByArtiste(@PathParam("id") Integer idArtiste) {
        List<OeuvreSouscriptionDto> listDto = new ArrayList<>();
        List<OeuvreSouscription> listEnt = oeuvreSouscriptionFacade.findByArtiste(idArtiste);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreSouscriptionConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreSouscriptionDto> findAll() {
        List<OeuvreSouscriptionDto> listDto = new ArrayList<>();
        List<OeuvreSouscription> listEnt = oeuvreSouscriptionFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> oeuvreSouscriptionConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    
    
    /*@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreSouscriptionDto find(@PathParam("id") Integer id) {
        OeuvreSouscription oeuvre = oeuvreSouscriptionFacade.find(id);
        return oeuvreSouscriptionConverter.entityToDto(oeuvre);
    }*/

   /* @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findAll() {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity -> 
                oeuvreConverter.entityToDto(entity)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }*/
    
    
}
