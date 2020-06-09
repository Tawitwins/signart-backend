package sn.modelsis.signart.service;

import com.sun.jersey.multipart.FormDataParam;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.io.IOUtils;
import sn.modelsis.signart.Artiste;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.FormDataParam;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.dto.ArtisteDto;
import sn.modelsis.signart.dto.ImageDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.OeuvreSouscriptionFacade;
import sun.misc.BASE64Encoder;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("image")
public class ImageREST {

    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    OeuvreSouscriptionFacade oeuvreSouscriptionFacade;
    @Inject
    ArtisteFacade artisteFacade;

    public ImageREST() {       
    }
    
    public static String encodeToString(byte[] imageBytes, String type) {
       String imageString = null;
       ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            //ImageIO.write(image, type, bos);
            imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    @POST
    @Path("/{id}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    //public Response create(@PathParam("id") Integer id, final MultipartFormDataInput input){//, @Context final SecurityContext securityContext) {
    public Response create(@PathParam("id") Integer id, @FormDataParam("file") InputStream uploadedInputStream) {
        //,@FormDataParam("file") FormDataContentDisposition fileDetail) {
        //String fileName = "";
        Oeuvre oeuvre;
        try {
            //final String idOeuvre = input.getFormDataPart("idOeuvre", String.class, null);
            //final String originalFileName = input.getFormDataPart("originalFileName", String.class, null);
            //final Map<String, List<InputPart>> formParts = input.getFormDataMap();
            //final List<InputPart> inPart = formParts.get("selectedFile");

            //if (Objects.isNull(inPart) || inPart.isEmpty()) {
            //    return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            //}
            if (Objects.isNull(uploadedInputStream)) {
                return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            }

            /*if (Objects.isNull(originalFileName) || originalFileName.isEmpty()) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Original filename must be given").build();
            }*/
            if (Objects.isNull(id)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("idOeuvre must be given").build();
            }
            //fichier.setOriginalFileName(originalFileName);
            oeuvre = oeuvreFacade.find(id);

            //if (inPart.size() > 1) {
            //    return Response.status(Response.Status.UNAUTHORIZED).entity("You should attach one file ").build();
            //}
            byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
            oeuvre.setImage(bytes);
            oeuvreFacade.edit(oeuvre);

//          for (final InputPart inputPart : inPart) {
//                // Recuperation du nom original du fichier a partir du Header
//                //final MultivaluedMap<String, String> headers = inputPart.getHeaders();
//                //fileName = Utils.parseFileName(headers);
//
//                // Creation du fichier a partir du Stream recupere
//                final InputStream istream = inputPart.getBody(InputStream.class, null);
//
//                byte[] bytes = IOUtils.toByteArray(istream);
//                oeuvre.setImage(bytes);
//                oeuvreFacade.edit(oeuvre);
//            }
            //fichier = fichierService.createFichier(fichier);
        } catch (IOException e) {
            //logger.warn("Une erreur est survenue lors de l'ajout de la piece de code ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity("Image sauvegardée").build();

    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/oeuvre/{id}")
    public Response findOeuvreImage(@PathParam("id") Integer id) {
        try {
        //ImageDto imgdto = new ImageDto();
            Oeuvre oeuvre = oeuvreFacade.find(id);
            //System.out.println(oeuvre.getImage()+"+++++++++++++++++++++++++++++++++++++++OEUVRE IMAGE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

           // BufferedImage res = createImageFromBytes(oeuvre.getImage());
           //             System.out.println(res+"+++++++++++++++++++++++++++++++++++++++RES++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

          /* BufferedImage resimg = addTextWatermarkMin("SignArt",res);
           System.out.println(resimg+"+++++++++++++++++++++++++++++++++++++++water++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String imageBase = encodeToString(resimg,"jpg");            
            System.out.println(imageBase+"+++++++++++++++++++++++++++++++++++++++IMAGE base++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            imgdto.setValeur(imageBase);*/
            
            final ResponseBuilder response = Response.ok(oeuvre.getImage());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");
            return response.build();
            //return imgdto;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/oeuvreSouscription/{id}")
    public Response findOeuvreSouscriptionImage(@PathParam("id") Integer id) {
        try {
        //ImageDto imgdto = new ImageDto();
            OeuvreSouscription oeuvreSousc = oeuvreSouscriptionFacade.find(id);
            //System.out.println(oeuvre.getImage()+"+++++++++++++++++++++++++++++++++++++++OEUVRE IMAGE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

           // BufferedImage res = createImageFromBytes(oeuvre.getImage());
           //             System.out.println(res+"+++++++++++++++++++++++++++++++++++++++RES++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

          /* BufferedImage resimg = addTextWatermarkMin("SignArt",res);
           System.out.println(resimg+"+++++++++++++++++++++++++++++++++++++++water++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String imageBase = encodeToString(resimg,"jpg");            
            System.out.println(imageBase+"+++++++++++++++++++++++++++++++++++++++IMAGE base++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            imgdto.setValeur(imageBase);*/
            
            final ResponseBuilder response = Response.ok(oeuvreSousc.getImage());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");
            return response.build();
            //return imgdto;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    static BufferedImage addTextWatermarkMin(String text,  BufferedImage sourceImage) {
	   
	       
	        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
	        // initializes necessary graphic properties
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	        g2d.setComposite(alphaChannel);
	        g2d.setColor(Color.BLACK);
	        g2d.setFont(new Font("Arial", Font.BOLD, 50));
	        FontMetrics fontMetrics = g2d.getFontMetrics();
	        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
	        // calculates the coordinate where the String is painted
	        int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
	        int centerY = sourceImage.getHeight() / 2;
	        // paints the textual watermark
	        g2d.drawString(text, centerX, centerY);
                //byte[] imageBytes = ((DataBufferByte) sourceImage.getData().getDataBuffer()).getData();
               return sourceImage;
	       /* ImageIO.write(sourceImage, "jpg", destImageFile);
	        g2d.dispose();
	        System.out.println("The tex watermark is added to the image.");*/
	 	 	    
	}
    
    
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/oeuvre/miniature/{id}")
    public Response findOeuvreMiniature(@PathParam("id") Integer id) {
        try {
            Oeuvre oeuvre = oeuvreFacade.find(id);
            final ResponseBuilder response = Response.ok(oeuvre.getMiniature());
            response.header("Content-Disposition", "attachment;filename=" + "miniature.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/artiste/{id}")
    public Response findArtisteImage(@PathParam("id") Integer id) {
        try {
            Artiste artiste = artisteFacade.find(id);
           // System.out.println(artiste+"+++++++++++++++++++++++++++++++++++++++artiste++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            byte[] bytes = artiste.getPhoto();
            //System.out.println(bytes+"+++++++++++++++++++++++++++++++++++++++bytes++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            final ResponseBuilder response = Response.ok(artiste.getPhoto());
            //System.out.println(response+"+++++++++++++++++++++++++++++++++++++++response++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }
    
    
    @PUT
    @Path("oeuvre/update/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id) throws SignArtException {
       Oeuvre oeuvre = oeuvreFacade.find(id);
       byte[] imgRes = oeuvre.getImage();
       BufferedImage bimg = createImageFromBytes(imgRes);
       BufferedImage waterImg = addTextWatermark("SignArt",bimg);
       byte[] imgfinal = createBytesFromImage(waterImg);
       
       oeuvre.setImage(imgfinal);
        oeuvreFacade.edit(oeuvre);
        return Response.status(Response.Status.OK).build();
    }
    
    private BufferedImage createImageFromBytes(byte[] imageData) {
    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
    try {
        return ImageIO.read(bais);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
   }
    
    public static BufferedImage addTextWatermark(String text, BufferedImage sourceImage) {
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // initializes necessary graphic properties
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        g2d.setComposite(alphaChannel);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 64));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

        // calculates the coordinate where the String is painted
        int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = sourceImage.getHeight() / 2;

        // paints the textual watermark
        g2d.drawString(text, centerX, centerY);

        return sourceImage;
    }
    
    private byte[] createBytesFromImage(BufferedImage image) {
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(image,"png",baos);

        byte[] imageBytes = baos.toByteArray();
        baos.close();
        return imageBytes;

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

}
