/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.ejb.Stateless;
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
import sn.modelsis.signart.dto.*;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;
import sn.modelsis.signart.utils.Utils;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("abonnement")
public class AbonnementREST {
    public final static String PATH = "/signartFiles/abonnement/";

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
    @Inject
    PaymentDetailsFacade paymentDetailsFacade;
    @Inject
    ListeSelection_OeuvresFacade listeOeuvreFacade;
    @Inject
    OeuvreNumeriqueFacade oeuvreNumeriqueFacade;
    @Inject
    ParametrageFacade parametrageFacade;
    @Inject
    ParametreAlgoFacade parametreAlgoFacade;
    @Inject
    ExpositionFacade expositionFacade;
    Utils utils = new Utils();
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AbonnementDto dto) throws SignArtException {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateAjout = new java.sql.Timestamp(currentDate.getTime());
        dto.setDateCreation(dateAjout);
        abonnementfacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    @POST
    @Path("reabonnement/{response}/{idTerminal}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response Reabonnement(AbonnementDto oldAbonnementdto, @PathParam("response") Boolean response, @PathParam("idTerminal") Integer idTerminal) throws SignArtException {
        //Abonnement oldAbonnement = abonnementfacade.find(idOldAbonnement);
        Abonnement oldAbonnement = dtoToEntity(oldAbonnementdto);
        Terminal newTerminal = findTerminal(idTerminal);
        Abonnement newAbonnement = oldAbonnement;
        if(response == true ){
            newAbonnement =createSameAbonnement(oldAbonnement);
        }
        else{
            int newMontant = oldAbonnement.getMontantPaiement()-oldAbonnement.getIdTerminal().getPrix()+newTerminal.getPrix();
            newAbonnement.setMontantPaiement(newMontant);
            newAbonnement.setIdTerminal(newTerminal);
            newAbonnement.setEtatAbonnement(etatAbonnementFacade.findByCode("NON_PAYE"));
            newAbonnement.setId(null);
            newAbonnement.setTokenPaiement(null);
            newAbonnement.setDateDebut(null);
            newAbonnement.setIdModePaiement(null);
        }
        abonnementfacade.add(newAbonnement);
        return Response.status(Response.Status.CREATED).entity(entityToDto(newAbonnement)).build();
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
        Abonnement entity =dtoToEntity(dto);
        entity = abonnementfacade.save(entity);
        AbonnementDto dtoRes = entityToDto(entity);
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
        Collections.reverse(dtoList);
        return dtoList;
    }

    @GET
    @Path("abonnementByAbonne/{idAbonne}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AbonnementDto> getByAbonne(@PathParam("idAbonne") Integer idAbonne) throws SignArtException {
        List<AbonnementDto> dtoList = new ArrayList<>();
        List<Abonnement> listEntity = abonnementfacade.findByIdAbonne(idAbonne);
        if (listEntity != null) {
            listEntity.stream().map(entity
                    -> entityToDto(entity)
            ).forEachOrdered(dto
                    -> dtoList.add(dto)
            );
        }
        Collections.reverse(dtoList);
        return dtoList;
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
        Collections.reverse(dtoList);
        return dtoList;
    }

    @GET
    @Path("getMontant/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbonnementDto calculMontantAbonnement(@PathParam("id") Integer id) throws SignArtException {
        Abonnement abonnement = abonnementfacade.find(id);
        List<Float> resultPrixOeuvre = getPrixActuelOeuvre(abonnement);
        List<Float> resultPrixAbonne = getPrixAbonne(abonnement,resultPrixOeuvre.get(0));

        float Total = (resultPrixOeuvre.get(0)*resultPrixOeuvre.get(1) + resultPrixAbonne.get(0)*resultPrixAbonne.get(1))/ resultPrixOeuvre.get(1)+resultPrixAbonne.get(1);
        abonnement.setMontantPaiement((int)Total);
        return entityToDto(abonnement);
    }
    @POST
    @Path("getMontantByAbonnementDto")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Float calculMontantAbonnementDto(AbonnementDto abonnementDto) throws SignArtException {
        Abonnement abonnement = dtoToEntity(abonnementDto);//abonnementfacade.find(id);
        List<Float> resultPrixOeuvre = getPrixActuelOeuvre(abonnement);
        List<Float> resultPrixAbonne = getPrixAbonne(abonnement,resultPrixOeuvre.get(0));

        float Total = (resultPrixOeuvre.get(0)*resultPrixOeuvre.get(1) + resultPrixAbonne.get(0)*resultPrixAbonne.get(1))/ resultPrixOeuvre.get(1)+resultPrixAbonne.get(1);
        abonnement.setMontantPaiement((int)Total);
        return Total;
    }

    @GET
    @Path("report/{id}/{format}/{adrGal}")
    public String generateReport(@PathParam("id") Integer id,@PathParam("format") String format,@PathParam("adrGal") String adrGal) throws JRException, SignArtException, IOException {
        String pathLogo = "/signartResources/resources/assets/images/logo_signart.png";
        String pathRessource = "/signartResources/resources/";

        List<AbonnementDto> abonnementDtoList = new ArrayList<>();
        abonnementDtoList.add(find(id));

        File file = new File(pathRessource+"recuAbonnement.jrxml");
        System.out.println(file);

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(abonnementDtoList);

        Map<String, Object> parameters = new HashMap<>();
        Abonne abonne = abonnementfacade.findById(id).getIdAbonne();
        Abonnement abonnement = abonnementfacade.findById(id);
        String ninea = parametrageFacade.findByParamName("NINEA").getValue();
        String adresseSignArt = parametrageFacade.findByParamName("adresseSignArt").getValue();
        String telephoneSignArt = parametrageFacade.findByParamName("telephoneSignArt").getValue();
        Long montantTotal = new Long(abonnement.getMontantPaiement());

        parameters.put("NomClient", abonne.getPrenom()+ " " +abonne.getNom());
        parameters.put("abonnementID", id);
        //Locale currentLocale = Locale.getDefault();
        parameters.put("adressGalerie", adrGal);
        //parameters.put("Pays", currentLocale.getCountry());
        parameters.put("montantEnLettre", utils.convertToLetter(montantTotal));
        parameters.put("ninea", ninea);
        parameters.put("adresseSignArt", adresseSignArt);
        parameters.put("telephoneSignArt", telephoneSignArt);
        parameters.put("pathLogo", pathLogo);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH+"recues/"+id+"_recue_paiement.html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH+"recues/"+id+"_recue_paiement.pdf");
        }
        byte [] imageByte = Files.readAllBytes((java.nio.file.Path) Paths.get(PATH+"recues/"+id+"_recue_paiement.pdf"));
        String imageString = java.util.Base64.getEncoder().encodeToString(imageByte);
        return imageString;
        //return "report generated in path : " + path;
    }

    private Abonnement createSameAbonnement( Abonnement oldAbonnement){
        Abonnement newAbonnement = oldAbonnement;
        newAbonnement.setId(null);
        //abonnementfacade.add(newAbonnement);
        return newAbonnement;
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
        entity.setReabonne(dto.getReabonne());
        if(dto.getIdModePaiement() != null)
            entity.setIdModePaiement(modePaiementFacade.find(dto.getIdModePaiement()));
        if(dto.getIdDetailPayment() != null)
            entity.setIdDetailPayment(paymentDetailsFacade.find(dto.getIdDetailPayment()));
        entity.setDateCréation(dto.getDateCreation());
        entity.setDateDebut(entity.getDateDebut());
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
        dto.setReabonne(entity.getReabonne());
        dto.setId(entity.getId());
        if(entity.getIdModePaiement()!=null) {
            dto.setIdModePaiement(entity.getIdModePaiement().getId());
            dto.setCodeModePaiement(entity.getIdModePaiement().getCode());
        }
        if(entity.getIdDetailPayment() != null)
            dto.setIdDetailPayment(entity.getIdDetailPayment().getId());
        dto.setDateCreation(entity.getDateCréation());
        dto.setDateDebut(entity.getDateDebut());
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

    private List<Float> getPrixActuelOeuvre(Abonnement abonnement) throws SignArtException {
        List<ListeSelection_Oeuvres> listOeuvre = listeOeuvreFacade.findByIdListe(abonnement.getIdListeSelection().getId());
        Parametrage prixBaseOeuvre = parametrageFacade.findByParamName("prixBaseOeuvre");
        int prixOeuvres = 0;
        int totalCoef = 0;
        for (ListeSelection_Oeuvres oeuvre : listOeuvre) {
            OeuvreNumerique currentOeuvre;
            try {
                currentOeuvre = oeuvreNumeriqueFacade.findByName(oeuvre.getNomOeuvre());
            } catch (SignArtException e) {
                throw new RuntimeException(e);
            }
            List<ParametreAlgo> listParamByElement = new ArrayList<>();
            List<ParametreAlgo> listParamByArtiste = new ArrayList<>();
            List<ParametreAlgo> listParamByOeuvre = new ArrayList<>();
            listParamByArtiste = recupérationParamArtiste(currentOeuvre.getIdentiteAuteur());
            listParamByOeuvre = récuperationParamOeuvre(currentOeuvre);
            listParamByElement = listParamByOeuvre;
            listParamByElement.addAll(listParamByArtiste);
            int totalProduit = 0;
            totalCoef = 0;
            for (ParametreAlgo parametreAlgo : listParamByElement) {
                totalProduit += parametreAlgo.getNote() * parametreAlgo.getCoefficientParam().getValeurParametre() * parametreAlgo.getPourcentReduction();
                totalCoef += parametreAlgo.getCoefficientParam().getValeurParametre();
            }
            totalProduit = totalProduit / listParamByElement.get(0).getBaseNote();
            totalProduit = totalProduit * Integer.parseInt(prixBaseOeuvre.getValue()) / totalCoef;
            prixOeuvres += totalProduit;

        }
        float moyenneOeuvres = prixOeuvres / listOeuvre.size();
        List<Float> result = new ArrayList<>();
        result.add(moyenneOeuvres);
        result.add((float) totalCoef);
        return result;
    }
    private List<Float> getPrixAbonne(Abonnement abonnement,float prixOeuvre) throws SignArtException {
        List<ParametreAlgo> listParamByAbonnement = new ArrayList<>();
        listParamByAbonnement = recupérationParamAbonnement(abonnement);
        float totalProduit = 0;
        float totalCoef = 0;
        for (ParametreAlgo parametreAlgo : listParamByAbonnement) {
            totalProduit += parametreAlgo.getNote() * parametreAlgo.getCoefficientParam().getValeurParametre() * parametreAlgo.getPourcentReduction();
            totalCoef += parametreAlgo.getCoefficientParam().getValeurParametre();
        }
        totalProduit = totalProduit/listParamByAbonnement.get(0).getBaseNote();
        totalProduit = totalProduit * prixOeuvre / totalCoef;
        float prixAbonnee = totalProduit;
        List<Float> result = new ArrayList<>();
        result.add(prixAbonnee);
        result.add((float) totalCoef);
        return result;
    }
    private  List<ParametreAlgo> récuperationParamOeuvre(OeuvreNumerique oeuvre) {
        List<ParametreAlgo> listParamOeuvre = new ArrayList<>();
        List<ParametreAlgo> allListParam = new ArrayList<>();
        allListParam = parametreAlgoFacade.findAll();
        // Dimension oeuvre
        for (ParametreAlgo parametreAlgo : allListParam) {
            if (parametreAlgo.getNiveau().equals(oeuvre.getDimensionLevel())){
                listParamOeuvre.add(parametreAlgo);
            }
        }
        //listParamOeuvre.add(allListParam.stream().findAny().filter(parametreAlgo -> parametreAlgo.getNiveau().equals(oeuvre.getDimensionLevel())).get());
        // poids oeuvre
        for (ParametreAlgo parametreAlgo : allListParam) {
            if (parametreAlgo.getNiveau().equals(oeuvre.getNiveauPoids())){
                listParamOeuvre.add(parametreAlgo);
            }
        }
        //listParamOeuvre.add(allListParam.stream().findAny().filter(parametreAlgo -> parametreAlgo.getNiveau().equals(oeuvre.getPoids())).get());
        return listParamOeuvre;
    }

    private  List<ParametreAlgo> recupérationParamArtiste(Artiste artiste) throws SignArtException {
        List<ParametreAlgo> listParamArtiste = new ArrayList<>();
        List<ParametreAlgo> allListParam = new ArrayList<>();
        allListParam = parametreAlgoFacade.findAll();
        // Qalification Artiste
        for (ParametreAlgo parametreAlgo : allListParam) {
            if (parametreAlgo.getNiveau().equals(artiste.getQualificationLevel())){
                listParamArtiste.add(parametreAlgo);
            }
        }
        //listParamArtiste.add(allListParam.stream().filter(parametreAlgo ->parametreAlgo.getNiveau().equals(artiste.getQualificationLevel())).findFirst().get());
        // Nombre Exposition
        int nbrExpo = expositionFacade.findByArtiste(artiste.getId()).size();
        for (ParametreAlgo parametreAlgo : allListParam) {
            if(parametreAlgo.getBorneInf() != null && parametreAlgo.getBorneSup() != null)
            {
                if (parametreAlgo.getBorneInf() <= nbrExpo && parametreAlgo.getBorneSup()>nbrExpo){
                    listParamArtiste.add(parametreAlgo);
                }
            }
        }
        //listParamArtiste.add(allListParam.stream().findAny().filter(parametreAlgo -> parametreAlgo.getBorneInf() <= nbrExpo && parametreAlgo.getBorneSup()>nbrExpo).get());
        // Nombre année expérience
        Date currentDate = new Date();
        int anneeXp = currentDate.getYear() - artiste.getAnneeDebutCarrier();
        for (ParametreAlgo parametreAlgo : allListParam) {
            if(parametreAlgo.getBorneInf() != null && parametreAlgo.getBorneSup() != null)
            {
                if ( parametreAlgo.getBorneInf() <= anneeXp && parametreAlgo.getBorneSup()>anneeXp){
                    listParamArtiste.add(parametreAlgo);
                }
            }
        }
        //listParamArtiste.add(allListParam.stream().findAny().filter(parametreAlgo -> parametreAlgo.getBorneInf() <= anneeXp && parametreAlgo.getBorneSup()>anneeXp).get());
        return listParamArtiste;
    }
    private  List<ParametreAlgo> recupérationParamAbonnement(Abonnement abonnement) throws SignArtException {
        List<ListeSelection_Oeuvres> listOeuvre = listeOeuvreFacade.findByIdListe(abonnement.getIdListeSelection().getId());
        List<ParametreAlgo> listParamAbonnement = new ArrayList<>();
        List<ParametreAlgo> allListParam = new ArrayList<>();
        allListParam = parametreAlgoFacade.findAll();
        // Nombre oeuvre dans abonnement
        for (ParametreAlgo parametreAlgo : allListParam) {
            if(parametreAlgo.getBorneInf() != null && parametreAlgo.getBorneSup() != null)
            {
                if (parametreAlgo.getBorneInf() <= listOeuvre.size() && parametreAlgo.getBorneSup()>listOeuvre.size()){
                    listParamAbonnement.add(parametreAlgo);
                }
            }
        }
        //listParamAbonnement.add(allListParam.stream().findAny().filter(parametreAlgo -> parametreAlgo.getBorneInf() <= listOeuvre.size() && parametreAlgo.getBorneSup()>listOeuvre.size()).get());
        // Durée abonnement
        for (ParametreAlgo parametreAlgo : allListParam) {
            if (parametreAlgo.getNiveau().equals(abonnement.getIdDelai().getDelaiLevel())){
                listParamAbonnement.add(parametreAlgo);
            }
        }
        //listParamAbonnement.add(allListParam.stream().findAny().filter(parametreAlgo ->parametreAlgo.getNiveau().equals(abonnement.getIdDelai().getDelaiLevel())).get());
        return listParamAbonnement;
    }
}
