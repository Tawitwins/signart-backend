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
    LigneCommandeFacade ligneCommandeFacade;
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
    @Inject
    EtatAbonnementFacade etatAbonnementFacade;
    @Inject
    AbonnementFacade abonnementFacade;
    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    ClientConverter clientConverter;
    @Inject
    ParametrageFacade parametrageFacade;
    @Inject
    ParametreAlgoFacade parametreAlgoFacade;
    @Inject
    ParametreAlgoREST parametreAlgoREST;
    @Inject
    ParametreAlgoConverteur parametreAlgoConverteur;
    @Inject
    CoefficientParametrageFacade coefficientParametrageFacade;
    @Inject
    CoefficientParametrageConverter coefficientParametrageConverter;
    @Inject
    OeuvreConverter oeuvreConverter;
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

    @GET
    //@Path("magasin/{idMagasin}/{isLivreur}")
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <CommandeDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin) {
        List<CommandeDto> listDto = new ArrayList<>();
        List<LigneCommande> listEntTmp = ligneCommandeFacade.findAll();
        Set<Commande> listEnt = new HashSet();
        for (LigneCommande ligneC : listEntTmp) {
            if(ligneC.getIdOeuvre().getIdMagasin().getId() == idMagasin){
                listEnt.add(ligneC.getIdCommande());
            }
        }
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
        Collections.reverse(listDto);
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
            LigneCommande ligneCommande = null;
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
    @Path("updateApresPaiement")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public String postForPaydunya(MultivaluedMap<String, String> payDunyaInput) throws SignArtException {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());

        Commande myCommande = commandeFacade.findByToken(payDunyaInput.get("data[invoice][token]").get(0));
        Abonnement abonnement = abonnementFacade.findByToken(payDunyaInput.get("data[invoice][token]").get(0));
        LignePaiement lignePaiement = lignePaiementFacade.findByToken(payDunyaInput.get("data[invoice][token]").get(0));

        if(myCommande != null){
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
        } else if(abonnement != null){
            abonnement.setEtatAbonnement(etatAbonnementFacade.findByCode("PAYE"));
            abonnementFacade.edit(abonnement);
        } else{
            lignePaiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
            lignePaiement.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
            lignePaiementFacade.edit(lignePaiement);
        }

        return "Update succeeded ";
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

    @GET
    @Path("{idCommande}/client")
    @Consumes({MediaType.APPLICATION_JSON})
    public  ClientDto findClientByIdCommande(@PathParam("idCommande") Integer idCommande){
        Client client = clientFacade.find(commandeFacade.find(idCommande).getIdClient().getId());
        return clientConverter.entityToDto(client);
    }


    @GET
    @Path("getFraisLivraison/{commandeId}")
    @Produces({MediaType.APPLICATION_JSON})
    public BigDecimal calculFraisLivraison(@PathParam("commandeId") Integer commandeId){
        getPrixBase(commandeId, "OEUVRE");

        BigDecimal fraisLiv = getPrixBase(commandeId, "OEUVRE").add(getPrixBase(commandeId, "TARIFICATION"));
        return fraisLiv.divide(new BigDecimal(2));
    }
    public BigDecimal getPrixBase(Integer id, String type){

        BigDecimal prixBaseOeuvres = BigDecimal.ZERO;
        BigDecimal prixOeuvre = BigDecimal.ZERO;
        BigDecimal prixBaseOeuvre = BigDecimal.ZERO;
        BigDecimal prixBaseTarification = BigDecimal.ONE; // quel est le prix de base de la tarification
        BigDecimal quotient = BigDecimal.ONE;
        BigDecimal coeffParamZone = BigDecimal.ZERO;
        BigDecimal coeffParamDistance = BigDecimal.ZERO;
        BigDecimal coeffParamPoids = BigDecimal.ZERO;
        BigDecimal coeffParamDim = BigDecimal.ZERO;

        Commande commande = commandeFacade.find(id);
        commande = commande;
        Set<LigneCommande> ligneCommandeSet = commande.getLigneCommandeSet();
        BigDecimal nombreTotalOeuvre = BigDecimal.valueOf(ligneCommandeSet.size());

        if (ligneCommandeSet != null && !ligneCommandeSet.isEmpty()) {
            switch (type){
                case "OEUVRE":
                    for (LigneCommande ligneCommande : ligneCommandeSet) {
                        Oeuvre oeuvre = ligneCommande.getIdOeuvre();
                        ligneParam(oeuvre,null, "POIDS");
                        coeffParamPoids = BigDecimal.valueOf(ligneParam(oeuvre,null, "POIDS").getCoefficientParam().getValeurParametre());
                        coeffParamDim = BigDecimal.valueOf(ligneParam(oeuvre, null,"DIMENSIONS").getCoefficientParam().getValeurParametre());
                        BigDecimal baseNote = BigDecimal.valueOf(ligneParam(oeuvre, null,"DIMENSIONS").getBaseNote());
                        BigDecimal notePoids = BigDecimal.valueOf(ligneParam(oeuvre,null, "POIDS").getBaseNote()); //getNote()
                        BigDecimal noteDim = BigDecimal.valueOf(ligneParam(oeuvre, null,"DIMENSIONS").getBaseNote()); //getNote()

                        BigDecimal prixPoids = coeffParamPoids.multiply(notePoids);
                        BigDecimal prixDim = coeffParamDim.multiply(noteDim);
                        prixBaseOeuvre = ligneCommande.getIdOeuvre().getPrix();
                        quotient = (coeffParamPoids.add(coeffParamDim)).multiply(baseNote);

                        prixOeuvre = (prixOeuvre.add(prixPoids).add(prixDim)).multiply(prixBaseOeuvre).divide(quotient);
                    }
                    prixBaseOeuvres = prixBaseOeuvres.add(prixOeuvre).divide(nombreTotalOeuvre);

                    prixBaseOeuvres = prixBaseOeuvres.divide(nombreTotalOeuvre.multiply(prixOeuvre)); //REVIEW
                    break;
                case "TARIFICATION":
                    Tarification tarification = commande.getIdTarification();

                    coeffParamZone = BigDecimal.valueOf(ligneParam(null, tarification,"ZONE").getCoefficientParam().getValeurParametre());
                    coeffParamDistance = BigDecimal.valueOf(ligneParam(null,tarification, "DISTANCE").getCoefficientParam().getValeurParametre());
                    BigDecimal baseNote = BigDecimal.valueOf(ligneParam(null,tarification, "DISTANCE").getBaseNote());
                    BigDecimal noteZone = BigDecimal.valueOf(ligneParam(null, tarification,"ZONE").getBaseNote()); //getNote()
                    BigDecimal noteDistance = BigDecimal.valueOf(ligneParam(null,tarification, "DISTANCE").getBaseNote()); //getNote()

                    BigDecimal prixZone = coeffParamZone.multiply(noteZone);
                    BigDecimal prixDistance = coeffParamDistance.multiply(noteDistance);
                    prixBaseTarification = BigDecimal.valueOf(tarification.getFraisAssurance()+tarification.getFraisAssurance());
                    BigDecimal prixBaseTarif = prixDistance.add(prixZone).multiply(prixBaseTarification);
                    BigDecimal qotient = (coeffParamZone.add(coeffParamDistance)).multiply(baseNote);

                    return prixBaseTarif.divide(qotient);
            }
        }
        return prixBaseOeuvres;
    }

    public ParametreAlgo ligneParam(Oeuvre oeuvre,Tarification tarification, String type) {
        ParametreAlgo paramAlgo = null;
        switch (type){
            case "DIMENSIONS":
                paramAlgo = parametreAlgoFacade.findByNiveau(oeuvre.getDimensions());
                break;
            case "POIDS":
                paramAlgo = parametreAlgoFacade.findByNiveau(oeuvre.getLibellePoids());
                break;
            case "ZONE_LIVRAISON":
                paramAlgo = parametreAlgoFacade.findByNiveau(tarification.getAccessibiliteZone());
                break;
            case "DISTANCE":
                paramAlgo = parametreAlgoFacade.findByNiveau(tarification.getCategorieDistance());
                break;
            default:
                break;
        }
        return paramAlgo;
    }
}
