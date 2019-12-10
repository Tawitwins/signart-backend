package sn.modelsis.signart.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Client;
import sn.modelsis.signart.EtatArtiste;
import sn.modelsis.signart.EtatClient;
import sn.modelsis.signart.Profil;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AccountDto;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.EtatArtisteFacade;
import sn.modelsis.signart.facade.EtatClientFacade;
import sn.modelsis.signart.facade.PaysFacade;
import sn.modelsis.signart.facade.ProfilFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;
import sn.modelsis.signart.utils.PasswordEncoder;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("account")
public class AccountREST {

    @Inject
    UtilisateurFacade utilisateurFacade;
    @Inject
    ProfilFacade profilFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    ArtisteFacade artisteFacade;
    @Inject
    PaysFacade paysFacade;
    @Inject
    EtatArtisteFacade etatArtisteFacade;
    @Inject
    EtatClientFacade etatClientFacade;
    @Inject
    PasswordEncoder passwordEncoder;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    // @Produces({MediaType.APPLICATION_JSON})
    public Response create(AccountDto dto) {
        Utilisateur user = dtoToEntity(dto);
        user.setActif(Boolean.TRUE);
        final String passwordEncoded = passwordEncoder.encodePassword(dto.getPassword(), dto.getEmail());
        System.out.println("passwordEncoded " + passwordEncoded);
        user.setPassword(passwordEncoded);
        if (user.getIdProfil().getCode().equals(Profil.CODE_PROFIL_ARTISTE)) {
            user.setUserType(Utilisateur.CODE_USER_TYPE_ARTISTE);
        } else {
            user.setUserType(Utilisateur.CODE_USER_TYPE_CLIENT);
        }
        // utilisateurFacade.create(user);
        // Vérification du type d'utilisateur
        // if (user.getIdProfil().getCode().equals(Profil.CODE_PROFIL_ARTISTE)) {
        // //création de l'artiste
        // Artiste artiste = dtoToArtisteEntity(dto, user);
        // artiste.setIdEtatArtiste(etatArtisteFacade.findByCode(EtatArtiste.CODE_ETAT_ARTISTE_ACTIF));
        // artiste.setTelephone(dto.getMobile());
        // artisteFacade.create(artiste);
        // if (artiste.getId() != null) {
        // dto.setIdArtiste(artiste.getId());
        // }
        // } else {
        // création du client
        Client client = dtoToClientEntity(dto, user);
        client.setIdEtatClient(etatClientFacade.findByCode(EtatClient.CODE_ETAT_CLIENT_ACTIF));
        client.setTelephone(dto.getMobile());
        clientFacade.create(client);
        if (client.getId() != null) {
            dto.setIdClient(client.getId());
        }
        // }
        dto.setIdUser(user.getId());

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    private Utilisateur dtoToEntity(AccountDto dto) {
        Utilisateur entity = new Utilisateur();
        entity.setMail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        Profil profil = profilFacade.findByCode(dto.getCodeProfil());
        entity.setIdProfil(profil);
        return entity;
    }

    private Client dtoToClientEntity(AccountDto dto, Utilisateur user) {
        Client client = new Client();
        client.setPrenom(dto.getPrenom());
        client.setNom(dto.getNom());
        client.setIdUser(user);
        client.setSexe(dto.getGender());
        client.setTelephone(dto.getMobile());
        client.setIdPays(paysFacade.findByCode(dto.getCodePays()));

        return client;
    }

    // private Artiste dtoToArtisteEntity(AccountDto dto, Utilisateur user) {
    //     Artiste artiste = new Artiste();
    //     artiste.setPrenom(dto.getPrenom());
    //     artiste.setNom(dto.getNom());
    //     artiste.setIdUser(user);
    //     artiste.setTelephone(dto.getMobile());
    //     artiste.setIdPays(paysFacade.findByCode(dto.getCodePays()));

    //     return artiste;
    // }
}
