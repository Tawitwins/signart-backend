package sn.modelsis.signart.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.utils.Utils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
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
    public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\commande\\";

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

    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    ParametrageFacade parametrageFacade;
    @Inject
    CommandeFacade commandeFacade;
    Utils utils = new Utils();
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
        Collections.reverse(listDto);
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
        Collections.reverse(listDto);
        return listDto;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(LignePaiementDto dto) throws SignArtException {
        lignePaiementFacade.edit(lignePaiementConverter.dtoToEntity(dto));
        LignePaiementDto dtoRes = lignePaiementConverter.entityToDto(lignePaiementConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dtoRes).build();
    }

    @PUT
    @Path("valider/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validerLignePaiement(@PathParam("id") Integer id, LignePaiementDto dto) {
        //LignePaiement entity = lignePaiementConverter.dtoToEntity(dto);*
        LignePaiement lp = lignePaiementFacade.find(dto.getId());
        lp.setIdEtatPaiement(etatPaiementFacade.findByCode("PAYE"));
        lp.setIdModePaiement(modePaiementFacade.find(dto.getIdModePaiement()));
        lp.setIdPaymentDetails(paymentDetailsFacade.find(dto.getIdPaymentDetails()));
        lignePaiementFacade.save(lp);

        Oeuvre oeuvre = oeuvreFacade.find(lp.getIdLigneCommande().getIdOeuvre().getId());
        oeuvre.setPaid(true);
        oeuvreFacade.save(oeuvre);

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
    @Path("upload/{filename}")
    public  String encode(@PathParam("filename") String filename,String fileContent) throws IOException {
        fileContent = fileContent.split("base64,")[1];
        try{
        byte[]  content = Base64.decodeBase64(fileContent);
        String path = PATH + "preuves\\" + filename;
        java.nio.file.Path filee = (java.nio.file.Path) Paths.get(path);
        Files.write(filee, content);
            return path;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


//=====================

    @GET
    @Path("report/{id}/{format}/{lieu}")
    public String generateReport(@PathParam("id") Integer id,@PathParam("format") String format,@PathParam("lieu") String lieu) throws JRException, IOException {

        List<LignePaiementDto> lignePaiementDtoList =  new ArrayList<>();
        lignePaiementDtoList.add(find(id));

        String basicPath = "D:\\projet signart";
        String path = "D:\\projet signart\\referentielsignart\\src\\main\\resources\\";
        String pathLogo =  "D:\\projet signart\\referentielsignart\\src\\main\\resources\\assets\\images\\logo_signart.png";

        String ninea = parametrageFacade.findByParamName("NINEA").getValue();
        String adresseSignArt = parametrageFacade.findByParamName("adresseSignArt").getValue();
        String telephoneSignArt = parametrageFacade.findByParamName("telephoneSignArt").getValue();
        Client client = clientFacade.find(commandeFacade.find(lignePaiementDtoList.get(0).getLigneCommande().getIdCommande()).getIdClient().getId());

        File file = new File(path+ "recuLignePaiement.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lignePaiementDtoList);

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("nomClient", client.getPrenom()+ " " +client.getNom());
        parameters.put("lignePaimentID", id);
        parameters.put("montantPaiement", lignePaiementDtoList.get(0).getMontant());
        parameters.put("montantEnLettre", utils.convertToLetter(lignePaiementDtoList.get(0).getMontant().longValue()));
        parameters.put("numeroCommande", lignePaiementDtoList.get(0).getLigneCommande().getNumeroCommande());
        parameters.put("ninea", ninea);
        parameters.put("adresseSignArt", adresseSignArt);
        parameters.put("telephoneSignArt", telephoneSignArt);
        parameters.put("pathLogo", pathLogo);
        parameters.put("lieu", lieu);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "recues\\"+id+"_reçue_Lpaiement.html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "recues\\"+id+"_reçu_Lpaiement.pdf");
        }
        byte [] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(PATH + "recues\\"+id+"_reçue_Lpaiement.pdf"));
        String imageString = java.util.Base64.getEncoder().encodeToString(imageByte);
        return imageString;
    }
    @GET
    @Path("{id}/preuve")
    public String downloadPreuve(@PathParam("id") Integer id)  {
        LignePaiement lignePaiement = lignePaiementFacade.find(id);
       try{
           PaymentDetails paymentDetails = paymentDetailsFacade.find(lignePaiement.getIdPaymentDetails().getId());
           byte [] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(paymentDetails.getPreuve()));
           String imageString = java.util.Base64.getEncoder().encodeToString(imageByte);
           return  imageString;
       } catch (Exception e) {
           return  "Veuillez vérifier l'id du ligne de paiement";
       }
    }

    @GET
    @Path("FrenchNumberToWords/{number}")
    public String convertToLetter(@PathParam("number") long number){
        return utils.convertToLetter(number);
    }
}
