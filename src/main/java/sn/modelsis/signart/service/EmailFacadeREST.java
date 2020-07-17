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
import sn.modelsis.signart.Email;
import sn.modelsis.signart.converter.EmailConverter;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.EmailFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("Email")
public class EmailFacadeREST {

    @Inject
    EmailFacade emailFacade;
    @Inject
    EmailConverter emailConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(EmailDto dto) throws SignArtException {
        Email entity = emailConverter.dtoToEntity(dto);
        emailFacade.create(entity);
        EmailDto dtoRes = emailConverter.entityToDto(entity);
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
    public Response edit(@PathParam("id") Integer id, EmailDto dto) {
        emailFacade.edit(emailConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        emailFacade.remove(emailFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public EmailDto find(@PathParam("id") Integer id) throws SignArtException {
        return emailConverter.entityToDto(emailFacade.find(id));
    }
    
    @GET
    @Path("theLast/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public EmailDto findbyEmail(@PathParam("to") String to) throws SignArtException {
        return emailConverter.entityToDto(emailFacade.findByToLast(to));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Email> findAll() {
        return emailFacade.findAll();
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Email> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return emailFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(emailFacade.count());
    }

}
