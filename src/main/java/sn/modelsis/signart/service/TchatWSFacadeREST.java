package sn.modelsis.signart.service;

import TchatPackage.TchatWS;
import java.util.Hashtable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Visiteur;
import sn.modelsis.signart.converter.VisiteurConverter;
import sn.modelsis.signart.dto.VisiteurDto;
import sn.modelsis.signart.facade.VisiteurFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("admin")
@ServerEndpoint(value="/admin/Ws/{pseudo}", 
                configurator=TchatWSFacadeREST.EndpointConfigurator.class)

public class TchatWSFacadeREST {

    private static TchatWSFacadeREST singleton = new TchatWSFacadeREST();

    public TchatWSFacadeREST() {
    }

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
     * @param pseudo
     */
    @OnOpen
    public void open(Session session, @javax.websocket.server.PathParam("pseudo") String pseudo ) {
        
        session.getUserProperties().put( "pseudo", pseudo );
        sessions.put( session.getId(), session );
        sendMessage( "Admin >>> Connection established for " + pseudo );
    }

    /**
     * Cette méthode est déclenchée à chaque déconnexion d'un utilisateur.
     * @param session
     */
    @OnClose
    public void close(Session session) {
        String pseudo = (String) session.getUserProperties().get( "pseudo" );
        sessions.remove( session.getId() );
        sendMessage( "Admin >>> Connection closed for " + pseudo );
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
     * @param message
     * @param session
     */
    @OnMessage
    public void handleMessage(String message, Session session) {
        String pseudo = (String) session.getUserProperties().get( "pseudo" );
        String fullMessage = pseudo + " >>> " + message; 
        
        sendMessage( fullMessage );
    }

    /**
     * Une méthode privée, spécifique à notre exemple.
     * Elle permet l'envoie d'un message aux participants de la discussion.
     */
    private void sendMessage( String fullMessage ) {
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
     * Permet de ne pas avoir une instance différente par client.
     * ChatRoom est donc gérer en "singleton" et le configurateur utilise ce singleton. 
     */
    public static class EndpointConfigurator extends ServerEndpointConfig.Configurator {
        @Override 
        @SuppressWarnings("unchecked")
        public <T> T getEndpointInstance(Class<T> endpointClass) {
            return (T) TchatWSFacadeREST.getInstance();
        }
    }
}
