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
import java.nio.file.Files;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
   
    public final static String PATH = "/opt/";
    public final static String PATHTEST = "C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\";
    
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
        entity.setDescription(dto.getDescription());
        entity.setTechnique(dto.getTechnique());
        entity.setNom(nom);
        return entity;
    }
    
    private OeuvreNumeriqueDto entityToDtoOeuvre(OeuvreNumerique entity) {
        
        OeuvreNumeriqueDto dto = new OeuvreNumeriqueDto();
       dto.setId(entity.getId());
        dto.setAnnee(entity.getAnnee());
        dto.setIdentiteAuteur(entity.getIdentiteAuteur().getId());
        dto.setTitre(entity.getTitre());
        dto.setLargeur(entity.getLargeur());
        dto.setLongueur(entity.getLongueur());
        dto.setMotscles(entity.getMotscles());
        dto.setTarif(entity.getTarif());
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
               ImageIO.write(bImageFromConvert, "jpg", new File(PATH+"images/"+nom+".jpg"));
              // System.out.println(source+"+++++++++++++++++++++++++++++++++++++++++++++source++++++++++++++++++++++++++++++++++++");

               //   ImageIO.write(bImageFromConvert, "jpg", new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\"+nom+".jpg"));
                  

                int type = bImageFromConvert.getType() == 0? BufferedImage.TYPE_INT_ARGB : bImageFromConvert.getType();
                BufferedImage resizeImageJpg = resizeImage(bImageFromConvert, type, largeur, longueur);
                addTextWatermarkMin("SignArt", resizeImageJpg, new File(PATH+"images/min_"+nom+".jpg"));
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
        BufferedImage minImage = ImageIO.read(new File(PATHTEST+"images\\min_"+imageName+".jpg"));

        //BufferedImage minImage = ImageIO.read(new File(PATH+"images/min_"+imageName+".jpg"));

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
    @Path("loadImage/{imageName}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreNumeriqueDto findByImageName(@PathParam("imageName") String imageName) throws IOException, SignArtException {
        System.out.println(imageName+"+++++++++++++++++++++++++++++++++++++++++++++imageName+++++++++++++++++++++++++++++++++++++");

        OeuvreNumeriqueDto dtoImgB = new OeuvreNumeriqueDto();
        ImageNumeriqueDto dtoImg = new ImageNumeriqueDto();
        
        //BufferedImage minImage = ImageIO.read(ImageNumeriqueREST.class.getResource("/Stockage/images/min_"+imageName+".jpg"));
        BufferedImage minImage = ImageIO.read(new File(PATHTEST+"images\\min_"+imageName+".jpg"));

               // BufferedImage minImage = ImageIO.read(new File("/opt/images/"+imageName+".jpg"));

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
            
            BufferedImage minImage = ImageIO.read(new File(PATHTEST+"images\\min_"+dtoImgB.get(j).getNom()+".jpg"));
            //BufferedImage minImage = ImageIO.read(new File(PATH+"images/min_"+dtoImgB.get(j).getNom()+".jpg"));

            System.out.println(dtoImgB.get(j).getNom()+"+++++++++++++++++++++++++++++++++++++++++++++nom+++++++++++++++++++++++++++++++++++++");
            
            String imageBase64 = encodeToString(minImage,"jpg");
            
            System.out.println(dtoImg.get(j).getValue()+"+++++++++++++++++++++++++++++++++++++++++++++nom+++++++++++++++++++++++++++++++++++++");
            dtoImg.get(j).setValue(imageBase64);
            
            
            dtoImgB.get(j).setAvatar(dtoImg.get(j));
            
        }

        return dtoImgB;
        
        
    }
    
    @DELETE
    @Path("deleteOeuvre/{id}")
    public Response remove(@PathParam("id") Integer id) throws SignArtException {
        
             OeuvreNumerique oeuvreNum = oeuvreNumeriqueFacade.findById(id);
             ImageNumerique imageNum = imageNumeriqueFacade.findByValue(oeuvreNum.getNom());
             java.nio.file.Path minImagePath = Paths.get(PATH+"images/min_"+oeuvreNum.getNom()+".jpg");
             java.nio.file.Path imagePath = Paths.get(PATH+"images/"+oeuvreNum.getNom()+".jpg");

            try {
                    Files.delete((java.nio.file.Path) minImagePath);
                    Files.delete((java.nio.file.Path) imagePath);
                   System.out.println("++++++++++++++++++++++++++++++++++++++++successfully removed+++++++++++++++++++++++++++++++++++++");
            } catch (IOException e) {
                System.err.println("+++++++++++++++++++++++++++++++++++++++++++++Unable to delete+++++++++++++++++++++++++++++++++++++++++ ");
                e.printStackTrace();
            }
            imageNumeriqueFacade.remove(imageNum);
            oeuvreNumeriqueFacade.remove(oeuvreNum);
           
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("deleteOeuvreTest/{id}")
    public Response removee2(@PathParam("id") Integer id) throws SignArtException {
        
             OeuvreNumerique oeuvreNum = oeuvreNumeriqueFacade.findById(id);
             ImageNumerique imageNum = imageNumeriqueFacade.findByValue(oeuvreNum.getNom());
             java.nio.file.Path minImagePath = Paths.get(PATHTEST+"images\\min_"+oeuvreNum.getNom()+".jpg");
             java.nio.file.Path imagePath = Paths.get(PATHTEST+"images\\"+oeuvreNum.getNom()+".jpg");

            try {
                    Files.delete((java.nio.file.Path) minImagePath);
                    Files.delete((java.nio.file.Path) imagePath);
                   System.out.println("++++++++++++++++++++++++++++++++++++++++successfully removed+++++++++++++++++++++++++++++++++++++");
            } catch (IOException e) {
                System.err.println("+++++++++++++++++++++++++++++++++++++++++++++Unable to delete+++++++++++++++++++++++++++++++++++++++++ ");
                e.printStackTrace();
            }
            imageNumeriqueFacade.remove(imageNum);
            oeuvreNumeriqueFacade.remove(oeuvreNum);
           
        return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    @Path("updateOeuvre/{idOeuvre}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPhoto(@PathParam("idOeuvre") Integer idOeuvre, OeuvreNumeriqueDto dto) throws SignArtException, IOException {
            OeuvreNumerique oeuvreNum = oeuvreNumeriqueFacade.findById(idOeuvre);
            System.out.println(oeuvreNum.getNom()+"++++++++++++++++++++++++++++++++++++++++oeuvreNum getNom +++++++++++++++++++++++++++++++++++++");
            ImageNumerique imageNum = imageNumeriqueFacade.findByValue(oeuvreNum.getNom());
            
            ImageNumeriqueDto dto2 = dto.getAvatar();
            System.out.println(dto2+"++++++++++++++++++++++++++++++++++++++++dto2 avatar+++++++++++++++++++++++++++++++++++++");

            Integer largeur = dto.getLargeur()/3; 
            Integer longueur = dto.getLongueur()/3;
            
            java.nio.file.Path minImagePath = Paths.get(PATH+"images/min_"+oeuvreNum.getNom()+".jpg");
            java.nio.file.Path imagePath = Paths.get(PATH+"images/"+oeuvreNum.getNom()+".jpg");
            try {
                   Files.delete((java.nio.file.Path) minImagePath);
                    Files.delete((java.nio.file.Path) imagePath);
                   System.out.println(" successfully removed");
            } catch (IOException e) {
                System.err.println("Unable to delete ");
                e.printStackTrace();
            }
            
            oeuvreNum = dtoEntityEdit(dto,idOeuvre);
            System.out.println(oeuvreNum+"++++++++++++++++++++++++++++++++++++++++oeuvreNum +++++++++++++++++++++++++++++++++++++");

            
            System.out.println(imageNum+"++++++++++++++++++++++++++++++++++++++++imageNu +++++++++++++++++++++++++++++++++++++");
            imageNum.setFilename(dto2.getFilename());
            imageNum.setFiletype(dto2.getFiletype());
            System.out.println(oeuvreNum.getNom()+"++++++++++++++++++++++++++++++++++++++++imageNum oeuvreNum.getNom()+++++++++++++++++++++++++++++++++++++");
            imageNum.setValue(oeuvreNum.getNom());
            System.out.println(imageNum.getValue()+"++++++++++++++++++++++++++++++++++++++++imageNum.getValue()+++++++++++++++++++++++++++++++++++++");

            
            String img = dto2.getValue();
            final byte[] imageInByte = Base64.decodeBase64(img.getBytes());
            final InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert;        
            try {
                bImageFromConvert = ImageIO.read(in);
                ImageIO.write(bImageFromConvert, "jpg", new File(PATH+"images/"+oeuvreNum.getNom()+".jpg"));
                int type = bImageFromConvert.getType() == 0? BufferedImage.TYPE_INT_ARGB : bImageFromConvert.getType();
                BufferedImage resizeImageJpg = resizeImage(bImageFromConvert, type, largeur, longueur);
                addTextWatermarkMin("SignArt", resizeImageJpg, new File("/opt/images/min_"+oeuvreNum.getNom()+".jpg"));
            } catch (IOException e) {               
            }     
 
              imageNumeriqueFacade.edit(imageNum);
              oeuvreNumeriqueFacade.edit(oeuvreNum);                                      
             return Response.status(Response.Status.OK).entity(dto).build();
       
    }
    
    @GET
    @Path("loadOeuvre/{idOeuvre}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreNumeriqueDto findByImageName(@PathParam("idOeuvre") Integer idOeuvre) throws IOException, SignArtException {
        OeuvreNumeriqueDto dtoImgB = new OeuvreNumeriqueDto();
        ImageNumeriqueDto dtoImg = new ImageNumeriqueDto();
        dtoImgB = entityToDtoOeuvre(oeuvreNumeriqueFacade.findById(idOeuvre));
        dtoImg = entityToDtoImg(imageNumeriqueFacade.findByValue(dtoImgB.getNom()));     
        dtoImgB.setAvatar(dtoImg);     
        return dtoImgB;               
    }
    
    @PUT
    @Path("updateOeuvreTest/{idOeuvre}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPhoto2(@PathParam("idOeuvre") Integer idOeuvre, OeuvreNumeriqueDto dto) throws SignArtException, IOException {
            OeuvreNumerique oeuvreNum;
            oeuvreNum = oeuvreNumeriqueFacade.findById(idOeuvre);
            System.out.println(oeuvreNum.getNom()+"++++++++++++++++++++++++++++++++++++++++oeuvreNum getNom +++++++++++++++++++++++++++++++++++++");
            ImageNumerique imageNum = imageNumeriqueFacade.findByValue(oeuvreNum.getNom());
            
            ImageNumeriqueDto dto2 = dto.getAvatar();
            System.out.println(dto2+"++++++++++++++++++++++++++++++++++++++++dto2 avatar+++++++++++++++++++++++++++++++++++++");

            Integer largeur = dto.getLargeur()/3; 
            Integer longueur = dto.getLongueur()/3;
            
            java.nio.file.Path minImagePath = Paths.get("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\min_"+oeuvreNum.getNom()+".jpg");
            java.nio.file.Path imagePath = Paths.get(PATHTEST+"image\\"+oeuvreNum.getNom()+".jpg");
            try {
                   Files.delete((java.nio.file.Path) minImagePath);
                    Files.delete((java.nio.file.Path) imagePath);
                   System.out.println(" successfully removed");
            } catch (IOException e) {
                System.err.println("Unable to delete ");
                e.printStackTrace();
            }
            
            oeuvreNum = dtoEntityEdit(dto,idOeuvre);
            System.out.println(oeuvreNum+"++++++++++++++++++++++++++++++++++++++++oeuvreNum +++++++++++++++++++++++++++++++++++++");

            
            System.out.println(imageNum+"++++++++++++++++++++++++++++++++++++++++imageNum +++++++++++++++++++++++++++++++++++++");
            imageNum.setFilename(dto2.getFilename());
            imageNum.setFiletype(dto2.getFiletype());
            System.out.println(oeuvreNum.getNom()+"++++++++++++++++++++++++++++++++++++++++imageNum oeuvreNum.getNom()+++++++++++++++++++++++++++++++++++++");
            imageNum.setValue(oeuvreNum.getNom());
            System.out.println(imageNum.getValue()+"++++++++++++++++++++++++++++++++++++++++imageNum.getValue()+++++++++++++++++++++++++++++++++++++");

            
            String img = dto2.getValue();
            final byte[] imageInByte = Base64.decodeBase64(img.getBytes());
            final InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert;        
            try {
                bImageFromConvert = ImageIO.read(in);
                ImageIO.write(bImageFromConvert, "jpg", new File("C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\"+oeuvreNum.getNom()+".jpg"));
                int type = bImageFromConvert.getType() == 0? BufferedImage.TYPE_INT_ARGB : bImageFromConvert.getType();
                BufferedImage resizeImageJpg = resizeImage(bImageFromConvert, type, largeur, longueur);
                addTextWatermarkMin("SignArt", resizeImageJpg, new File(PATHTEST+"images\\min_"+oeuvreNum.getNom()+".jpg"));
            } catch (IOException e) {               
            }     

              imageNumeriqueFacade.edit(imageNum);
              oeuvreNumeriqueFacade.edit(oeuvreNum);
               
             return Response.status(Response.Status.OK).entity(dto).build();
            //return Response.status(Response.Status.OK).build();
       
    }
    
     private OeuvreNumerique dtoEntityEdit(OeuvreNumeriqueDto dto, Integer idOeuvre) throws SignArtException {
        OeuvreNumerique oeuvreNum = oeuvreNumeriqueFacade.findById(idOeuvre);   
        String identite = artisteFacade.findById(dto.getIdentiteAuteur()).getIdentite();
        String nom = dto.getTitre()+"_"+identite;
        
        oeuvreNum.setAnnee(dto.getAnnee());
        oeuvreNum.setIdentiteAuteur(artisteFacade.findById(dto.getIdentiteAuteur()));
        oeuvreNum.setTitre(dto.getTitre());
        oeuvreNum.setLargeur(dto.getLargeur());
        oeuvreNum.setLongueur(dto.getLongueur());
        oeuvreNum.setMotscles(dto.getMotscles());
        oeuvreNum.setTarif(dto.getTarif());
        oeuvreNum.setDescription(dto.getDescription());
        oeuvreNum.setTechnique(dto.getTechnique());
        oeuvreNum.setNom(nom);
        return oeuvreNum;
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
