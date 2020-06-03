package sn.modelsis.signart.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import org.apache.commons.lang3.StringUtils;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AccountInformation;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.utils.PasswordEncoder;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "SignArtPU")
    private EntityManager em;
    
     @Inject
    UtilisateurFacade userFacade;
    @Inject
    PasswordEncoder passwordEncoder;

    public UserFacadeREST() {
        super(Utilisateur.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Utilisateur entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Utilisateur entity) {
        super.edit(entity);
    }
    
    @PUT
    @Path("editPassword/{password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPassword(@PathParam("password") String newPassword, AccountInformation dto) {
        Utilisateur utilisateur = dtoToUserDetails(dto, newPassword);
        userFacade.edit(utilisateur);
        return Response.status(Response.Status.OK).build();
    }
    

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Utilisateur find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Utilisateur> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Utilisateur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private Utilisateur dtoToUserDetails(AccountInformation dto, String newPassword) {
        final Utilisateur foundUser = userFacade.findByMail(dto.getUserName());
        final String passwordEncoded = passwordEncoder.encodePassword(dto.getPassword(), dto.getUserName());
       
        if (StringUtils.equals(foundUser.getPassword(), passwordEncoded)) {
            //return entityToDtoAccount(foundUser);
            newPassword = passwordEncoder.encodePassword(newPassword, dto.getUserName());
            foundUser.setPassword(newPassword);
        }
        return foundUser;
    }
    
}
