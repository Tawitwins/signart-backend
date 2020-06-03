/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import com.sun.jersey.multipart.FormDataParam;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
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
import sn.modelsis.signart.ImageProfil;
import sn.modelsis.signart.facade.ImageProfilFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
@Path("imageProfil")
public class ImageProfilREST {
    
    @Inject
    ImageProfilFacade imageProfilFacade;
    
    
  /*  @POST
    @Path("/{id}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response uplaodImage(@PathParam("id") Integer id, @FormDataParam("file") InputStream file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageProfil img = new ImageProfil(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        imageProfilFacade.create(img);
        return Response.status(Response.Status.CREATED).entity(img).build();
    }
    
    @GET
    @Path("/getImageProfil/{imageName}")
    public ImageProfil getImage(@PathParam("imageName") String imageName) throws IOException, DataFormatException {
        final Optional<ImageProfil> retrievedImage = imageProfilFacade.findByName(imageName);
        ImageProfil img = new ImageProfil(retrievedImage.get().getNom(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }*/
    
     public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
    
}
