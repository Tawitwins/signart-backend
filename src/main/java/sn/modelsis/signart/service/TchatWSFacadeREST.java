package sn.modelsis.signart.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sn.modelsis.signart.Artiste;
import sn.modelsis.signart.MessagesTchats;
import sn.modelsis.signart.converter.MessagesTchatsConverter;
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
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(22222222);
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
            ,@javax.websocket.server.PathParam("idUser") String idUser,@javax.websocket.server.PathParam("profilUser") String profilUser) {
        
        session.getUserProperties().put( "username", username );
        session.getUserProperties().put( "idUser", idUser );
        session.getUserProperties().put( "profilUser", profilUser );
        sessions.put( session.getId(), session );
        
        messages = ConvertListEntityToDto(messagesTchatsFacade.findAll());
        
        sendMessageCon( "Admin >>> Data sent to the user " + username,session);
        sendMessageAll("Admin >>> Connection established for " + username);
        if(isAdmin==true)
        {
             messagesAdmin = ConvertListEntityToDto(messagesTchatsFacade.findAll());
             sendMessageData( "Admin >>> Data sent to the (admin) user" + username,session);
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
        String username = (String) session.getUserProperties().get( "username" );
        String fullMessage = username + " >>> " + messagePackage; 
        List<String> splittedMessage = Arrays.asList(messagePackage.split(Pattern.quote("|")));
        messageTmp = new MessagesTchatsDto();
        int headerCount=splittedMessage.get(0).length()+splittedMessage.get(1).length()+splittedMessage.get(2).length()+3;
        String message=messagePackage.substring(headerCount);
        messageTmp.setContenu(message);
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Timestamp dateEnvoi = new java.sql.Timestamp(currentDate.getTime());
        System.out.println( calendar+"/"+currentDate+"/"+dateEnvoi );
        messageTmp.setDateEnvoi(dateEnvoi);
        messageTmp.setUsername(username);
        messageTmp.setIdSender(Integer.parseInt((String)session.getUserProperties().get( "idUser" )));
        messageTmp.setIdReceiver(Integer.parseInt(splittedMessage.get(0)));
        messageTmp.setProfilSender((String)session.getUserProperties().get( "profilUser" ));
        messageTmp.setProfilReceiver(splittedMessage.get(1));
        sendMessageTmp( messageTmp);
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
    private void sendMessageTmp( MessagesTchatsDto messageTmp ) {
        // Affichage sur la console du server Web.
        System.out.println( messageTmp );  
        Session Reveiversession = sessions.values().stream()       
        //.filter(sess -> messageTmp.getIdReceiver().equals((int)sess.getUserProperties().get( "idUser" ))&& messageTmp.getProfilReceiver().equals(sess.getUserProperties().get( "profilUser" )))
        .filter(sess -> messageTmp.getUsername().equals((String)sess.getUserProperties().get( "username" ))&& messageTmp.getProfilReceiver().equals((String)sess.getUserProperties().get( "profilUser" )))
        .findAny()
        .orElse(null);
        System.out.println(Reveiversession );
        // On envoie le message au destinateur seulement.
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String messagesJson = ow.writeValueAsString(messageTmp);
            Reveiversession.getBasicRemote().sendObject(messagesJson );
        } catch( Exception exception ) {
            System.out.println( "ERROR: cannot send message to " + Reveiversession.getId() );
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
}
