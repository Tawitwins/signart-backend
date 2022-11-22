package sn.modelsis.signart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sn.modelsis.signart.*;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.facade.LignePaiementFacade;
import sn.modelsis.signart.facade.PaiementFacade;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;
import sn.modelsis.signart.utils.Utils;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("paiement")
public class PaiementREST {
    public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\commande\\";

    @Inject
    PaiementFacade paiementFacade;
    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    PaiementConverter paiementConverter;
    @Inject
    ParametrageFacade parametrageFacade;
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    ClientFacade clientFacade;
    Utils utils = new Utils();
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PaiementDto dto) {
        paiementFacade.create(paiementConverter.dtoToEntity(dto));
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PaiementDto dto) {
        paiementFacade.edit(paiementConverter.dtoToEntity(dto));
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        paiementFacade.remove(paiementFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PaiementDto find(@PathParam("id") Integer id) {
        return paiementConverter.entityToDto(paiementFacade.find(id));
    }

    @GET
    @Path("paiement/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaiementDto> findO(@PathParam("id") Integer id) {
        List<PaiementDto> paiementDto = new ArrayList<>();
        paiementDto.add(paiementConverter.entityToDto(paiementFacade.find(id)));
        return  paiementDto;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PaiementDto> findAll() {
        List<PaiementDto> listDto = new ArrayList<>();
        List<Paiement> listEnt = paiementFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> paiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        Collections.reverse(listDto);
        return listDto;
    }

    @GET
    //@Path("magasin/{idMagasin}/{isLivreur}")
    @Path("magasin/{idMagasin}")
    @Produces({MediaType.APPLICATION_JSON})
    public List <PaiementDto> findByIdMagasin(@PathParam("idMagasin") Integer idMagasin/*,@PathParam("isLivreur") Boolean isLivreur*/) {
        // return commandeConverter.entityToDto(commandeFacade.findByIdClient(idClient));
        List<PaiementDto> listDto = new ArrayList<>();
        List<LignePaiement> listEntTmp = lignePaiementFacade.findAll();
        Set<Paiement> listEnt = new HashSet<>();
        for (LignePaiement ligneP : listEntTmp) {
            if(ligneP.getIdLigneCommande().getIdOeuvre().getIdMagasin().getId() == idMagasin){
                listEnt.add(ligneP.getIdPaiement());
            }
        }
        if (listEnt != null) {
            listEnt.stream().map(entity
                    -> paiementConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> listDto.add(dto)
            );
        }
        Collections.reverse(listDto);
        return listDto;
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Paiement> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return paiementFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(paiementFacade.count());
    }

    @GET
    @Path("report/{id}/{format}/{lieu}")
    public String generateReport(@PathParam("id") Integer id,
                                 @PathParam("format") String format,
                                 @PathParam("lieu") String lieu)
            throws JRException, IOException {

        String basicPath = "D:\\projet signart";
        String Path = "D:\\projet signart\\referentielsignart\\src\\main\\resources\\";
        String pathLogo = "D:\\projet signart\\referentielsignart\\src\\main\\resources\\assets\\images\\logo_signart.png";

        Client client = null;
        List<PaiementDto> paiementDtoList = new ArrayList<>();
        paiementDtoList.add(find(id));
        PaiementDto paiementDto = paiementDtoList.get(0);
        Set<LignePaiementDto> lignePaiementDtoSet = paiementDto.getLignePaiements();
        BigDecimal montantTotal = new BigDecimal("0");

        if (!lignePaiementDtoSet.isEmpty()) {
             for(LignePaiementDto lpaie: lignePaiementDtoSet){
               montantTotal = montantTotal.add(lpaie.getMontant());
             }
             paiementDto.setMontant(montantTotal);
             paiementFacade.save(paiementConverter.dtoToEntity(paiementDto));
         }

        if (!lignePaiementDtoSet.isEmpty()) {
            LignePaiementDto lignePaiementDto = lignePaiementDtoSet.iterator().next();
            try {
                client = clientFacade.findById(commandeFacade.find(lignePaiementDto.getLigneCommande().getIdCommande()).getIdClient().getId());
            } catch (SignArtException e) {
                throw new RuntimeException(e);
            }
            String ninea = parametrageFacade.findByParamName("NINEA").getValue();
            String adresseSignArt = parametrageFacade.findByParamName("adresseSignArt").getValue();
            String telephoneSignArt = parametrageFacade.findByParamName("telephoneSignArt").getValue();
            File file = new File(Path + "recuPaiement.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paiementDtoList);

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("nomClient", client.getPrenom()+ " " +client.getNom());
            parameters.put("paimentID", id);
            parameters.put("montantPaiement", montantTotal);
            parameters.put("montantEnLettre", utils.convertToLetter(montantTotal.longValue()));
            parameters.put("numeroCommande", commandeFacade.find(lignePaiementDto.getLigneCommande().getIdCommande()).getNumero());
            parameters.put("ninea", ninea);
            parameters.put("adresseSignArt", adresseSignArt);
            parameters.put("telephoneSignArt", telephoneSignArt);
            parameters.put("pathLogo", pathLogo);
            parameters.put("lieu", lieu);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            if (format.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "recues\\"+id+"_reçue_paiement.html");
            }
            if (format.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "recues\\"+id+"_reçue_paiement.pdf");
            }
            byte[] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(PATH + "recues\\"+id+"_reçue_paiement.pdf"));
            String imageString = java.util.Base64.getEncoder().encodeToString(imageByte);
            return imageString;
        }
        return null;
    }
}
