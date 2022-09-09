/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.modelsis.signart.*;
import sn.modelsis.signart.dto.AbonneDto;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.dto.EtatAbonnementDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("abonnement")
public class AbonnementREST {
    
    @Inject
    AbonnementFacade abonnementfacade;
    
    @Inject
    TerminalFacade terminalFacade;
    
    @Inject
    DelaiFacade delaiFacade;
    
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    AbonneFacade abonneFacade;
    
    @Inject
    EtatAbonnementFacade etatAbonnementFacade;
    @Inject
    ModePaiementFacade modePaiementFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AbonnementDto dto) throws SignArtException {
        abonnementfacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Path("editEtatAbonnement/{idAbonnement}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPhoto(@PathParam("idAbonnement") Integer idAbonnement, EtatAbonnementDto etatAbonnement) throws SignArtException{
        Abonnement abonnement;
                abonnement = abonnementfacade.findById(idAbonnement);
                abonnement.setEtatAbonnement(etatAbonnementFacade.findById(etatAbonnement.getId()));      
                abonnementfacade.edit(abonnement);
                AbonnementDto dto = entityToDto(abonnement);
                return Response.status(Response.Status.OK).entity(dto).build();
                 
    }
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(AbonnementDto dto) throws SignArtException{
        abonnementfacade.edit(dtoToEntity(dto));
        AbonnementDto dtoRes = entityToDto(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dtoRes).build();

    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonnementDto find(@PathParam("id") Integer id) {
        Abonnement abonnement = abonnementfacade.find(id);
        return entityToDto(abonnement);
    }
    
    @GET
    @Path("abonne/{idAbonne}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AbonnementDto> findByAbonne(@PathParam("idAbonne") Integer idAbonne) throws SignArtException {
         List<AbonnementDto> dtoList = new ArrayList<>();
         List<Abonnement> entityList = abonnementfacade.findAllByIdAbonne(idAbonne);
            if (entityList != null) {
                entityList.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> dtoList.add(dto)
                );
            }
        return dtoList;
    }
    
    @GET
    @Path("abonnementByAbonne/{idAbonne}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonnementDto getByAbonne(@PathParam("idAbonne") Integer idAbonne) throws SignArtException {
         Abonnement entity = abonnementfacade.findByIdAbonne(idAbonne);          
        return entityToDto(entity);
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test abonnement rest";
    }
    
    @GET
    @Path("loadAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AbonnementDto> findAll(){
            List<AbonnementDto> dtoList = new ArrayList<>();
            List<Abonnement> entityList = abonnementfacade.findAll();
            if (entityList != null) {
                entityList.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> dtoList.add(dto)
                );
            }
            return dtoList;   
    }
    @GET
    @Path("report/{id}/{format}/{adrGal}")
    public String generateReport(@PathParam("id") Integer id,@PathParam("format") String format,@PathParam("adrGal") String adrGal) throws JRException, SignArtException, IOException {
        String path = "D:\\projet signart";
        List<AbonnementDto> abonnementDtoList = new ArrayList<>();
        abonnementDtoList.add(find(id));

        File file = new File("D:\\projet signart\\referentielsignart\\src\\main\\resources\\recuAbonnement.jrxml");
        System.out.println(file);

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(abonnementDtoList);

        Map<String, Object> parameters = new HashMap<>();
        Abonne abonne = abonnementfacade.findById(id).getIdAbonne();
        parameters.put("NomClient", abonne.getPrenom()+ " " +abonne.getNom());
        parameters.put("abonnementID", id);
        //Locale currentLocale = Locale.getDefault();
        parameters.put("adressGalerie", adrGal);
        //parameters.put("Pays", currentLocale.getCountry());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\reçue_paiement.html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\reçue_paiement.pdf");
        }
        byte [] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(path + "\\reçue_paiement.pdf"));
        BASE64Encoder encoder = new BASE64Encoder();
        String imageString = encoder.encode(imageByte);
        return imageString;
        //return "report generated in path : " + path;
    }

    private Abonnement dtoToEntity(AbonnementDto dto) throws SignArtException {
        
        Abonnement entity = new Abonnement();
        entity.setId(dto.getId());
        entity.setIdDelai(delaiFacade.findById(dto.getIdDelai()));
        entity.setIdListeSelection(listeSelectionFacade.findById(dto.getIdListeSelection()));
        entity.setIdTerminal(terminalFacade.findById(dto.getIdTerminal()));
        entity.setIdAbonne(abonneFacade.findById(dto.getIdAbonne()));
        entity.setMontantPaiement(dto.getMontantPaiement());
        entity.setPrecisions(dto.getPrecisions());
        entity.setEtatAbonnement(etatAbonnementFacade.findById(dto.getEtatAbonnement()));
        if(dto.getIdModePaiement() != null)
            entity.setIdModePaiement(modePaiementFacade.find(dto.getIdModePaiement()));
        entity.setTokenPaiement(dto.getToken());
        return entity;
    }
    
    private AbonnementDto entityToDto(Abonnement entity){
        AbonnementDto dto = new AbonnementDto();
        dto.setId(entity.getId());
        dto.setIdDelai(entity.getIdDelai().getId());
        dto.setIdListeSelection(entity.getIdListeSelection().getId());
        dto.setIdTerminal(entity.getIdTerminal().getId());
        dto.setIdAbonne(entity.getIdAbonne().getId());
        dto.setMontantPaiement(entity.getMontantPaiement());
        dto.setPrecisions(entity.getPrecisions());
        dto.setEtatAbonnement(entity.getEtatAbonnement().getId());
        dto.setId(entity.getId());
        if(entity.getIdModePaiement()!=null) {
            dto.setIdModePaiement(entity.getIdModePaiement().getId());
            dto.setCodeModePaiement(entity.getIdModePaiement().getCode());
        }
        dto.setToken(entity.getTokenPaiement());
        return dto;
    }
    
    private Utilisateur findUtilisateur(Integer idUtilisateur){
        return utilisateurFacade.find(idUtilisateur);
    }
    
    private Abonne findAbonne(Integer idAbonne){
        return abonneFacade.find(idAbonne);
    }
    
    private Delai findDelai(Integer idDelai){
        return delaiFacade.find(idDelai);
    }
    
    private Terminal findTerminal(Integer idTerminal){
        return terminalFacade.find(idTerminal);
    }
    
    private ListeSelection findListe(Integer idList) throws SignArtException{
        
        return listeSelectionFacade.findById(idList);
    }
    
    
}
