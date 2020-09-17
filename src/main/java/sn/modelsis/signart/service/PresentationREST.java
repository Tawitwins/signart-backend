/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.Presentation;
import sn.modelsis.signart.dto.PresentationDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.PresentationFacade;
import sn.modelsis.signart.facade.ArtisteFacade;





/*import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
*/
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
/**
 *
 * @author Pendaaa
 */
@Stateless
@Path("presentation")
public class PresentationREST {
     private static final String CLIENT_SECRETS= "code_secret_client.json";
    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube","https://www.googleapis.com/auth/youtubepartner","https://www.googleapis.com/auth/youtube.force-ssl");
    
   // private static final String[] SCOPES = new String[] {
    //	      "https://www.googleapis.com/auth/youtube.upload"
    //	    };
    private static final String APPLICATION_NAME = "API code samples";
   /* private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static FileDataStoreFactory dataStoreFactory;
    private static final java.io.File DATA_STORE_FILE =
    	    new File(System.getProperty("user.home"), "doubleclicksearch.json");
    private static  AccessToken token;*/
    @Inject
    PresentationFacade presentationFacade;
    
    @Inject
    ArtisteFacade artisteFacade;
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(PresentationDto dto) throws FileNotFoundException, IOException, Exception {
        presentationFacade.create(dtoToEntity(dto));
       /*   YouTube youtubeService = getService();
        
        // Define the Video object, which will be uploaded as the request body.
        Video video = new Video();
        // TODO: For this request to work, you must replace "YOUR_FILE"
        //       with a pointer to the actual file you are uploading.
        //       The maximum file size for this operation is 128GB.
        File mediaFile = new File("C:\\Users\\SNMBENGUEO\\Videos\\Captures\\skypeVid.mp4");
        InputStreamContent mediaContent =
            new InputStreamContent("video/*",
                new BufferedInputStream(new FileInputStream(mediaFile)));
        mediaContent.setLength(mediaFile.length());

        System.out.println(YouTube.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(YouTube.Videos.Insert.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(Video.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        // Define and execute the API request
        YouTube.Videos.Insert request = youtubeService.videos()
            .insert("id", video, mediaContent);
        request.setQuotaUser("1600");
        request.setFields("id,kind,snippet");
        System.out.println(token);
        request.setOauthToken(token.getTokenValue());
        System.out.println(video);
        System.out.println(request);
        System.out.println(mediaContent.getInputStream());
        System.out.println(mediaContent.getLength());
        Video response = request.execute();
        System.out.println(response);
        //return response;*/
        return Response.status(Response.Status.CREATED).entity(dto).build();
       // return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
//    @POST
//    public Response insertYoutubeVid(PresentationDto dto) throws Exception{
//        YouTube youtubeService = getService();
//        
//        // Define the Video object, which will be uploaded as the request body.
//        Video video = new Video();
//
//        // TODO: For this request to work, you must replace "YOUR_FILE"
//        //       with a pointer to the actual file you are uploading.
//        //       The maximum file size for this operation is 128GB.
//        File mediaFile = new File("/Users/macbookpro/Desktop/test2.mp4");
//        InputStreamContent mediaContent =
//            new InputStreamContent("video/*",
//                new BufferedInputStream(new FileInputStream(mediaFile)));
//        mediaContent.setLength(mediaFile.length());
//
//        // Define and execute the API request
//        YouTube.Videos.Insert request = youtubeService.videos()
//            .insert("id", video, mediaContent);
//        
//        Video response = request.execute();
//        System.out.println(response);
//         return Response.status(Response.Status.CREATED).entity(dto).build();
//    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, PresentationDto dto) {
        presentationFacade.edit(dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        presentationFacade.remove(presentationFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PresentationDto find(@PathParam("id") Integer id) {
        Presentation presentation = presentationFacade.find(id);
        return entityToDto(presentation);
    }
    
    @GET
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public String findtest() {
        return "test presentation";
    }
    
    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public PresentationDto findByArtiste(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        Presentation dto = presentationFacade.findByArtiste(idArtiste);
        return entityToDto(dto);
    }
    
    @GET
    @Path("artisteAll/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PresentationDto> findAllByArtiste(@PathParam("idArtiste") Integer idArtiste) throws SignArtException {
        //Presentation dto = presentationFacade.findByArtiste(idArtiste);
         List<PresentationDto> listDto = new ArrayList<>();
        List<Presentation> listEnt = presentationFacade.findAllByArtiste(idArtiste);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(presentationFacade.count());
    }
    
    private Presentation dtoToEntity(PresentationDto dto) {
        
        Presentation entity = new Presentation();
       // entity.setId(dto.getId());
       entity.setLibelle(dto.getLibelle());
       entity.setVideoId(dto.getVideoId());
       entity.setEtatPubPresentation(dto.getEtatPubPresentation());
       entity.setIdArtiste(findArtiste(dto.getIdArtiste())); 
        return entity;
    }
    

    private PresentationDto entityToDto(Presentation entity){
        PresentationDto dto = new PresentationDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setVideoId(entity.getVideoId());
        dto.setEtatPubPresentation(entity.getEtatPubPresentation());
        dto.setIdArtiste(entity.getIdArtiste().getId());
        return dto;
    }
    
    
    
    private Artiste findArtiste(Integer idArtiste){
        return artisteFacade.find(idArtiste);
    }
    
}
