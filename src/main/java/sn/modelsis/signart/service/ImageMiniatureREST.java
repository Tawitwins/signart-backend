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
import java.util.ArrayList;
import java.util.List;
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
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.ImageMiniature;
import sn.modelsis.signart.OeuvreNumerique;
import sn.modelsis.signart.dto.ImageMiniatureDto;
import sn.modelsis.signart.dto.ImageNumeriqueDto;
import sn.modelsis.signart.dto.OeuvreNumeriqueDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ImageMiniatureFacade;
import sn.modelsis.signart.facade.OeuvreNumeriqueFacade;
import static sn.modelsis.signart.service.ImageNumeriqueREST.encodeToString;


/**
 *
 * @author snfayemp
 */

@Stateless
@Path("imageMiniature")
public class ImageMiniatureREST {

    public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\abonnement\\";

    @Inject
    ImageMiniatureFacade imageMiniaturefacade;
    
    @Inject
    OeuvreNumeriqueFacade oeuvreNumeriqueFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ImageMiniatureDto dto) {
        imageMiniaturefacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @PUT
    @Path("addAllMiniature")
    public Response createAll() throws IOException {
        
        List<OeuvreNumerique> oeuvre = new ArrayList<>();       
        for(int i=0; i<oeuvreNumeriqueFacade.findAll().size(); i++){
            oeuvre.add(oeuvreNumeriqueFacade.findAll().get(i));
        }       
        for(int i=0; i<oeuvre.size(); i++){
            ImageMiniature image = new ImageMiniature();
            String nom = oeuvre.get(i).getNom();
            BufferedImage minImage = ImageIO.read(new File(PATH+"images\\min_"+nom+".jpg"));
            byte[] minImageByte = toByteArray(minImage, "jpg");           
            image.setNomImage(nom);         
            image.setValeurImage(minImageByte);
            imageMiniaturefacade.create(image);
        }
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("addMiniatureTest/{nameImage}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAllTest(@PathParam("nameImage") String nameImage) throws IOException, SignArtException {
        
        //OeuvreNumerique oeuvre = oeuvreNumeriqueFacade.findByName(nameImage);
        ImageMiniature image = new ImageMiniature();
            BufferedImage minImage = ImageIO.read(new File(PATH + "images\\min_"+nameImage+".jpg"));
            byte[] minImageByte = toByteArray(minImage, "jpg");           
            image.setNomImage(nameImage);         
            image.setValeurImage(minImageByte);
            imageMiniaturefacade.create(image);     
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ImageMiniatureDto find(@PathParam("id") Integer id) {
        ImageMiniature image = imageMiniaturefacade.find(id);
        return entityToDto(image);
    }
    
    @GET
    @Path("byName/{nomImage}")
    @Produces({MediaType.APPLICATION_JSON})
    public ImageMiniatureDto findByName(@PathParam("nomImage") String nomImage) throws SignArtException {
        ImageMiniatureDto dto = new ImageMiniatureDto();
        ImageMiniature image = new ImageMiniature();
        try { 
             image = imageMiniaturefacade.findByName(nomImage);
        }catch (final SignArtException e) {
            Logger.getLogger(ImageMiniatureREST.class.getName()).log(Level.SEVERE, "findByName/Exception", e);
            return dto;
        }
        return entityToDto(image);
    }
    
    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON})
     public List<ImageMiniatureDto> findAll() {
        List<ImageMiniatureDto> listDto = new ArrayList<>();   
            List<ImageMiniature> listEnt = imageMiniaturefacade.findAll();
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
       
    }
     
    
     
     private ImageMiniature dtoToEntity(ImageMiniatureDto dto) {
        
        ImageMiniature entity = new ImageMiniature();
       // entity.setId(dto.getId());
        entity.setNomImage(dto.getNomImage());
        String valeur = dto.getNomImage();
         final byte[] valeurImage = Base64.decodeBase64(valeur.getBytes());
        entity.setValeurImage(valeurImage);
        return entity;
    }
    
    private ImageMiniatureDto entityToDto(ImageMiniature entity){
        ImageMiniatureDto dto = new ImageMiniatureDto();
        dto.setId(entity.getId());
        dto.setNomImage(entity.getNomImage());
         byte[] imageBytes = entity.getValeurImage();
         String imageString = java.util.Base64.getEncoder().encodeToString(imageBytes);
        dto.setValeurImage(imageString);
        return dto;
    }
    
     private byte[] toByteArray(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }
}
