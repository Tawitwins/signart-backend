package sn.modelsis.signart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

import com.sun.org.apache.xpath.internal.operations.Bool;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.LigneCommandeConverter;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("lignecommande")
public class LigneCommandeREST {

    @Inject
    LigneCommandeFacade ligneCommandeFacade;
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    LigneLivraisonFacade ligneLivraisonFacade;
    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    LigneCommandeConverter ligneCommandeConverter;
    @Inject
    EtatLigneCommandeFacade etatLigneanierFacade;
    @Inject
    AgentFacade agentFacade;

    public LigneCommandeREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LigneCommandeDto dto) {
        LigneCommande entity = ligneCommandeConverter.dtoToEntity(dto);
        entity.setIdEtatLigneCommande(etatLigneanierFacade.findByCode("NOUVEAU"));
        ligneCommandeFacade.create(entity);
        return Response.status(Response.Status.CREATED).entity(ligneCommandeConverter.entityToDto(entity)).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LigneCommandeDto dto) {
        ligneCommandeFacade.edit(ligneCommandeConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ligneCommandeFacade.remove(ligneCommandeFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LigneCommandeREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LigneCommandeDto find(@PathParam("id") Integer id) {
        LigneCommande ligneCommande = ligneCommandeFacade.findById(id);
        return ligneCommandeConverter.entityToDto(ligneCommande);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LigneCommandeDto> findAll() {
        List<LigneCommandeDto> listDto = new ArrayList<>();
        List<LigneCommande> listEnt = ligneCommandeFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneCommandeConverter.entityToDto(entity)
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
    public List <LigneCommandeDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin/*,@PathParam("isLivreur") Boolean isLivreur*/) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LigneCommandeDto> listDto = new ArrayList<>();
        List<LigneCommande> listEntTmp = ligneCommandeFacade.findAll();
        List<LigneCommande> listEnt = new ArrayList<>();
        for (LigneCommande ligneC : listEntTmp) {
            if(ligneC.getIdOeuvre().getIdMagasin().getId() == idMagasin){
                listEnt.add(ligneC);
                /* if(isLivreur == true){
                LigneLivraison ligneLivraison = ligneLivraisonFacade.findByLigneCommande(ligneC.getId());
                    if(ligneLivraison.getIdAgent()!=null){
                        listEnt.add(ligneC);
                    }
                }
                else {
                    listEnt.add(ligneC);
                }*/
            }
        }
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneCommandeConverter.entityToDtoPlusLigneLivraison(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

    @PUT
    @Path("affecterLivreur/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validerLigneLivraison(@PathParam("id") Integer id, LigneCommandeDto dto) throws SignArtException {
        //LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);
        LigneLivraison liv = ligneLivraisonFacade.find(dto.getLigneLivraison().getId());
        LignePaiement lp = lignePaiementFacade.findByLigneCommande(dto.getId());
        if(lp.getIdEtatPaiement().getCode().equals("PAYE")){
            int idAgent = dto.getLigneLivraison().getAgent().getId();
            liv.setIdAgent(agentFacade.findById(idAgent));
            ligneLivraisonFacade.save(liv);
        }
        else {
            return null;
        }


        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LigneCommande> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return ligneCommandeFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(ligneCommandeFacade.count());
    }
}
