package sn.modelsis.signart.service;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sn.modelsis.signart.Client;
import sn.modelsis.signart.MarquageArtiste;
import sn.modelsis.signart.converter.ClientConverter;
import sn.modelsis.signart.dto.ClientDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.CommandeFacade;
import sn.modelsis.signart.facade.MarquageArtisteFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("client")
public class ClientFacadeREST {

    @Inject
    ClientFacade clientFacade;
    @Inject
    MarquageArtisteFacade marquageArtiste;
    @Inject
    ClientConverter clientConverter;
    @Inject
    CommandeFacade commandeFacade;
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ClientDto dto) {
        clientFacade.create(clientConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ClientDto dto) {
        clientFacade.edit(clientConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        clientFacade.remove(clientFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ClientDto find(@PathParam("id") Integer id) {
        return clientConverter.entityToDto(clientFacade.find(id));
    }

    @GET
    @Path("user/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public ClientDto findByUser(@PathParam("idUser") Integer idUser) throws SignArtException {
        if (idUser == null || idUser == 0) {
            throw new SignArtException("Utilisateur invalide!");
        }
        return clientConverter.entityToDto(clientFacade.findByUserAdvanced(idUser));
    }
    @GET
    @Path("commande/{idCommande}")
    @Produces({MediaType.APPLICATION_JSON})
    public ClientDto findByCommande(@PathParam("idCommande") Integer idCommande) throws SignArtException {
        if (idCommande == null || idCommande == 0) {
            throw new SignArtException("Commandes invalide!");
        }

        return clientConverter.entityToDto(commandeFacade.findById(idCommande).getIdClient());
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientDto> findAll() {
        List<ClientDto> listDto = new ArrayList<>();
        List<Client> client = clientFacade.findAll();
        if(client != null){
            client.stream().map(entity->clientConverter.entityToDto(entity))
                    .forEachOrdered(clientDto -> listDto.add(clientDto));
        }
        return listDto;
    }

    @GET
    @Path("artiste/{idArtiste}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientDto> findAll(@PathParam("idArtiste") Integer idArtiste) {
        try {
            //return clientFacade.findByArtiste(idArtiste);
            List<ClientDto> listDto = new ArrayList<>();
            List<Client> listEnt = clientFacade.findByArtiste(idArtiste);
            if (listEnt != null) {
                listEnt.stream().map(entity
                        -> clientConverter.entityToDto(entity)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (SignArtException ex) {
            Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Client> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return clientFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(clientFacade.count());
    }

}
