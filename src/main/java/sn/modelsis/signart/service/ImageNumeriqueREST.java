package sn.modelsis.signart.service;

import com.sun.jersey.multipart.FormDataParam;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javassist.bytecode.ByteArray;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import sn.modelsis.signart.ImageNumerique;
import sn.modelsis.signart.OeuvreNumerique;

import sn.modelsis.signart.dto.OeuvreNumeriqueDto;
import sn.modelsis.signart.dto.ImageNumeriqueDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.OeuvreNumeriqueFacade;
import sn.modelsis.signart.facade.ImageNumeriqueFacade;


import sun.misc.BASE64Encoder;
/*import sn.modelsis.signart.Artiste;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.FormDataParam;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.OeuvreFacade;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author SNLOM
 */
@Stateless 
@Path("numerique")
public class ImageNumeriqueREST {

    /*@Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    ArtisteFacade artisteFacade;*/
   
    
    @Inject
    OeuvreNumeriqueFacade oeuvreNumeriqueFacade;
    
    @Inject
    ImageNumeriqueFacade imageNumeriqueFacade;

    @Inject
    ArtisteFacade artisteFacade;
    public ImageNumeriqueREST() {
    }
    
    private OeuvreNumerique dtoToEntityOeuvre(OeuvreNumeriqueDto dto, String nom) throws SignArtException {
        
        OeuvreNumerique entity = new OeuvreNumerique();
       // entity.setId(dto.getId());
        entity.setAnnee(dto.getAnnee());
       entity.setIdentiteAuteur(artisteFacade.findById(dto.getIdentiteAuteur()));
        entity.setTitre(dto.getTitre());
        entity.setLargeur(dto.getLargeur());
        entity.setLongueur(dto.getLongueur());
        entity.setMotscles(dto.getMotscles());
        entity.setTarif(dto.getTarif());
        entity.setCategorie(dto.getCategorie());
        entity.setDescription(dto.getDescription());
        entity.setTechnique(dto.getTechnique());
        entity.setNom(nom);
        return entity;
    }
    
    private OeuvreNumeriqueDto entityToDtoOeuvre(OeuvreNumerique entity) {
        
        OeuvreNumeriqueDto dto = new OeuvreNumeriqueDto();
       // entity.setId(dto.getId());
        dto.setAnnee(entity.getAnnee());
        dto.setIdentiteAuteur(entity.getIdentiteAuteur().getId());
        dto.setTitre(entity.getTitre());
        dto.setLargeur(entity.getLargeur());
        dto.setLongueur(entity.getLongueur());
        dto.setMotscles(entity.getMotscles());
        dto.setTarif(entity.getTarif());
        dto.setCategorie(entity.getCategorie());
        dto.setDescription(entity.getDescription());
        dto.setTechnique(entity.getTechnique());
        dto.setNom(entity.getNom());
        return dto;
    }
    
    private ImageNumerique dtoToEntityImg(ImageNumeriqueDto dto, String nom) {
        
        ImageNumerique entity = new ImageNumerique();
       // entity.setId(dto.getId());
        entity.setFilename(dto.getFilename());
        entity.setFiletype(dto.getFiletype());
        entity.setValue(nom);
        return entity;
    }
    
    private ImageNumeriqueDto entityToDtoImg(ImageNumerique entity) {
        
        ImageNumeriqueDto dto = new ImageNumeriqueDto();
       // entity.setId(dto.getId());
        dto.setFilename(entity.getFilename());
        dto.setFiletype(entity.getFiletype());
        dto.setValue(entity.getValue());
        return dto;
    }
    
    
    static void addTextWatermarkMin(String text,  BufferedImage sourceImage, File destImageFile) {
	    try {
	       
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
	        ImageIO.write(sourceImage, "jpg", destImageFile);
	        g2d.dispose();
	        System.out.println("The tex watermark is added to the image.");
	 
	    } catch (IOException ex) {
	        System.err.println(ex);
	    }
	}
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height){
	    BufferedImage resizedImage = new BufferedImage(width, height, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, width, height, null);
	    g.dispose();

	    return resizedImage;
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
    
    @POST
    @Path("output")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(OeuvreNumeriqueDto dto) throws SignArtException{
        OeuvreNumeriqueDto imgbrut = dto;
        ImageNumeriqueDto imgdto = dto.getAvatar();
               // System.out.println(imgdto.getValue()+"+++++++++++++++++++++++++++++++++++++++++++++largeur++++++++++++++++++++++++++++++++++++");

        Integer largeur = dto.getLargeur()/3; 
        System.out.println(largeur+"+++++++++++++++++++++++++++++++++++++++++++++largeur++++++++++++++++++++++++++++++++++++");

        Integer longueur = dto.getLongueur()/3;
        System.out.println(longueur+"+++++++++++++++++++++++++++++++++++++++++++++longueur++++++++++++++++++++++++++++++++++++");

        String identite = artisteFacade.findById(dto.getIdentiteAuteur()).getIdentite();
        String nom = dto.getTitre()+"_"+identite;
        System.out.println(nom+"+++++++++++++++++++++++++++++++++++++++++++++nom++++++++++++++++++++++++++++++++++++");

       
        
        String img = imgdto.getValue();
                System.out.println(img+"+++++++++++++++++++++++++++++++++++++++++++++imhg++++++++++++++++++++++++++++++++++++");

        final byte[] imageInByte = Base64.decodeBase64(img.getBytes());
                        System.out.println(imageInByte+"+++++++++++++++++++++++++++++++++++++++++++++imageInByte++++++++++++++++++++++++++++++++++++");

        final InputStream in = new ByteArrayInputStream(imageInByte);
                                System.out.println(in+"+++++++++++++++++++++++++++++++++++++++++++++InputStream++++++++++++++++++++++++++++++++++++");
         BufferedImage bImageFromConvert;
          
            try {
                bImageFromConvert = ImageIO.read(in);
                System.out.println(bImageFromConvert+"+++++++++++++++++++++++++++++++++++++++++++++bImageFromConvert++++++++++++++++++++++++++++++++++++");
                ImageIO.write(bImageFromConvert, "jpg", new File("/opt/images/"+nom+".jpg"));
                //URL source = ImageNumeriqueREST.class.getResource("/Stockage/images");
              // System.out.println(source+"+++++++++++++++++++++++++++++++++++++++++++++source++++++++++++++++++++++++++++++++++++");

             //     ImageIO.write(bImageFromConvert, "jpg", new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\"+nom+".jpg"));
                  

                int type = bImageFromConvert.getType() == 0? BufferedImage.TYPE_INT_ARGB : bImageFromConvert.getType();
                BufferedImage resizeImageJpg = resizeImage(bImageFromConvert, type, largeur, longueur);
                addTextWatermarkMin("SignArt", resizeImageJpg, new File("/opt/images/min_"+nom+".jpg"));
                // addTextWatermarkMin("SignArt", resizeImageJpg, new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\min_"+nom+".jpg"));
               //  addTextWatermarkMin("SignArt", resizeImageJpg, new File( "../../../../../../resources/stockage/images/min_"+nom+".jpg"));
            } catch (IOException e) {               
            }     
       imageNumeriqueFacade.create(dtoToEntityImg(imgdto,nom));
       oeuvreNumeriqueFacade.create(dtoToEntityOeuvre(dto,nom)); 
        //BufferedImage originalImage = ImageIO.read(new File("/Users/macbookpro/Desktop/art.jpg"));     
       /* System.out.println(dto+"+++++++++++++++++++++++++++++++++++++++++++++dto++++++++++++++++++++++++++++++++++++");    
        System.out.println(imgbrut.getName()+"+++++++++++++++++++++++++++++++++++++++++++++imgbrut++++++++++++++++++++++++++++++++++++");
        String name = "salut";
        name = imgbrut.getName();
        System.out.println(name+"+++++++++++++++++++++++++++++++++++++++++++++name++++++++++++++++++++++++++++++++++++"); 
        System.out.println(img+"+++++++++++++++++++++++++++++++++++++++++++++img++++++++++++++++++++++++++++++++++++");
        System.out.println(imageInByte+"+++++++++++++++++++++++++++++++++++++++++++++imageInByte++++++++++++++++++++++++++++++++++++");*/

           //return Response.status(Response.Status.CREATED).build();
           return Response.status(Response.Status.CREATED).entity(imgbrut).build();
    }
    

   /* @POST
    @Path("upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    //public Response create(@PathParam("id") Integer id, final MultipartFormDataInput input){//, @Context final SecurityContext securityContext) {
    public Response create(@FormDataParam("file") InputStream uploadedInputStream) throws IOException {
        //,@FormDataParam("file") FormDataContentDisposition fileDetail) {
        //String fileName = "";
        ImageProfil image = new ImageProfil("JEANTHEO","image/jpg");
       // Logger logger = Logger.getLogger(getClass().getName());
       //  logger("");
      
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++saluuuuuuuu+++++++++++++++++++++++++++++++++++++");
        System.out.println(uploadedInputStream+"+++++++++++++++++++++++++++++++++++++++++++++upload+++++++++++++++++++++++++++++++++++++");
        
        try {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++dans le try+++++++++++++++++++++++++++++++++++++");

            //final String idOeuvre = input.getFormDataPart("idOeuvre", String.class, null);
            //final String originalFileName = input.getFormDataPart("originalFileName", String.class, null);
            //final Map<String, List<InputPart>> formParts = input.getFormDataMap();
            //final List<InputPart> inPart = formParts.get("selectedFile");

            //if (Objects.isNull(inPart) || inPart.isEmpty()) {
            //    return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            //}
           /* if (Objects.isNull(uploadedInputStream)) {
                return Response.status(Response.Status.NOT_FOUND).entity("a file must be attached").build();
            }*/

            /*if (Objects.isNull(originalFileName) || originalFileName.isEmpty()) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Original filename must be given").build();
            }
            if (Objects.isNull(id)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("idOeuvre must be given").build();
            }*/
            //fichier.setOriginalFileName(originalFileName);
           // oeuvre = oeuvreFacade.find(id);
         
           

            //if (inPart.size() > 1) {
            //    return Response.status(Response.Status.UNAUTHORIZED).entity("You should attach one file ").build();
            //}
            
           /* byte[] imageAr = new byte[40324];
            
            for (int i = 0; i < 40324; i++) {
               imageAr[i] = (byte) uploadedInputStream.read();
            }*/
           /* BufferedImage img=ImageIO.read(new File("/Users/macbookpro/Desktop/test1.JPG"));
            System.out.println(img+"+++++++++++++++++++++++++++++++++++++++++++++img++++++++++++++++++++++++++++++++++++");
            ImageIO.write(img, "jpg", new File("/Users/macbookpro/Desktop/stockage/output.jpg"));

            
             byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
            //ByteArray byt = new ByteArray();
            System.out.println(bytes+"+++++++++++++++++++++++++++++++++++++++++++++bytes+++++++++++++++++++++++++++++++++++++");
       
            
            

            
            image.setPicByte(bytes);
            //System.out.println(bytes);
            
            
             imageProfilFacade.create(image);
            
            //InputStream stream = request.getInputStream();
            //bytes = IOUtils.toByteArray(stream);
            //File file = new File("myfile/1.jpg");
           ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
          System.out.println(bis+"+++++++++++++++++++++++++++++++++++++++++++++bis++++++++++++++++++++++++++++++++++++");
          
            //BufferedImage img = ImageIO.read(bis);
           

            //ImageIO.write(img, "jpg", new File("/Users/macbookpro/Desktop/stockage/output.jpg") );
            // File file = new File("/Users/macbookpro/Desktop/stockage/JEANTHEO.jpg");
            //BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
           // ImageIO.write(img, "jpg", file);
           
           
            // ImageInputStream sourceImage = ImageIO.createImageInputStream(bytes);
            //ImageIO.write((RenderedImage) sourceImage, "jpg", destImageFile);
            
            

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
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++fin try+++++++++++++++++++++++++++++++++++++");

        } catch (IOException e) {
            //logger.warn("Une erreur est survenue lors de l'ajout de la piece de code ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity("Image sauvegardée").build();

    }*/

    /*@GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Path("/load/{id}")
    public Response findOeuvreImage(@PathParam("id") Long id) throws SignArtException {
        try {
            ImageProfil image = imageProfilFacade.find(id);
            final ResponseBuilder response = Response.ok(image.getPicByte());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");
            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }*/
    
    @GET
    @Path("test")
    public String getImage() {
        
        return "test image SLOM";
    }
    
    @GET
    @Path("load/{imageName}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreNumeriqueDto findByName(@PathParam("imageName") String imageName) throws IOException, SignArtException {
        System.out.println(imageName+"+++++++++++++++++++++++++++++++++++++++++++++imageName+++++++++++++++++++++++++++++++++++++");

        OeuvreNumeriqueDto dtoImgB = new OeuvreNumeriqueDto();
        ImageNumeriqueDto dtoImg = new ImageNumeriqueDto();
        
        //BufferedImage minImage = ImageIO.read(ImageNumeriqueREST.class.getResource("/Stockage/images/min_"+imageName+".jpg"));
        //BufferedImage minImage = ImageIO.read(new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\min_"+imageName+".jpg"));

                BufferedImage minImage = ImageIO.read(new File("/opt/images/min_"+imageName+".jpg"));

             //   System.out.println(minImage+"+++++++++++++++++++++++++++++++++++++++++++++minImage+++++++++++++++++++++++++++++++++++++");
        String imageBase64 = encodeToString(minImage,"jpg");
        System.out.println(imageBase64+"+++++++++++++++++++++++++++++++++++++++++++++imageBase64+++++++++++++++++++++++++++++++++++++");
        dtoImgB = entityToDtoOeuvre(oeuvreNumeriqueFacade.findByName(imageName));
        dtoImg = entityToDtoImg(imageNumeriqueFacade.findByValue(imageName));     
        dtoImg.setValue(imageBase64);
        dtoImgB.setAvatar(dtoImg);     
        return dtoImgB;               
    }
    
   @GET
    @Path("loadAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreNumeriqueDto> findAll() throws IOException, SignArtException {
        List<OeuvreNumeriqueDto> dtoImgB = new ArrayList<>();
        List<ImageNumeriqueDto> dtoImg = new ArrayList<>();
        
        for(int i=0; i< oeuvreNumeriqueFacade.findAll().size(); i++){
            dtoImgB.add(entityToDtoOeuvre(oeuvreNumeriqueFacade.findAll().get(i)));
        }
        
        for(int j=0; j< oeuvreNumeriqueFacade.findAll().size(); j++){
            dtoImg.add(entityToDtoImg(imageNumeriqueFacade.findAll().get(j)));
        }
                
        for(int j=0; j< dtoImgB.size(); j++){
            System.out.println(dtoImgB.get(j).getNom()+"+++++++++++++++++++++++++++++++++++++++++++++nom+++++++++++++++++++++++++++++++++++++");
            
            //BufferedImage minImage = ImageIO.read(new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\min_"+dtoImgB.get(j).getNom()+".jpg"));
            BufferedImage minImage = ImageIO.read(new File("/opt/images/min_"+dtoImgB.get(j).getNom()+".jpg"));

            System.out.println(dtoImgB.get(j).getNom()+"+++++++++++++++++++++++++++++++++++++++++++++nom+++++++++++++++++++++++++++++++++++++");
            
            String imageBase64 = encodeToString(minImage,"jpg");
            
            System.out.println(dtoImg.get(j).getValue()+"+++++++++++++++++++++++++++++++++++++++++++++nom+++++++++++++++++++++++++++++++++++++");
            dtoImg.get(j).setValue(imageBase64);
            
            
            dtoImgB.get(j).setAvatar(dtoImg.get(j));
            
        }

        return dtoImgB;
        
        
    }
    /*@GET
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
            final ResponseBuilder response = Response.ok(artiste.getPhoto());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");

            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }*/

}
