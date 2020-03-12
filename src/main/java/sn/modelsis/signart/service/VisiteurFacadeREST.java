package sn.modelsis.signart.service;

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
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.converter.VisiteurConverter;
import sn.modelsis.signart.dto.VisiteurDto;
import sn.modelsis.signart.facade.VisiteurFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("Visiteur")
public class VisiteurFacadeREST {

    @Inject
    VisiteurFacade visiteurFacade;
    @Inject
    VisiteurConverter visiteurConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(VisiteurDto dto) {
        Visiteur entity = visiteurConverter.dtoToEntity(dto);
        visiteurFacade.create(entity);
        VisiteurDto dtoRes = visiteurConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    /*@POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Visiteur entity) {
        visiteurFacade.create(entity);
        return Response.status(Response.Status.CREATED).build();
    }*/

    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, VisiteurDto dto) {
        visiteurFacade.edit(visiteurConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        visiteurFacade.remove(visiteurFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public VisiteurDto find(@PathParam("id") Integer id) {
        return visiteurConverter.entityToDto(visiteurFacade.find(id));
    }

    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Visiteur> findAll() {
        return visiteurFacade.findAll();
    }

    /*@GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientDto> findAll(@PathParam("idArtiste") Integer idArtiste) {
        try {
            //return clientFacade.findByArtiste(idArtiste);
            List<ClientDto> listDto = new ArrayList<>();
            List<Client> listEnt = clientFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> clientConverter.entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (SignArtException ex) {
            Logger.getLogger(VisiteurFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Visiteur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return visiteurFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(visiteurFacade.count());
    }

}
