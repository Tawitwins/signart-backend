package sn.modelsis.signart.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.websocket.EncodeException;
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
import sn.modelsis.signart.Email;
import sn.modelsis.signart.MessagesTchats;
import sn.modelsis.signart.converter.EmailConverter;
import sn.modelsis.signart.converter.MessagesTchatsConverter;
import sn.modelsis.signart.dto.EmailDto;
import sn.modelsis.signart.dto.MessagesTchatsDto;
import sn.modelsis.signart.facade.ArtisteFacade;
import sn.modelsis.signart.facade.EmailFacade;
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
    //public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\tchat\\";
    public final static String PATH = "/signartFiles/tchat/";

    private @Inject MessagesTchatsFacade messagesTchatsFacade;
    private @Inject MessagesTchatsConverter messagesTchatsConverter;
    private @Inject EmailConverter emailConverter;
    private @Inject EmailFacade emailFacade;
    
    
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
    @PUT
    @Path(value = "/updateMsg")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public Response SendMail(MessagesTchatsDto message) {
        messagesTchatsFacade.edit(messagesTchatsConverter.dtoToEntity(message));
        return Response.status(Response.Status.CREATED).entity(message).build();
    }
    
     
    @POST
    @Path(value = "/sendMail")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response SendMail(EmailDto emailDto) {
      String to = emailDto.getTo();
      String host = "smtp.gmail.com";//or IP address
      //String PORT="587";
      String PORT="465";
      //String userSMTP = "SignArtEmail2020";
      //String passwordSMTP = "Sign@rtEm@il2020";
      String userSMTP = "SignArtEmail2020";
      String passwordSMTP = "hakjpquhthkzsyeh";
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
      Email entity = new Email();
      Calendar calendar = Calendar.getInstance();
      java.util.Date currentDate = calendar.getTime();
      //java.sql.Timestamp dateEnvoi = new java.sql.Timestamp(currentDate.getTime());
      emailDto.setDateEnvoi(currentDate);
      entity = emailConverter.dtoToEntity(emailDto);
      emailFacade.create(entity);
      return Response.status(Response.Status.CREATED).entity(entity).build();
    }
    
    
    
    @POST
    @Path(value = "/sendMailTest")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response SendMail2(EmailDto emailDto) {
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
      Email entity = new Email();
      Calendar calendar = Calendar.getInstance();
      java.util.Date currentDate = calendar.getTime();
      //java.sql.Timestamp dateEnvoi = new java.sql.Timestamp(currentDate.getTime());
      emailDto.setDateEnvoi(currentDate);
      entity = emailConverter.dtoToEntity(emailDto);
      emailFacade.create(entity);
      return Response.status(Response.Status.CREATED).entity(emailDto).build();
    }
     /**
     *
     * @param asyncResponse
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
        java.nio.file.Path filee = (java.nio.file.Path) Paths.get(PATH +signartFile.getName());
        Files.write(filee, data);
        return Response.status(Response.Status.CREATED).entity(signartFile).build();
    }
    @DELETE
    @Path("/filesRemove/{filename}")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean DeleteFile(@PathParam("filename") String filename) throws IOException {
        return Files.deleteIfExists((java.nio.file.Path) Paths.get(PATH + filename));
    }
    
    @GET
    @Path("/GetFiles/{filename}")
    @Produces(MediaType.TEXT_PLAIN)
    public String GetFile(@PathParam("filename") String filename) throws IOException {
        byte [] myFile = Files.readAllBytes((java.nio.file.Path) Paths.get(PATH + filename));
        String File = Base64.encodeBase64String(myFile);
        String vof = String.valueOf(myFile);
        String mimeType = Files.probeContentType((java.nio.file.Path) Paths.get(PATH + filename));
        return mimeType+";"+File;
    }
    /**
     * On maintient toutes les sessions utilisateurs dans une collection.
     */
    private final Hashtable<String, Session> sessions = new Hashtable<>();
    
    /**
     * Cette m??thode est d??clench??e ?? chaque connexion d'un utilisateur.
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
            session.getUserProperties().put( "idUser", idUser);
            session.getUserProperties().put( "profilUser", profilUser );
            if(profilUser.equals("VISITEUR"))
            {
                session.getUserProperties().put( "idUser", ByteBuffer.wrap(session.getId().getBytes()).getInt());
            }
            sessions.put( session.getId(), session );
        
            if(messages!=null)
                messages.clear();
            messages = ConvertListEntityToDto(messagesTchatsFacade.findAllMine(idUser));
            if(isAdmin==false)
            {
                sendMessageCon( "Admin >>> Data sent to the user " + username ,session);
                //sendMessageAll("Admin >>> Connection established for " + username);
            }
            else
            {
                //messages.addAll(ConvertListEntityToDto(messagesTchatsFacade.findNewMsg()));
                messages = ConvertListEntityToDto(messagesTchatsFacade.findAll());
                sendMessageCon( "Admin >>> Data sent to the user " + username,session);
                //sendMessageAll("Admin >>> Connection established for " + username);
                if(messagesAdmin!=null)
                    messagesAdmin.clear();
                messagesAdmin = ConvertListEntityToDto(messagesTchatsFacade.findAllForAdmin(idUser));
                sendMessageData( "Admin >>> Data sent to the (admin) user" + username,session);
            }
            }catch( Exception exception ) {
                System.out.println( "ERROR: on open " + exception );
                }   
        
    }

    /**
     * Cette m??thode est d??clench??e ?? chaque d??connexion d'un utilisateur.
     * @param session
     */
    @OnClose
    public void close(Session session) {
        String username = (String) session.getUserProperties().get( "username" );
        sessions.remove( session.getId() );
        //sendMessageAll( "Admin >>> Connection closed for " + username );
    }

    /**
     * Cette m??thode est d??clench??e en cas d'erreur de communication.
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println( "Error: " + error.getMessage() );
    }

    /**
     * Cette m??thode est d??clench??e ?? chaque r??ception d'un message utilisateur.
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
            if(Integer.parseInt(splittedMessage.get(0))==0)
                messageTmp.setIdReceiver(null);
            else
                 messageTmp.setIdReceiver(Integer.parseInt(splittedMessage.get(0)));
            messageTmp.setProfilSender((String)session.getUserProperties().get( "profilUser" ));
            messageTmp.setProfilReceiver(splittedMessage.get(1));
            messageTmp.setMsgStateSender("VALIDE");
            messageTmp.setMsgStateReceiver("VALIDE");
            sendMessageTmp( messageTmp, session);
            System.out.println( "profil receiver " + messageTmp.getProfilSender() );
            }catch( Exception exception ) {
                System.out.println( "ERROR: on message " + exception );
                } 
    }

    /**
     * Une m??thode priv??e, sp??cifique ?? notre exemple.
     * Elle permet l'envoie d'un message au participant qui vient juste de se connecter.
     */
    private void sendMessageCon( String fullMessage,Session sessionCon ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message ?? tout le monde.
       
            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String messagesJson = ow.writeValueAsString(messages);
                sessionCon.getBasicRemote().sendObject(messagesJson);
                sessionCon.getBasicRemote().sendText( fullMessage + " "+ (Integer)sessionCon.getUserProperties().get( "idUser" ) );
            } catch( Exception exception ) {
                System.out.println( "ERROR: cannot send message to " + sessionCon.getId() );
            }   
    }
    /**
     * Une m??thode priv??e, sp??cifique ?? notre exemple.
     * Elle permet l'envoie d'un message au participant qui vient juste de se connecter.
     */
    private void sendMessageData( String fullMessage,Session sessionCon ) {
        // Affichage sur la console du server Web.
        System.out.println( fullMessage );      
        
        // On envoie le message ?? tout le monde.
       
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
        
        // On envoie le message ?? tout le monde.
        sessions.values().forEach((session) -> {
            try {
                session.getBasicRemote().sendText( fullMessage );
            } catch( Exception exception ) {
                System.out.println( "ERROR: cannot send message to " + session.getId() );
            }
        });       
    }
    
    /**
     * Une m??thode priv??e, sp??cifique ?? notre exemple.
     * Elle permet l'envoie de donn??s ?? une session sp??cifique.
     */
    private void sendMessageTmp( MessagesTchatsDto messageTmp, Session session ) {
        // Affichage sur la console du server Web.
        System.out.println( messageTmp );  
        
         try {
             ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
             String messagesJson = ow.writeValueAsString(messageTmp);
             session.getBasicRemote().sendObject(messagesJson );
             if(messageTmp.getIdReceiver()==null)
             {
                 System.out.println( "Envoie des messages ?? tous les administrateurs");
                 Stream<Session> adminsSessions = sessions.values().stream()   
                .filter(sess ->"ADMIN".equals((String)sess.getUserProperties().get( "profilUser" )));
                adminsSessions.forEach((sess) -> {
                try {
                    sess.getBasicRemote().sendObject(messagesJson );
                     System.out.println( "Termin?? Envoie des messages ?? un administrateur");
                    } catch( IOException | EncodeException exception ) {
                    System.out.println( "ERROR: cannot send message to " + sess.getId()+"-->"+ exception );
                    }
                }); 
             }
             else if("VISITEUR".equals((String)session.getUserProperties().get( "profilUser" )))
             {
                 Stream<Session> Reveiversessions= sessions.values().stream()   
                .filter(sess -> messageTmp.getIdReceiver().equals(new String(ByteBuffer.allocate(4).putInt((Integer)sess.getUserProperties().get( "idUser" )).array(),StandardCharsets.UTF_8)));
                   //.filter(sess -> messageTmp.getUsername().equals((String)sess.getUserProperties().get( "username" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )))
                  System.out.println(Reveiversessions );
                // On envoie le message au destinateur seulement.
                try {

                    Reveiversessions.forEach((sess) -> {
                    try {
                        sess.getBasicRemote().sendObject(messagesJson );
                         System.out.println( "Termin?? Envoie des messages aux destinataires");
                        } catch( IOException | EncodeException exception ) {
                        System.out.println( "ERROR: cannot send message to " + sess.getId()+"-->"+ exception );
                        }
                    }); 
                    //Reveiversession.getBasicRemote().sendObject(messagesJson );
                   
                } catch( Exception exception ) {
                    System.out.println( "ERROR: cannot send message to the receivers sessions qui sont au nombre de: " + Reveiversessions.count() );
                }
             }
             else
             {
                Stream<Session> Reveiversessions = sessions.values().stream()   
                .filter(sess -> messageTmp.getIdReceiver().equals((Integer)sess.getUserProperties().get( "idUser" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )));
                   //.filter(sess -> messageTmp.getUsername().equals((String)sess.getUserProperties().get( "username" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )))
            
         
                System.out.println(Reveiversessions );
                // On envoie le message au destinateur seulement.
                try {
                    Reveiversessions.forEach((sess) -> {
                    try {
                        sess.getBasicRemote().sendObject(messagesJson );
                         System.out.println( "Termin?? Envoie des messages aux destinataires");
                        } catch( IOException | EncodeException exception ) {
                        System.out.println( "ERROR: cannot send message to " + sess.getId()+"-->"+ exception );
                        }
                    }); 
                    //Reveiversession.getBasicRemote().sendObject(messagesJson );
                } catch( Exception exception ) {
                    System.out.println( "ERROR: cannot send message to the receivers sessions qui sont au nombre de: " + Reveiversessions.count() );
                }
             }
        }catch(Exception exception){
            System.out.println( "ERROR: sessions is empty"+ exception);
        }
         finally{
             System.out.println(messageTmp);
             //messageTmp.setContenu(Base64.encodeBase64String(messageTmp.getContenu().getBytes()));
             if(messageTmp.getProfilSender().equals("VISITEUR") || messageTmp.getProfilReceiver().equals("VISITEUR"))
             {
                 // pas de sauvegarde dans le base de donn??es pour le moment.
             }
             else
                 messagesTchatsFacade.create(messagesTchatsConverter.dtoToEntity(messageTmp));
        }
//        // On envoie le message ?? tout le monde.
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
     * Permet de ne pas avoir une instance diff??rente par client.
     * ChatRoom est donc g??rer en "singleton" et le configurateur utilise ce singleton. 
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
