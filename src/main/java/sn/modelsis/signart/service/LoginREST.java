package sn.modelsis.signart.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import org.apache.commons.lang3.StringUtils;
import sn.modelsis.signart.AdminsTable;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.converter.AdminsTableConverter;
import sn.modelsis.signart.dto.AccountDto;
import sn.modelsis.signart.dto.AccountInformation;
import sn.modelsis.signart.dto.AdminsTableDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AdminsTableFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.tools.JwtService;
import sn.modelsis.signart.utils.PasswordEncoder;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("login")
public class LoginREST {

    @Inject
    UtilisateurFacade userFacade;
    @Inject
    AdminsTableFacade adminsTableFacade;
    @Inject
    AdminsTableConverter adminsTableConverter;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    JwtService jwtService;

    @POST
    //@Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public Response signIn(final AccountInformation account) throws SignArtException, NoSuchAlgorithmException {
        if (account == null || !account.isValid()) {
            throw new SignArtException("Nom d'utilisateur ou mot de passe invalide");
        }
        final Utilisateur foundUser = userFacade.findByMail(account.getUserName());
        final String passwordEncoded = passwordEncoder.encodePassword(account.getPassword(), account.getUserName());
        System.out.println("passwordEncoded " + passwordEncoded);

        if (foundUser == null || !StringUtils.equals(foundUser.getPassword(), passwordEncoded)) {
            throw new SignArtException("Nom d'utilisateur ou mot de passe invalide");
        }
        if (!foundUser.getActif()) {
            throw new SignArtException("Compte utilisateur n'est pas encore activé");
        }
        if(foundUser.getUserType().equals("CLIENT") ||foundUser.getUserType().equals("ARTISTE")) {
            try {
                final String token = jwtService.createJWT(foundUser);
                return Response.status(Response.Status.CREATED).entity(token).build();
            } catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException | JsonProcessingException e) {
                throw new SignArtException("Erreur de generation du token jwt, contacter l'administrateur" + e.getMessage());
            }
        } else {
            throw new SignArtException("Compte utilisateur ne peut pas se connecter cette plateforme admin");
        }
    }

    @GET
    @Path("adminInfos/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AdminsTableDto find(@PathParam("id") Integer idUser) throws SignArtException {
        return adminsTableConverter.entityToDto(adminsTableFacade.findByIdUser(idUser));
    }


    @POST
    @Path("adminLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(final AccountInformation account) throws SignArtException, NoSuchAlgorithmException {
        if (account == null || !account.isValid()) {
            throw new SignArtException("Nom d'utilisateur ou mot de passe invalide");
        }

        final Utilisateur foundUser = userFacade.findByMail(account.getUserName());
        final String passwordEncoded = passwordEncoder.encodePassword(account.getPassword(), account.getUserName());
        System.out.println("passwordEncoded " + passwordEncoded);

        if (foundUser == null || !StringUtils.equals(foundUser.getPassword(), passwordEncoded)) {
            throw new SignArtException("Nom d'utilisateur ou mot de passe invalide");
        }
        if (!foundUser.getActif()) {
            throw new SignArtException("Compte utilisateur n'est pas encore activé");
        }
        if(foundUser.getUserType().equals("CLIENT") ||foundUser.getUserType().equals("ARTISTE")) {
            throw new SignArtException("Compte utilisateur ne peut pas se connecter cette plateforme admin");
        }
        try {
            final String token = jwtService.createJWT(foundUser);
            return Response.status(Response.Status.CREATED).entity(token).build();
        } catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException | JsonProcessingException e) {
            throw new SignArtException("Erreur de generation du token jwt, contacter l'administrateur" + e.getMessage());
        }
        //return Response.status(Response.Status.OK).entity(foundUser).build();
    }

    @POST
    @Path("passwordFind")
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountDto testPassword(AccountInformation account){
        final Utilisateur foundUser = userFacade.findByMail(account.getUserName());
        final String passwordEncoded = passwordEncoder.encodePassword(account.getPassword(), account.getUserName());
        if (StringUtils.equals(foundUser.getPassword(), passwordEncoded)) {
            return entityToDtoAccount(foundUser);
        }
        return null;
    }

    private AccountDto entityToDtoAccount(Utilisateur entity){
        AccountDto dto = new AccountDto();
        dto.setIdUser(entity.getId());
        dto.setEmail(entity.getMail());

        return dto;
    }




}
