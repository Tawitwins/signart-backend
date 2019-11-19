package sn.modelsis.signart.service;

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
import sn.modelsis.signart.AbonnementNewsletter;
import sn.modelsis.signart.Newsletter;
import sn.modelsis.signart.dto.AbonnementNewsletterDto;
import sn.modelsis.signart.dto.NewsletterDto;
import sn.modelsis.signart.facade.AbonnementNewsletterFacade;
import sn.modelsis.signart.facade.NewsletterFacade;
import sn.modelsis.signart.facade.TypeNewsletterFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("newsletter")
public class NewsletterREST {

    @Inject
    NewsletterFacade newsletterFacade;
    @Inject
    AbonnementNewsletterFacade abonnementNewsletterFacade;
    @Inject
    TypeNewsletterFacade typeNewsletterFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(NewsletterDto dto) {
        newsletterFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, NewsletterDto dto) {
        newsletterFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            newsletterFacade.remove(newsletterFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(NewsletterREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public NewsletterDto find(@PathParam("id") Integer id) {
        Newsletter newsletter = newsletterFacade.find(id);
        return entityToDto(newsletter);
    }

    /*@GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<NewsletterDto> findByArtiste(@PathParam("idArtiste") Integer idArtiste) {
        List<NewsletterDto> listDto = new ArrayList<>();
        try {
            List<Newsletter> listEnt = newsletterFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (final SignArtException e) {
            Logger.getLogger(NewsletterREST.class.getName()).log(Level.SEVERE, "findByArtiste/Exception", e);
            return listDto;
        }
    }*/
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(newsletterFacade.count());
    }

    @POST
    @Path("abonnement")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AbonnementNewsletterDto dto) {
        AbonnementNewsletter entity = new AbonnementNewsletter();
        entity.setEmail(dto.getEmail());
        entity.setIdTypeNewsletter(typeNewsletterFacade.find(dto.getIdTypeNewsletter()));
        abonnementNewsletterFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    private NewsletterDto entityToDto(Newsletter entity) {
        NewsletterDto dto = new NewsletterDto();
        dto.setContenu(entity.getContenu());
        dto.setId(entity.getId());
        dto.setDateEnvoi(entity.getDateEnvoi());
        dto.setEnvoye(entity.getEnvoye());
        dto.setIdTypeNewsletter(entity.getIdTypeNewsletter().getId());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    private Newsletter dtoToEntity(NewsletterDto dto) {
        Newsletter entity = new Newsletter();
        entity.setId(dto.getId());
        entity.setContenu(dto.getContenu());
        entity.setDateEnvoi(dto.getDateEnvoi());
        entity.setEnvoye(dto.isEnvoye());
        entity.setIdTypeNewsletter(typeNewsletterFacade.find(dto.getIdTypeNewsletter()));
        return entity;
    }
}
