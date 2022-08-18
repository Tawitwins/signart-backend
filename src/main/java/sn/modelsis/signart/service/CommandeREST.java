package sn.modelsis.signart.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.*;
import sn.modelsis.signart.dto.*;
import sn.modelsis.signart.facade.*;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import sn.modelsis.signart.exception.SignArtException;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("commande")
public class CommandeREST {
    
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    ConfigStateFacade configStateFacade;
    @Inject
    EtatLigneCommandeFacade etatLigneCommandeFacade;
    @Inject
    EtatCommandeFacade etatCommandeFacade;
    @Inject
    EtatPaiementFacade etatPaiementFacade;
    @Inject
    LignePanierFacade lignePanierFacade;
    @Inject
    CommandeConverter commandeConverter;
    @Inject
    LignePanierConverter lignePanierConverter;
    @Inject
    ClientFacade clientFacade;
    @Inject
    DeviseFacade deviseFacade;
    @Inject
    PaiementFacade paiementFacade;
    @Inject
    PaiementConverter paiementConverter;
    @Inject
    LignePaiementConverter lignePaiementConverter;
    @Inject
    LigneCommandeConverter ligneCommandeConverter;
    @Inject
    ModePaiementFacade modePaiementFacade;
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(CommandeDto dto) throws SignArtException {
        commandeFacade.create(commandeConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, CommandeDto dto) throws SignArtException {
        commandeFacade.edit(commandeConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        commandeFacade.remove(commandeFacade.find(id));
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CommandeDto find(@PathParam("id") Integer id) {
        Commande commande = commandeFacade.findById(id);
        return commandeConverter.entityToDto(commande);
    }
    
    @GET
    @Path("client/{idClient}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <CommandeDto> findByIdClient(@PathParam("idClient") Integer idClient) {
       // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
       List<CommandeDto> listDto = new ArrayList<>();
        List<Commande> listEnt = commandeFacade.findByIdClient(idClient);
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> commandeConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

   /* @GET
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LigneCommandeDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LigneCommandeDto> listDto = new ArrayList<>();
        List<Commande> listEntTmp = commandeFacade.findAll();
        List<LigneCommande> listEnt = null;
        for (Commande commande : listEntTmp) {
            for (LigneCommande ligneCommande : commande.getLigneCommandeSet()) {
                if(ligneCommande.getIdOeuvre().getIdMagasin().getId() == idMagasin){
                    listEnt.add(ligneCommande);
                }
            }

        }
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneCommandeConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }*/
    
    @GET
    @Path("numero/{numero}")
    @Produces({MediaType.APPLICATION_JSON})
    public CommandeDto findByNumero(@PathParam("numero") String numero) {
        return commandeConverter.entityToDto(commandeFacade.findByNumero(numero));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<CommandeDto> findAll() {
        List<CommandeDto> listDto = new ArrayList<>();
        List<Commande> listEnt = commandeFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> commandeConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(commandeFacade.count());
    }
    
    @POST
    @Path("passer/{idClient}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response passerCommande(@PathParam("idClient") Integer idClient, PanierDto panierDto)
            throws SignArtException {
        CommandeDto dto;
        Set<LignePanierDto> listLignesPanierDto = panierDto.getLignesPanier();

        // List<LignePanier> listLignePanier =
        // lignePanierFacade.findLignePanierByClient(idClient);
        List<LignePanier> listLignePanier = new ArrayList<>();
        if (listLignesPanierDto != null && !listLignesPanierDto.isEmpty()) {
            for (LignePanierDto lignePanierDto : listLignesPanierDto) {
                listLignePanier.add(lignePanierConverter.dtoToEntity(lignePanierDto));
            }
        }
        if (listLignePanier != null && !listLignePanier.isEmpty()) {
            // Set<LignePanier> lignePanierSet = panier.getLignePanierSet();
            Set<LigneCommande> ligneCommandeSet = new HashSet<>();
            Commande commande = new Commande();
            commande.setDateCommande(LocalDate.now());
            // commande.setIdClient(listLignePanier.get(0).getIdPanier().getIdClient());
            commande.setIdClient(clientFacade.find(idClient));
            // commande.setIdDevise(listLignePanier.get(0).getIdPanier().getIdDevise());
            commande.setIdDevise(deviseFacade.findByCode("XOF"));
            commande.setDelaiLivraison(1);
            commande.setIdEtatCommande(etatCommandeFacade.findByCode("NOUVEAU"));
            LigneCommande ligneCommande;
            BigDecimal montant = BigDecimal.ZERO, fraisLivraison = BigDecimal.ZERO;
            for (LignePanier lignePanier : listLignePanier) {
                ligneCommande = new LigneCommande();
                ligneCommande.setIdCommande(commande);
                ligneCommande.setIdEtatLigneCommande(etatLigneCommandeFacade.findByCode("NOUVEAU"));
                ligneCommande.setIdOeuvre(lignePanier.getIdOeuvre());
                ligneCommande.setPrix(lignePanier.getPrix());
                ligneCommande.setQuantite(lignePanier.getQuantite());
                ligneCommandeSet.add(ligneCommande);
                montant = montant.add(lignePanier.getPrix().multiply(BigDecimal.valueOf(lignePanier.getQuantite())));
                fraisLivraison = fraisLivraison.add(lignePanier.getIdOeuvre().getFraisLivraison() != null
                        ? lignePanier.getIdOeuvre().getFraisLivraison()
                        : BigDecimal.ZERO);
            }
            commande.setMontant(montant);
            commande.setFraisLivraison(fraisLivraison);
            commande.setLigneCommandeSet(ligneCommandeSet);
            commande.setNumero("CMD000000");
            commande.setEtat("adresse");
            commandeFacade.create(commande);
            commande.setNumero("CMD000000" + commande.getId());
            commandeFacade.edit(commande);
            this.createPaiementAndLignesEntities(commande);
            dto = commandeConverter.entityToDto(commande);
            return Response.status(Response.Status.CREATED).entity(dto).build();
        } else {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.INFO, "Impossible de créér la commande");
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("Impossible de créér la commande")
                    .build();
        }
        
    }
    private void createPaiementAndLignesEntities(Commande commande){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        Paiement paiement = new Paiement();
        paiement.setCommande(commande);
        paiement.setDatePaiement(dateAjout);
        paiement.setIdEtatPaiement(etatPaiementFacade.findByCode("NOPAYE"));
        paiement.setIdModePaiement(modePaiementFacade.findByCode("MAGASIN"));
        paiement.setId(commande.getId());
        Set<LignePaiement> lignePaiementSet = new HashSet<>();
        LignePaiement lignePaiement;
        for (LigneCommande ligneCommande : commande.getLigneCommandeSet()) {
            lignePaiement = new LignePaiement();
            lignePaiement.setIdLigneCommande(ligneCommande);
            lignePaiement.setIdModePaiement(modePaiementFacade.findByCode("MAGASIN"));
            lignePaiement.setIdEtatPaiement(etatPaiementFacade.findByCode("NOPAYE"));
            lignePaiement.setDatePaiement(dateAjout);
            lignePaiement.setMontant(BigDecimal.valueOf(ligneCommande.getPrix().intValue()*ligneCommande.getQuantite()));
            lignePaiement.setIdPaiement(paiement);
            lignePaiementSet.add(lignePaiement);
        }
        paiement.setLignePaiementSet(lignePaiementSet);
        paiementFacade.create(paiement);
    }

   /* @POST
    @Path("updateAprèsPaiement/{token}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public String postForPaydunya(@PathParam("token") String token){
        return "error to bad request";
    }*/
    @POST
    @Path("updateAprèsPaiement")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public String postForPaydunya(MultivaluedMap<String, String> payDunyaInput){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        Commande myCommande = commandeFacade.findByToken(payDunyaInput.get("data[invoice][token]").get(0));
        myCommande.getLigneCommandeSet().forEach(ligneC ->{
         ligneC.setIdEtatLigneCommande(etatLigneCommandeFacade.findByCode("PAYEETNONLIVREE"));
        });
        commandeFacade.save(myCommande);
        Paiement myPaiement = paiementFacade.find(myCommande.getId());
        if(myPaiement == null){
            myPaiement = new Paiement();
            myPaiement.setId(myCommande.getId());
            myPaiement.setCommande(myCommande);
            myPaiement.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
        }
        myPaiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        myPaiement.setDatePaiement(dateAjout);
        //Set<LignePaiement> lignePaiementSet = new HashSet<>();
        for (LignePaiement lp :  myPaiement.getLignePaiementSet()) {
            lp.setDatePaiement(dateAjout);
            lp.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
            lp.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        }
        //myPaiement.setLignePaiementSet(lignePaiementSet);
        paiementFacade.save(myPaiement);
        return "Update succeeded";
    }
    @POST
    @Path("updateApresPaiementOrange")
    @Consumes({MediaType.APPLICATION_JSON})
    public String postForOrange(JsonObject response){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        System.out.println(response);
        JsonValue idtransaction = response.get("transactionId");
        JsonValue partner = response.get("partner");

        //String id = partner.("id").asText();
       /* Commande myCommande = commandeFacade.findByToken(payDunyaInput.get("data[invoice][token]").get(0));
        myCommande.getLigneCommandeSet().forEach(ligneC ->{
            ligneC.setIdEtatLigneCommande(etatLigneCommandeFacade.findByCode("PAYEETNONLIVREE"));
        });
        Paiement myPaiement = paiementFacade.find(myCommande.getId());
        if(myPaiement == null){
            myPaiement = new Paiement();
            myPaiement.setId(myCommande.getId());
            myPaiement.setCommande(myCommande);
            myPaiement.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
        }
        myPaiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        myPaiement.setDatePaiement(dateAjout);
        //Set<LignePaiement> lignePaiementSet = new HashSet<>();
        for (LignePaiement lp :  myPaiement.getLignePaiementSet()) {
            lp.setDatePaiement(dateAjout);
            lp.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
            lp.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        }
        //myPaiement.setLignePaiementSet(lignePaiementSet);
        paiementFacade.save(myPaiement);*/
        return "We got datas from om but no link with commane. So no update on data base";
    }
    
    @PUT
    @Path("next/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response next(@PathParam("id") Integer id) {
        Commande commande = commandeFacade.find(id);
        ConfigState configState = configStateFacade.findByEtat(commande.getEtat());
        if (configState != null) {
            commande.setEtat(configState.getSuivant());
            commandeFacade.edit(commande);
        }
        CommandeDto commandeDto = commandeConverter.entityToDto(commande);
        return Response.status(Response.Status.OK).entity(commandeDto).build();
    }
}
