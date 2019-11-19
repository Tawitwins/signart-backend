package sn.modelsis.signart.tools;

import java.io.UnsupportedEncodingException;
import javax.ejb.Stateless;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Client;
import static sn.modelsis.signart.Client.USER_TYPE_ARTISTE;
import static sn.modelsis.signart.Client.USER_TYPE_CLIENT;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.JwtSubject;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.ClientFacade;

@Stateless
public class JwtService {
    
    public static final String ISSUER = "signart";
    public static final String SECRET = "CLDE087732A";

    @Inject
    ClientFacade clientFacade;
    @Inject
    ArtisteFacade artisteFacade;

    /**
     * Create JWT from user
     *
     * @param user
     * @param secret
     * @param issuer
     * @return
     * @throws UnsupportedEncodingException
     * @throws JWTCreationException
     * @throws IllegalArgumentException
     * @throws JsonProcessingException
     */
    public String createJWT(Utilisateur user, String secret, String issuer) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String prenom = "", nom = "";
        if (user.getUserType() != null) {
            if (user.getUserType().equals(USER_TYPE_CLIENT)) {
                Client cli;
                try {
                    cli = clientFacade.findByUser(user.getId());
                    prenom = cli.getPrenom();
                    nom = cli.getNom();
                } catch (SignArtException ex) {
                    Logger.getLogger(JwtService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (user.getUserType().equals(USER_TYPE_ARTISTE)) {
                Artiste art;
                try {
                    art = artisteFacade.findByUser(user.getId());
                    prenom = art.getPrenom();
                    nom = art.getNom();
                } catch (SignArtException ex) {
                    Logger.getLogger(JwtService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String subject = mapper.writeValueAsString(new JwtSubject(user, prenom, nom));
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withIssuedAt(new Date())
                //.withExpiresAt(expiresAt);
                .sign(Algorithm.HMAC512(secret));
    }
    
    /**
     * Create JWT from user
     *
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     * @throws JWTCreationException
     * @throws IllegalArgumentException
     * @throws JsonProcessingException
     */
    public String createJWT(Utilisateur user) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String prenom = "", nom = "";
        if (user.getUserType() != null) {
            if (user.getUserType().equals(USER_TYPE_CLIENT)) {
                Client cli;
                try {
                    cli = clientFacade.findByUser(user.getId());
                    prenom = cli.getPrenom();
                    nom = cli.getNom();
                } catch (SignArtException ex) {
                    Logger.getLogger(JwtService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (user.getUserType().equals(USER_TYPE_ARTISTE)) {
                Artiste art;
                try {
                    art = artisteFacade.findByUser(user.getId());
                    prenom = art.getPrenom();
                    nom = art.getNom();
                } catch (SignArtException ex) {
                    Logger.getLogger(JwtService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String subject = mapper.writeValueAsString(new JwtSubject(user, prenom, nom));
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(subject)
                .withIssuedAt(new Date())
                //.withExpiresAt(expiresAt);
                .sign(Algorithm.HMAC512(SECRET));
    }
}
