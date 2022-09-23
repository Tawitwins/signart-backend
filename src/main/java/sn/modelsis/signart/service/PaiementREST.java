package sn.modelsis.signart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import sn.modelsis.signart.LigneCommande;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.converter.LignePaiementConverter;
import sn.modelsis.signart.converter.PaiementConverter;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.facade.LignePaiementFacade;
import sn.modelsis.signart.facade.PaiementFacade;
import sun.misc.BASE64Encoder;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("paiement")
public class PaiementREST {

    @Inject
    PaiementFacade paiementFacade;
    @Inject
    LignePaiementFacade lignePaiementFacade;
    @Inject
    PaiementConverter paiementConverter;
    @Inject
    LignePaiementConverter lignePaiementConverter;

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
    @Path("report/{id}/{format}")
    public String generateReport(@PathParam("id") Integer id,@PathParam("format") String format) throws JRException, IOException {
        String path = "D:\\Modelsis";
        List<PaiementDto> paiementDtoList = findO(id);

        File file = new File("D:\\Modelsis\\SignArt\\signArt\\referentielsignart\\src\\main\\resources\\recuP.jrxml");
        System.out.println(file);

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paiementDtoList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "MODELSIS");

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
    }
}
