package sn.modelsis.signart.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sn.modelsis.signart.*;
import sn.modelsis.signart.dto.AccountDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;
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
    AdminsTableFacade adminsTableFacade;
    @Inject
    MagasinFacade magasinFacade;
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;
    @Inject
    AgentFacade agentFacade;
    @Inject
    PasswordEncoder passwordEncoder;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    //@Produces({MediaType.APPLICATION_JSON})
    public Response create(AccountDto dto) throws SignArtException {
       if(!isExistEmail(dto.getEmail())){
           Utilisateur user = dtoToEntity(dto);
           user.setActif(Boolean.TRUE);
           final String passwordEncoded = passwordEncoder.encodePassword(dto.getPassword(), dto.getEmail());
           System.out.println("passwordEncoded " + passwordEncoded);
           user.setPassword(passwordEncoded);
           utilisateurFacade.create(user);
           switch (user.getIdProfil().getCode()) {
               case Profil.CODE_PROFIL_ARTISTE:
                   user.setUserType(Utilisateur.CODE_USER_TYPE_ARTISTE);
                   //création de l'artiste
                   Artiste artiste = artisteFacade.findById(dto.getIdArtiste());//dtoToArtisteEntity(dto, user);
                   artiste.setIdEtatArtiste(etatArtisteFacade.findByCode(EtatArtiste.CODE_ETAT_ARTISTE_ACTIF));
                   artiste.setTelephone(dto.getMobile());
                   artiste.setIdUser(user);
                   artiste = artisteFacade.save(artiste);
                   if (artiste.getId() != null) {
                       dto.setIdArtiste(artiste.getId());
                   }   break;
               //utilisateurFacade.create(user);
               //Vérification du type d'utilisateur
               case Profil.CODE_PROFIL_ADMIN:
                   user.setUserType(Utilisateur.CODE_USER_TYPE_ADMIN);
                   AdminsTable admin= dtoToAdminEntity(dto,user);
                   adminsTableFacade.create(admin);
                   if (admin.getId() != null) {
                       dto.setIdAdmin(admin.getId());
                   }   break;
               case Profil.CODE_PROFIL_AGENT_LIVREUR:
               case Profil.CODE_PROFIL_AGENT_TECHABMNT:
               case Profil.CODE_PROFIL_AGENT_GESTABMNT:
               case Profil.CODE_PROFIL_AGENT_GESTSTOCK:
               case Profil.CODE_PROFIL_AGENT_CAISSIER:
                   user.setUserType(user.getIdProfil().getCode());
                   //création de l'agent
                   Agent agent = dtoToAgentEntity(dto, user);
                   agentFacade.create(agent);
                   if (agent.getId() != null) {
                       dto.setIdAgent(agent.getId());
                   }   break;
               default:
                   user.setUserType(Utilisateur.CODE_USER_TYPE_CLIENT);
                   //création du client
                   Client client = dtoToClientEntity(dto, user);
                   client.setIdEtatClient(etatClientFacade.findByCode(EtatClient.CODE_ETAT_CLIENT_ACTIF));
                   client.setTelephone(dto.getMobile());
                   clientFacade.create(client);
                   if (client.getId() != null) {
                       dto.setIdClient(client.getId());
                   }   break;

           }

           dto.setIdUser(user.getId());

           return Response.status(Response.Status.CREATED).entity(dto).build();
       }
       dto.setExisteEmail(true);
       dto.setCodeProfil(null);
       dto.setPassword(null);
       return Response.status(Response.Status.BAD_REQUEST).entity(dto).build();
    }

    private AccountDto agents(AccountDto dto){
        return dto;
    }
    private Utilisateur dtoToEntity(AccountDto dto) {
        Utilisateur entity = new Utilisateur();
        entity.setMail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        Profil profil = profilFacade.findByCode(dto.getCodeProfil());
        entity.setIdProfil(profil);
        entity.setUserType(dto.getCodeProfil());
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

    private Artiste dtoToArtisteEntity(AccountDto dto, Utilisateur user) {
        Artiste artiste = new Artiste();
        artiste.setPrenom(dto.getPrenom());
        artiste.setNom(dto.getNom());
        artiste.setIdUser(user);
        artiste.setTelephone(dto.getMobile());
        artiste.setIdPays(paysFacade.findByCode(dto.getCodePays()));

        return artiste;
    }
    private AdminsTable dtoToAdminEntity(AccountDto dto, Utilisateur user){
        AdminsTable admin = new AdminsTable();
        admin.setPrenom(dto.getPrenom());
        admin.setNom(dto.getNom());
        admin.setTelephone(dto.getTelephone());
        admin.setEmail(dto.getEmail());
        admin.setAdresse(dto.getAdresse());
        admin.setId(dto.getIdAdmin());
        if(user.getId() != null)
            admin.setIdUser(utilisateurFacade.findById(user.getId()));
        return admin;
    }
    private Agent dtoToAgentEntity(AccountDto dto, Utilisateur user) throws SignArtException {
        Agent agent = new Agent();
        agent.setPrenom(dto.getPrenom());
        agent.setNom(dto.getNom());
        agent.setSurnom(dto.getSurnom());
        agent.setTelephone(dto.getTelephone());
        agent.setEmail(dto.getEmail());
        agent.setAdresse(dto.getAdresse());
        agent.setVille(dto.getVille());
        agent.setId(dto.getIdAgent());
        agent.setIdMagasin(magasinFacade.findById(dto.getIdMagasin()));
        agent.setIdServiceDeLivraison(serviceLivraisonFacade.findById(dto.getIdServiceLivraison()));
        agent.setSurnom(dto.getSurnom());
        agent.setIdProfil(profilFacade.findByCode(dto.getCodeProfil()));
        if(user.getId() != null)
            agent.setIdUser(utilisateurFacade.findById(user.getId()));
        return agent;
    }

    public boolean isExistEmail(String email){
         if(utilisateurFacade.findByMail(email) != null) return true;
        return false;
    }
}
