package sn.modelsis.signart.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.LignePanierDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.facade.*;
import sun.misc.BASE64Encoder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author SNLOM
 */
@Stateless
@Path("lignepaiement")
public class LignePaiementREST {

    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    PaiementFacade paiementFacade;
    @Inject
    LignePaiementConverter lignePaiementConverter;
    @Inject
    PaiementConverter paiementConverter;
    @Inject
    EtatPaiementFacade etatPaiementFacade;
    @Inject
    ModePaiementFacade modePaiementFacade;

    @Inject
    PaymentDetailsFacade paymentDetailsFacade;

    public LignePaiementREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LignePaiementDto dto) {
        LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);
        lignePaiementFacade.create(entity);
        //return Response.status(Response.Status.CREATED).entity(lignePanierConverter.entityToDto(entity)).build();
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, LignePaiementDto dto) {
        LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);
        lignePaiementFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            lignePaiementFacade.remove(lignePaiementFacade.find(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            Logger.getLogger(LignePaiementREST.class.getName()).log(Level.SEVERE, "remove/Exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LignePaiementDto find(@PathParam("id") Integer id) {
        LignePaiement lignePaiement = lignePaiementFacade.find(id);
        return lignePaiementConverter.entityToDto(lignePaiement);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<LignePaiementDto> findAll() {
        List<LignePaiementDto> listDto = new ArrayList<>();
        List<LignePaiement> listEnt = lignePaiementFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> lignePaiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }
    @GET
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <LignePaiementDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<LignePaiementDto> listDto = new ArrayList<>();
        List<LignePaiement> listEntTmp = lignePaiementFacade.findAll();
        List<LignePaiement> listEnt = new ArrayList<>();
        for (LignePaiement lignePaiement : listEntTmp) {
            if(lignePaiement.getIdLigneCommande().getIdOeuvre().getIdMagasin().getId() == idMagasin){
                listEnt.add(lignePaiement);
            }

        }
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> lignePaiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        return listDto;
    }

    @PUT
    @Path("valider/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validerLignePaiement(@PathParam("id") Integer id, LignePaiementDto dto) {
        //LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);*
        LignePaiement lp = lignePaiementFacade.find(dto.getId());
        lp.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        lp.setIdModePaiement(modePaiementFacade.find(dto.getIdModePaiement()));
        lignePaiementFacade.save(lp);
        //Vérifier si toutes les lignes paiement sont valider pour pas et metter a jour le paiement global
        Paiement paiement = paiementFacade.find((dto.getIdPaiement()));
        BigDecimal total;
        boolean allPaid = true;
        int cpt = 0;
        for (LignePaiement lignePaiement : paiement.getLignePaiementSet()) {
            if(lignePaiement.getIdEtatPaiement().getCode().equals("NOPAYE") ||
                    lignePaiement.getIdEtatPaiement().getCode().equals("PARTIEL")){
                allPaid = false;
            }
            cpt++;
        }
        if(cpt>0 && allPaid){
            paiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        }
        else if (cpt>0 && !allPaid){
            paiement.setIdEtatPaiement(etatPaiementFacade.findByCode("PARTIEL"));
        }
        paiementFacade.save(paiement);
        //lignePaiementFacade.edit(entity);
        //lignePaiementFacade.remove(lignePaiementFacade.find(id));
        return Response.status(Response.Status.OK).entity(dto).build();
    }


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(lignePaiementFacade.count());
    }

    @POST
    @Path("upload")
    public  String encode(String filePath)  {
        try{
            byte[] data = Files.readAllBytes((java.nio.file.Path) Paths.get("D:\\Modelsis\\"+filePath));
            BASE64Encoder encoder = new BASE64Encoder();
            String imageString = encoder.encode(data);
            return imageString;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    @GET
    @Path("genere")

    public byte[] decode() {
        //return Base64.getDecoder().decode(base64String);
        File file = new File("./recu_payment.pdf");
        try(FileOutputStream fos = new FileOutputStream(file);){
            String b64 = "JVBERi0xLjUKJYCBgoMKMSAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvRmlyc3QgMTQxL04gMjAvTGVuZ3==";
            byte[] decoder = Base64.getDecoder().decode(b64);
            fos.write(decoder);
            System.out.println("PDF File Saved");
            return decoder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//=====================

    @GET
    @Path("paiement/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<LignePaiementDto> findO(@PathParam("id") Integer id) {
        List<LignePaiementDto> lignePaiementDtoList = new ArrayList<>();
        lignePaiementDtoList.add(lignePaiementConverter.entityToDto(lignePaiementFacade.find(id)));
        return  lignePaiementDtoList;
    }

    @GET
    @Path("report/{id}/{format}")
    public String generateReport(@PathParam("id") Integer id,@PathParam("format") String format) throws JRException {
        String path = "D:\\Modelsis";
        List<LignePaiementDto> paiementDtoList = findO(id);

        File file = new File("D:\\Modelsis\\SignArt\\signArt\\referentielsignart\\src\\main\\resources\\recuPaiement.jrxml");
        System.out.println(file);

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paiementDtoList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("numeroCommande", paiementDtoList.get(0).getLigneCommande().getNumeroCommande());
        parameters.put("nomOeuvre",paiementDtoList.get(0).getLigneCommande().getOeuvre().getNom());
        parameters.put("montant", paiementDtoList.get(0).getMontant());
        parameters.put("modePaiement", paiementDtoList.get(0).getLibelleModePaiement());
        parameters.put("etat", paiementDtoList.get(0).getLibelleEtatPaiement());
        parameters.put("date", paiementDtoList.get(0).getStringPaymentDate());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\reçue_paiement.html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\reçue_paiement.pdf");
        }
        return "report generated in path : " + path;
    }
}
