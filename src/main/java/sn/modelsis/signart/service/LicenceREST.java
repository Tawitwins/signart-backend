/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.Licence;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.DelaiFacade;
import sn.modelsis.signart.facade.LicenceFacade;
import sn.penda.signart.dto.LicenceDto;

/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("licence")
public class LicenceREST {
    
    @Inject
    LicenceFacade licenceFacade;
    @Inject
    AbonnementFacade abonnementFacade;
    @Inject
    AbonneFacade abonneFacade;
    @Inject
    DelaiFacade delaiFacade;
    
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(LicenceDto dto) throws SignArtException {
        licenceFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
   /* @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public LicenceDto find(@PathParam("id") Integer id) {
        Licence licence = licenceFacade.find(id);
        return entityToDto(licence);
    }*/
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        return "test terminal rest";
    }
    
    private Licence dtoToEntity(LicenceDto dto) throws SignArtException {
        
        Licence entity = new Licence();
        Abonnement abonnement = abonnementFacade.findById(dto.getIdAbonnement());
        System.out.println(abonnement+"++++++++++++++++++++++++++++++abonnement++++++++++++++++++++++++++++++++++++");
        Abonne abonne = abonneFacade.findById(abonnement.getIdAbonne().getId());
       System.out.println(abonne+"++++++++++++++++++++++++++++++abonne++++++++++++++++++++++++++++++++++++");
        Delai delai = delaiFacade.findById(abonnement.getIdDelai().getId());
        String username = abonne.getPrenom()+""+abonne.getNom()+""+abonne.getTelephone();  
        System.out.println(username+"++++++++++++++++++++++++++++++username++++++++++++++++++++++++++++++++++++");
        int nbMois = delai.getNbMois();
        String licenceValue = createLicence(username, nbMois);
        entity.setId(dto.getId());
        entity.setIdAbonnement(abonnementFacade.findById(dto.getIdAbonnement()));
        entity.setValeur(licenceValue);
        
        return entity;
    }
    
    private String createLicence(String username, int nbMois){
        Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
                String licence2 = "";
                String licence3 = "";
		c.add(Calendar.MONTH, nbMois);
		
		Date currentDatePlusOne = c.getTime();
		//System.out.println(dateFormat.format(currentDatePlusOne));
		String date = dateFormat.format(currentDatePlusOne);
		//System.out.println(date);
		
		String licence = "Application: SignArt\n" + 
				         "Version: 1.0\n" + 
				         "Expiration: date_expiration\n" + 
				         "Utilisateur: nom_du_client";
                
		//System.out.println("++++++++Avant+++++++++");
		//System.out.println(licence);
		//String nom = "Penda_Faye_00777966116";
		licence2 = licence.replaceAll("nom_du_client", username);
		licence3 = licence2.replaceAll("date_expiration", date);
		//System.out.println("++++++++Après+++++++++");
		//System.out.println(licence2);
                String filename = "licence00"+username;    
                 try {
                        FileWriter myWriter = new FileWriter("/opt/images"+filename);
                                                //System.out.println(LicenceREST.class.getResource("/Stockage/licences").getPath()+"++++++++++++++++++++++++++++++path++++++++++++++++++++++++++++++++++++");

                        //File file = new File("");
                       // FileWriter myWriter = new FileWriter();
                       // FileWriter myWriter = new FileWriter(LicenceREST.class.getResource("/Stockage/licences").getPath()+"/"+filename+".txt");
                       // File(ImageNumeriqueREST.class.getResource("/Stockage/images").getPath()+"/"+nom+".jpg")

                        myWriter.write(licence3);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
		
		return licence3;
    }
}
