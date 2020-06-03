package sn.modelsis.signart.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AccountDto;
import sn.modelsis.signart.dto.AccountInformation;
import sn.modelsis.signart.exception.SignArtException;
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

        try {
            final String token = jwtService.createJWT(foundUser);
            return Response.status(Response.Status.CREATED).entity(token).build();
        } catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException | JsonProcessingException e) {
            throw new SignArtException("Erreur de generation du token jwt, contacter l'administrateur" + e.getMessage());
        }
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
