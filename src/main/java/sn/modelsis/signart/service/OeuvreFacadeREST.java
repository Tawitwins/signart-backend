package sn.modelsis.signart.service;

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
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.Oeuvre;
import sn.modelsis.signart.converter.OeuvreConverter;
import sn.modelsis.signart.dto.OeuvreDto;
import sn.modelsis.signart.facade.OeuvreFacade;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;
import sn.modelsis.signart.facade.OeuvreSouscriptionFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
@Path("oeuvre")
public class OeuvreFacadeREST {

    @Inject
    OeuvreFacade oeuvreFacade;
    
    @Inject
    OeuvreSouscriptionFacade oeuvreSouscriptionFacade;
    @Inject
    OeuvreConverter oeuvreConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(OeuvreDto dto) throws SignArtException {
        Oeuvre entity = oeuvreConverter.dtoToEntity(dto);
        oeuvreFacade.create(entity);
        OeuvreDto dtoRes = oeuvreConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }
    
    @POST
    @Path("oeuvresous/{idMagasin}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response valider(OeuvreSouscriptionDto dto,@PathParam("idMagasin") Integer idMagasin) throws SignArtException {
        OeuvreSouscription enti = oeuvreSouscriptionFacade.findById(dto.getId());
        
        Oeuvre entity = new Oeuvre();
        
        
        entity = oeuvreConverter.convertOueuvreSouscription(enti,idMagasin);
        oeuvreFacade.create(entity);
       // OeuvreDto dtoRes = oeuvreConverter.entityToDto(entity);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, OeuvreDto dto) throws SignArtException {
        Oeuvre oeuvre = oeuvreFacade.find(id);
        oeuvre.setTaxes(dto.getTaxes());
        oeuvre.setTauxremise(dto.getTauxremise());
        oeuvre.setPrix(dto.getPrix());
        oeuvre.setStock(dto.getStock());
        oeuvre.setDescription(dto.getDescription());
        oeuvre.setSpecialDelivery(dto.getSpecialDelivery());
        oeuvreFacade.edit(oeuvre);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        oeuvreFacade.remove(oeuvreFacade.find(id));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public OeuvreDto find(@PathParam("id") Integer id) {
        Oeuvre oeuvre = oeuvreFacade.find(id);
        return oeuvreConverter.entityToDto(oeuvre);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findAll() {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findAll();
        if (listEnt != null) {
            listEnt.stream().map(entity -> 
                oeuvreConverter.entityToDto(entity)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }

    @GET
    @Path("technique/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findByTechnique(@PathParam("id") Integer idTechnique) {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findByTechnique(idTechnique);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }
    @GET
    @Path("technique/by-theme/{idTechnique}/{idTheme}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findByTechniqueAndTheme(@PathParam("idTechnique") Integer idTechnique, @PathParam("idTheme") Integer idTheme) {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findByTechniqueAndTheme(idTechnique, idTheme);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }

    /*@GET
    @Path("technique/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findBySousTechnique(@PathParam("id") Integer idTechnique) {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findByTechnique(idTechnique);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }*/

    @GET
    @Path("artiste/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findByArtiste(@PathParam("id") Integer idArtiste) {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findByArtiste(idArtiste);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }
    @GET
    @Path("artiste/CouvertureImg/{id}")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response findCouvertureImgByArtiste(@PathParam("id") Integer idArtiste) {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findByArtiste(idArtiste);
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        if(listDto.size()>0)
        {
            final Response.ResponseBuilder response = Response.ok(listDto.get(0).getImage());
            response.header("Content-Disposition", "attachment;filename=" + "image.jpg");
            return response.build();
        }
        else
            return null;
    }

    @GET
    @Path("nouveau")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findNouveau() {
        List<OeuvreDto> listDto = new ArrayList<>();
        List<Oeuvre> listEnt = oeuvreFacade.findNouveau();
        if (listEnt != null) {
            listEnt.stream().map(oeuvre -> 
                oeuvreConverter.entityToDto(oeuvre)
            ).forEachOrdered(dto -> 
                listDto.add(dto)
            );
        }
        return listDto;
    }

    @GET
    @Path("marque/{codeTypeMarquage}/{idClient}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OeuvreDto> findMarqueByClient(@PathParam("codeTypeMarquage") String codeTypeMarquage, @PathParam("idClient") Integer idClient) {
        List<OeuvreDto> listDto = new ArrayList<>();
        try {
            List<Oeuvre> listEnt = oeuvreFacade.findMarqueByClient(codeTypeMarquage, idClient);
            if (listEnt != null) {
                listEnt.stream().map(oeuvre
                        -> oeuvreConverter.entityToDto(oeuvre)
                ).forEachOrdered(dto
                        -> listDto.add(dto)
                );
            }
            return listDto;
        } catch (SignArtException e) {
            Logger.getLogger(OeuvreFacadeREST.class.getName()).log(Level.SEVERE, "findMarqueByClient/SignArtException", e);
            return listDto;
        } catch (final Exception e) {
            Logger.getLogger(OeuvreFacadeREST.class.getName()).log(Level.SEVERE, "findMarqueByClient/Exception", e);
            return listDto;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Oeuvre> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return oeuvreFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(oeuvreFacade.count());
    }

}
