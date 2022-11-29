package sn.modelsis.signart.service;

import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.LigneLivraisonConverter;
import sn.modelsis.signart.dto.LigneLivraisonDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("lignelivraison")
public class LigneLivraisonREST {

   // public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\commande\\";
    public final static String PATH = "/signartFiles/commande/";

    // C:\Users\SNMBENGUEO\Desktop\
    @Inject
    LigneLivraisonFacade ligneLivraisonFacade;
    @Inject
    LivraisonFacade livraisonFacade;
    @Inject
    LigneLivraisonConverter ligneLivraisonConverter;
    @Inject
    EtatLivraisonFacade etatLivraisonFacade;
    @Inject
    ModeLivraisonFacade modeLivraisonFacade;


    public LigneLivraisonREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LigneLivraisonDto dto) throws SignArtException {
        LigneLivraison entity = ligneLivraisonConverter.dtoToEntity(dto);
        ligneLivraisonFacade.create(entity);
        //return Response.status(Response.Status.CREATED).entity(lignePanierConverter.entityToDto(entity)).build();
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LigneLivraisonDto dto) throws SignArtException {
        LigneLivraison entity = ligneLivraisonConverter.dtoToEntity(dto);
        ligneLivraisonFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ligneLivraisonFacade.remove(ligneLivraisonFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LigneLivraisonREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LigneLivraisonDto find(@PathParam("id") Integer id) throws SignArtException {
        LigneLivraison ligneLivraison = ligneLivraisonFacade.find(id);
        return ligneLivraisonConverter.entityToDto(ligneLivraison,true);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LigneLivraisonDto> findAll() throws SignArtException{
        List<LigneLivraisonDto> listDto = new ArrayList<>();
        List<LigneLivraison> listEnt = ligneLivraisonFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> ligneLivraisonConverter.entityToDto(entity,false)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        Collections.reverse(listDto);
        return listDto;
    }
    @GET
    @Path("magasin/{idMagasin}/{idLivreur}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LigneLivraisonDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin,@PathParam("idLivreur") Integer idLivreur) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LigneLivraisonDto> listDto = new ArrayList<>();
        List<LigneLivraison> listEntTmp = ligneLivraisonFacade.findAll();
        List<LigneLivraison> listEnt = new ArrayList<>();
        for (LigneLivraison ligneLivraison : listEntTmp) {
            if(ligneLivraison.getIdLigneCommande().getIdOeuvre().getIdMagasin().getId() == idMagasin &&
                    ligneLivraison.getIdAgent() !=null && ligneLivraison.getIdAgent().getId() == idLivreur){
                listEnt.add(ligneLivraison);
            }

        }
        listEnt.stream().map(entity -> ligneLivraisonConverter.entityToDto(entity,true)
        ).forEachOrdered(dto
                -> listDto.add(dto)
        );
        return listDto;
    }
    @PUT
    @Path("valider/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validerLigneLivraison(@PathParam("id") Integer id, LigneLivraisonDto dto) {
        //LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);*
        LigneLivraison lp = ligneLivraisonFacade.find(dto.getId());
        lp.setIdEtatLivraison(etatLivraisonFacade.findByCode("TERMINEE"));
        lp.setIdModeLivraison(modeLivraisonFacade.find(dto.getModeLivraison().getId()));
        lp.setPreuvePourLivraison(dto.getPreuvePourLivraison());
        ligneLivraisonFacade.save(lp);
        //Vérifier si toutes les lignes paiement sont valider pour pas et metter a jour le paiement global
        Livraison livraison = livraisonFacade.find((dto.getIdLivraison()));
        BigDecimal total;
        boolean allDelivered = true;
        int cpt = 0;
        for (LigneLivraison ligneLivraison : livraison.getLigneLivraisonSet()) {
            if(ligneLivraison.getIdEtatLivraison().getCode().equals("NOLIVREE") ||
                    ligneLivraison.getIdEtatLivraison().getCode().equals("TRAITEMENT")){
                allDelivered = false;
            }
            cpt++;
        }
        if(cpt>0 && allDelivered == true){
            livraison.setIdEtatLivraison(etatLivraisonFacade.findByCode("TERMINEE"));
            livraisonFacade.save(livraison);
        }
        if(cpt>0 && !allDelivered){
            livraison.setIdEtatLivraison(etatLivraisonFacade.findByCode("TRAITEMENT"));
            livraisonFacade.save(livraison);
        }
        //lignePaiementFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }
    @POST
    @Path("upload/{filename}")
    public  String encode(@PathParam("filename") String filename,String fileContent) throws IOException {
        try{
            byte[]  content = Base64.decodeBase64(fileContent);
            java.nio.file.Path filee = (java.nio.file.Path) Paths.get(PATH + filename);
            Files.write(filee, content);
            return PATH + "preuves/"+filename;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    @GET
    @Path("{id}/preuve")
    public String downloadPreuve(@PathParam("id") Integer id) throws IOException {
        LigneLivraison ligneLivraison = ligneLivraisonFacade.find(id);
            byte [] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(ligneLivraison.getPreuvePourLivraison().trim()));
            String imageString = java.util.Base64.getEncoder().encodeToString(imageByte);
            return  imageString;
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(ligneLivraisonFacade.count());
    }
}
