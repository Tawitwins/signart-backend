package sn.modelsis.signart.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
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
            dto = commandeConverter.entityToDto(commande);
            return Response.status(Response.Status.CREATED).entity(dto).build();
        } else {
            Logger.getLogger(ArtisteREST.class.getName()).log(Level.INFO, "Impossible de créér la commande");
            return Response.status(Response.Status.EXPECTATION_FAILED).entity("Impossible de créér la commande")
                    .build();
        }
        
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
        Paiement myPaiement = paiementFacade.find(myCommande.getId());
        PaiementDto myPaiementDto;
        if(myPaiement ==null)
        {
            myPaiementDto = new PaiementDto();
            myPaiementDto.setId(myCommande.getId());
            myPaiementDto.setIdCommande(myCommande.getId());
            myPaiementDto.setCodeModePaiement("PAYDUNYA");
        }
        else{
            myPaiementDto = paiementConverter.entityToDto(myPaiement);
        }
        myPaiementDto.setCodeEtatPaiement("PAYE");
        myPaiementDto.setDatePaiement(dateAjout);
        Set<LignePaiementDto> lignePaiementDtoSet = new HashSet<>();
        LignePaiementDto lp = new LignePaiementDto();
        if(myPaiement != null)
            lp.setIdPaiement(myPaiement.getId());
        lp.setDatePaiement(dateAjout);
        lp.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA").getId());
        lp.setCodeModePaiement("PAYDUNYA");
        lp.setMontant(BigDecimal.valueOf(0));
        for (LigneCommande ligneC :  myCommande.getLigneCommandeSet()) {
            BigDecimal montant = lp.getMontant();
            lp.setMontant(BigDecimal.valueOf((ligneC.getPrix().intValue()*ligneC.getQuantite())+montant.intValue()));
        }
        lignePaiementDtoSet.add(lp);
      /*  if(myPaiement == null){
            myPaiement = new Paiement();
            myPaiement.setId(myCommande.getId());
            myPaiement.setCommande(myCommande);
            myPaiement.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
        }
        myPaiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        myPaiement.setDatePaiement(dateAjout);
        Set<LignePaiement> lignePaiementSeet = new HashSet<>();
        Set<LignePaiementDto> lpd = (Set<LignePaiementDto>) new ArrayList<LignePaiementDto>();
        for (LigneCommande ligneC :  myCommande.getLigneCommandeSet()) {
            LignePaiement lp = new LignePaiement();
            lp.setIdPaiement(myPaiement);
            lp.setDatePaiement(dateAjout);
            lp.setMontant(BigDecimal.valueOf(ligneC.getPrix().intValue()*ligneC.getQuantite()));
            lp.setIdModePaiement(modePaiementFacade.findByCode("PAYDUNYA"));
            lignePaiementSeet.add(lp);
            lpd.add(lignePaiementConverter.entityToDto(lp));
        }*/
        myPaiementDto.setLignePaiements(lignePaiementDtoSet);
        myPaiement = paiementConverter.dtoToEntity(myPaiementDto);
        //paiementFacade.save(myPaiement);
        return "error to bad request";
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
