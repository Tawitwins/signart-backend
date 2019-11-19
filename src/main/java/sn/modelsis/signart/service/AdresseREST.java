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
import sn.modelsis.signart.Adresse;
import sn.modelsis.signart.converter.AdresseConverter;
import sn.modelsis.signart.dto.AdresseDto;
import sn.modelsis.signart.dto.AdressesDto;
import sn.modelsis.signart.facade.AdresseFacade;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.PaysFacade;
import sn.modelsis.signart.facade.TypeAdresseFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("adresse")
public class AdresseREST {

    @Inject
    AdresseFacade adresseFacade;
    @Inject
    TypeAdresseFacade typeAdresseFacade;
    @Inject
    PaysFacade paysFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    AdresseConverter adresseConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AdresseDto dto) {
        adresseFacade.create(adresseConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @POST
    @Path("adresses")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(AdressesDto dto) {

        Adresse adresseFacturation = adresseFacade.add(adresseConverter.dtoToEntity(dto.getAdresseFacturation()));
        Adresse adresseLivraison = adresseFacade.add(adresseConverter.dtoToEntity(dto.getAdresseLivraison()));
        AdressesDto result = new AdressesDto();
        result.setAdresseFacturation(adresseConverter.entityToDto(adresseFacturation));
        result.setAdresseLivraison(adresseConverter.entityToDto(adresseLivraison));

        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, AdresseDto dto) {
        adresseFacade.edit(adresseConverter.dtoToEntity(dto));
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        adresseFacade.remove(adresseFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AdresseDto find(@PathParam("id") Integer id) {
        Adresse adresse = adresseFacade.find(id);
        return adresseConverter.entityToDto(adresse);
    }

    @GET
    @Path("client/{idClient}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AdresseDto> findAll(@PathParam("idClient") Integer idClient) {
        List<AdresseDto> listDto = new ArrayList<>();
        List<Adresse> listEnt = adresseFacade.findByClient(idClient);
        if (listEnt != null) {
            listEnt.stream().map((entity) -> {
                return adresseConverter.entityToDto(entity);
            }).forEachOrdered((dto) -> {
                listDto.add(dto);
            });
        }
        return listDto;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(adresseFacade.count());
    }
    
}
