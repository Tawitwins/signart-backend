package sn.modelsis.signart.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import static javax.mail.Session.getDefaultInstance;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.MessagesTchats;
import static sn.modelsis.signart.MessagesTchats_.filename;
import sn.modelsis.signart.converter.MessagesTchatsConverter;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.dto.MessagesTchatsDto;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.MessagesTchatsFacade;
import sn.modelsis.signart.facade.VisiteurFacade;

/**
 *
 * @author SNMBENGUEO
 */
@Stateless
@Path("admin")
@ServerEndpoint(value="/admin/Ws/{username}/{isAdmin}/{idUser}/{profilUser}", 
                configurator=TchatWSFacadeREST.EndpointConfigurator.class)

public class TchatWSFacadeREST {

    private @Inject MessagesTchatsFacade messagesTchatsFacade;
    private @Inject MessagesTchatsConverter messagesTchatsConverter;
    
    
    private static TchatWSFacadeREST singleton = new TchatWSFacadeREST();
    private List<MessagesTchatsDto> messages;
    private List<MessagesTchatsDto> messagesAdmin; 
    private MessagesTchatsDto messageTmp;


    /**
     * Acquisition de notre unique instance ChatRoom 
     * @return 
     */
    public static TchatWSFacadeREST getInstance() {
        return TchatWSFacadeREST.singleton;
    }

    @POST
    @Path(value = "/sendMail")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response SendMail(EmailDto emailDto) {
      String to = emailDto.getTo();
      String host = "smtp.gmail.com";//or IP address
      //String PORT="587";
      String PORT="465";
      String userSMTP = "SignArtEmail2020";
      String passwordSMTP = "Sign@rtEm@il2020";
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.put("mail.smtp.host", host); 
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", PORT); 
      properties.put("mail.transport.protocol", "smtp");
      //properties.put("mail.smtp.starttls.enable", "true");
      //properties.put("mail.smtp.startssl.enable", "true");
      properties.put("mail.smtp.ssl.enable", "true");

//      javax.mail.Authenticator authentificator= new javax.mail.Authenticator() { 
//      @Override
//      protected  PasswordAuthentication getPasswordAuthentication() {  
//        return new PasswordAuthentication(userSMTP,passwordSMTP);
//      }  };
      javax.mail.Session session = javax.mail.Session.getInstance(properties,new javax.mail.Authenticator() {  
        protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(userSMTP,passwordSMTP);  
        }  
      });  
//      ,new javax.mail.Authenticator() {  
//      protected PasswordAuthentication getPasswordAuthentication() {  
//        return new PasswordAuthentication(userSMTP,passwordSMTP);  
//      }  
//    }
      
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject(emailDto.getObjet()); 
         message.setText(emailDto.getContent());  
  
        // Transport transport = session.getTransport("smtp");
         // Send message 
 //        transport.connect(host, "SignArtEmail2020@gmail.com","Sign@rtEm@il2020");
         Transport.send(message);  
         System.out.println("message sent successfully ....");
  
      }catch (MessagingException mex) {
          mex.printStackTrace();
          return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
      }
      return Response.status(Response.Status.CREATED).entity(emailDto).build();
    }
     /**
     *
     * @param asyncResponse
     * @param signartFile
     * @throws IOException
     */
    @PUT
    @Path(value = "/files/{filename}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.TEXT_PLAIN})
    @Asynchronous
    public void PostFile(@Suspended final AsyncResponse asyncResponse, String fileContent,@PathParam("filename") String filename) throws IOException {
        SignartFile signartFile= new SignartFile();
        fileContent = fileContent.split("base64,")[1];
        signartFile.setName(filename);
        signartFile.setContent(Base64.decodeBase64(fileContent));
        
        asyncResponse.resume(doPostFile(signartFile));
    }

    private Response doPostFile(SignartFile signartFile) throws IOException {
        byte[] data = signartFile.getContent();
        java.nio.file.Path filee = (java.nio.file.Path) Paths.get("./"+signartFile.getName());
        Files.write(filee, data);
        return Response.status(Response.Status.CREATED).entity(signartFile).build();
    }
    @DELETE
    @Path("/filesRemove/{filename}")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean DeleteFile(@PathParam("filename") String filename) throws IOException {
        return Files.deleteIfExists((java.nio.file.Path) Paths.get("./"+filename));
    }
    
    @GET
    @Path("/GetFiles/{filename}")
    @Produces(MediaType.TEXT_PLAIN)
    public String GetFile(@PathParam("filename") String filename) throws IOException {
        byte [] myFile = Files.readAllBytes((java.nio.file.Path) Paths.get("./"+filename));
        String File = Base64.encodeBase64String(myFile);
        String vof = String.valueOf(myFile);
        String mimeType = Files.probeContentType((java.nio.file.Path) Paths.get("./"+filename));
        return mimeType+";"+File;
    }
    /**
     * On maintient toutes les sessions utilisateurs dans une collection.
     */
    private final Hashtable<String, Session> sessions = new Hashtable<>();
    
    /**
     * Cette méthode est déclenchée à chaque connexion d'un utilisateur.
     * @param session
     * @param username
     * @param isAdmin
     * @param idUser
     * @param profilUser
     */
    @OnOpen
    public void open(Session session, @javax.websocket.server.PathParam("username") String username,@javax.websocket.server.PathParam("isAdmin") boolean isAdmin
            ,@javax.websocket.server.PathParam("idUser") Integer idUser,@javax.websocket.server.PathParam("profilUser") String profilUser) {
        try {
            session.getUserProperties().put( "username", username );
            session.getUserProperties().put( "idUser", idUser );
            session.getUserProperties().put( "profilUser", profilUser );
            sessions.put( session.getId(), session );
        
        
            messages = ConvertListEntityToDto(messagesTchatsFacade.findAllMine(idUser));

            sendMessageCon( "Admin >>> Data sent to the user " + username,session);
            sendMessageAll("Admin >>> Connection established for " + username);
            if(isAdmin==true)
            {
                 messagesAdmin = ConvertListEntityToDto(messagesTchatsFacade.findAllForAdmin(idUser));
                 sendMessageData( "Admin >>> Data sent to the (admin) user" + username,session);
            }
            }catch( Exception exception ) {
                System.out.println( "ERROR: on open " + exception );
                }   
        
    }

    /**
     * Cette méthode est déclenchée à chaque déconnexion d'un utilisateur.
     * @param session
     */
    @OnClose
    public void close(Session session) {
        String username = (String) session.getUserProperties().get( "username" );
        sessions.remove( session.getId() );
        sendMessageAll( "Admin >>> Connection closed for " + username );
    }

    /**
     * Cette méthode est déclenchée en cas d'erreur de communication.
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println( "Error: " + error.getMessage() );
    }

    /**
     * Cette méthode est déclenchée à chaque réception d'un message utilisateur.
     * @param messagePackage
     * @param session
     */
    @OnMessage
    public void handleMessage(String messagePackage, Session session) {
        try{
            String username = (String) session.getUserProperties().get( "username" );
            String fullMessage = username + " >>> " + messagePackage; 
            List<String> splittedMessage = Arrays.asList(messagePackage.split(Pattern.quote("|")));
            messageTmp = new MessagesTchatsDto();
            int headerCount=splittedMessage.get(0).length()+splittedMessage.get(1).length()+splittedMessage.get(2).length()+3;
            String message=messagePackage.substring(headerCount);
            messageTmp.setContenu(message);
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            //java.sql.Timestamp dateEnvoi = new java.sql.Timestamp(currentDate.getTime());
            System.out.println( calendar+"/"+currentDate+"/"+currentDate );
            messageTmp.setDateEnvoi(currentDate);
            messageTmp.setUsername(username);
            messageTmp.setFilename(splittedMessage.get(2));
            messageTmp.setIdSender(((Integer)session.getUserProperties().get( "idUser" )));
            messageTmp.setIdReceiver(Integer.parseInt(splittedMessage.get(0)));
            messageTmp.setProfilSender((String)session.getUserProperties().get( "profilUser" ));
            messageTmp.setProfilReceiver(splittedMessage.get(1));
            sendMessageTmp( messageTmp, session);
            }catch( Exception exception ) {
                System.out.println( "ERROR: on message " + exception );
                } 
    }

    /**
     * Une méthode privée, spécifique à notre exemple.
     * Elle permet l'envoie d'un message au participant qui vient juste de se connecter.
     */
    private void sendMessageCon( String fullMessage,Session sessionCon ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message à tout le monde.
       
            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String messagesJson = ow.writeValueAsString(messages);
                sessionCon.getBasicRemote().sendObject(messagesJson);
                sessionCon.getBasicRemote().sendText( fullMessage );
            } catch( Exception exception ) {
                System.out.println( "ERROR: cannot send message to " + sessionCon.getId() );
            }   
    }
    /**
     * Une méthode privée, spécifique à notre exemple.
     * Elle permet l'envoie d'un message au participant qui vient juste de se connecter.
     */
    private void sendMessageData( String fullMessage,Session sessionCon ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message à tout le monde.
       
            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String messagesJson = ow.writeValueAsString(messagesAdmin);
                sessionCon.getBasicRemote().sendText( fullMessage );
                sessionCon.getBasicRemote().sendObject(messagesJson);
            } catch( Exception exception ) {
                System.out.println( "ERROR: cannot send message to " + sessionCon.getId() );
            }   
    }
    
    
     private void sendMessageAll( String fullMessage ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message à tout le monde.
        sessions.values().forEach((session) -> {
            try {
                session.getBasicRemote().sendText( fullMessage );
            } catch( Exception exception ) {
                System.out.println( "ERROR: cannot send message to " + session.getId() );
            }
        });       
    }
    
    /**
     * Une méthode privée, spécifique à notre exemple.
     * Elle permet l'envoie de donnés à une session spécifique.
     */
    private void sendMessageTmp( MessagesTchatsDto messageTmp, Session session ) {
        // Affichage sur la console du server Web.
        System.out.println( messageTmp );  
        
         try {
             ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
             String messagesJson = ow.writeValueAsString(messageTmp);
             session.getBasicRemote().sendObject(messagesJson );
             Session Reveiversession = sessions.values().stream()   
             .filter(sess -> messageTmp.getIdReceiver().equals((Integer)sess.getUserProperties().get( "idUser" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )))
                //.filter(sess -> messageTmp.getUsername().equals((String)sess.getUserProperties().get( "username" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )))
                .findAny()
                .orElse(null);
            
         
        System.out.println(Reveiversession );
        // On envoie le message au destinateur seulement.
        try {
            
            Reveiversession.getBasicRemote().sendObject(messagesJson );
        } catch( Exception exception ) {
            System.out.println( "ERROR: cannot send message to " + Reveiversession.getId() );
        }
        }catch(Exception exception){
            System.out.println( "ERROR: sessions is empty"+ exception);
        }
         finally{
             messagesTchatsFacade.create(messagesTchatsConverter.dtoToEntity(messageTmp));
        }
//        // On envoie le message à tout le monde.
//        sessions.values().forEach((session) -> {
//            try {
//                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//                String messagesJson = ow.writeValueAsString(messageTmp);
//                session.getBasicRemote().sendObject(messagesJson );
//            } catch( Exception exception ) {
//                System.out.println( "ERROR: cannot send message to " + session.getId() );
//            }
//        });       
    }
    private List<MessagesTchatsDto> ConvertListEntityToDto(List<MessagesTchats> listEnt )
    {
        List<MessagesTchatsDto> listDto = new ArrayList<>();
        //List<MessagesTchats> listEnt = messagesTchatsFacade.findAll();
        if (listEnt != null) {
                listEnt.stream().map((MessagesTchats) -> {
                    return messagesTchatsConverter.entityToDto(MessagesTchats);
                }).forEachOrdered((dto) -> {
                    listDto.add(dto);
                });
            }
        return listDto;
    }
    
    /**
     * Permet de ne pas avoir une instance différente par client.
     * ChatRoom est donc gérer en "singleton" et le configurateur utilise ce singleton. 
     */
    public static class EndpointConfigurator extends ServerEndpointConfig.Configurator {
        private @Inject VisiteurFacade visiteurFacade;
        private @Inject ArtisteFacade artisteFacade;
        @Override 
        @SuppressWarnings("unchecked")
        public <T> T getEndpointInstance(Class<T> endpointClass) {
            return  CDI.current().select(endpointClass).get(); 
        }
    }

    public class SignartFile {

        private String name;
        private byte [] content;
        public SignartFile() {
        }
        public String getName(){
            return this.name;
        }
        public void setName (String name){
            this.name = name;
        }
        public byte[] getContent(){
            return this.content;
        }
        public void setContent ( byte[] content){
            this.content = content;
        }
    }

   
}
