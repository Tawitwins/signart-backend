package sn.modelsis.signart.service;

import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.CoefficientParametrage;
import sn.modelsis.signart.converter.CoefficientParametrageConverter;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.dto.CoefficientParametrageDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.CoefficientParametrageFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
@Path("coefficientParametrage")
public class CoefficientParametrageREST {

    @Inject
    CoefficientParametrageFacade coefficientParametrageFacade;
    @Inject
    CoefficientParametrageConverter coefficientParametrageConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(CoefficientParametrageDto dto) throws SignArtException {
        coefficientParametrageFacade.create(coefficientParametrageConverter.dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(CoefficientParametrageDto dto) throws SignArtException {
        CoefficientParametrage entity = coefficientParametrageConverter.dtoToEntity(dto);
        entity = coefficientParametrageFacade.save(entity);
        CoefficientParametrageDto coefficientParametrageDto = coefficientParametrageConverter.entityToDto(entity);
        return Response.status(Response.Status.OK).entity(coefficientParametrageDto).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<CoefficientParametrageDto> findAll(){
        List<CoefficientParametrageDto> dtoList = new ArrayList<>();
        List<CoefficientParametrage> entityList = coefficientParametrageFacade.findAll();
        if (entityList != null) {
            entityList.stream().map(entity
                    -> coefficientParametrageConverter.entityToDto(entity)
            ).forEachOrdered(dto
                    -> dtoList.add(dto)
            );
        }
        Collections.reverse(dtoList);
        return dtoList;
    }

    @GET
    @Path("findById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CoefficientParametrageDto findById(@PathParam("id") Integer id) throws SignArtException {
        CoefficientParametrage entity = coefficientParametrageFacade.findById(id);
        return coefficientParametrageConverter.entityToDto(entity);
    }

    @GET
    @Path("findByCodeParametre/{codeParametre}")
    @Produces({MediaType.APPLICATION_JSON})
    public CoefficientParametrageDto findByCodeParametre(@PathParam("codeParametre") String codeParametre) throws SignArtException {
        CoefficientParametrage entity = coefficientParametrageFacade.findByCodeParametre(codeParametre);
        return coefficientParametrageConverter.entityToDto(entity);
    }

    @GET
    @Path("findByValeurParametre/{valeurParametre}")
    @Produces({MediaType.APPLICATION_JSON})
    public CoefficientParametrageDto findByValeurParametre(@PathParam("valeurParametre") String valeurParametre) throws SignArtException {
        CoefficientParametrage entity = coefficientParametrageFacade.findByValeurParametre(valeurParametre);
        return coefficientParametrageConverter.entityToDto(entity);
    }

    @GET
    @Path("findByEnumTypeParam/{enumTypeParam}")
    @Produces({MediaType.APPLICATION_JSON})
    public CoefficientParametrageDto findByEnumTypeParam(@PathParam("enumTypeParam") String enumTypeParam) throws SignArtException {
        CoefficientParametrage entity = coefficientParametrageFacade.findByEnumTypeParam(enumTypeParam);
        return coefficientParametrageConverter.entityToDto(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void  delete(@PathParam("id") Integer id){
        coefficientParametrageFacade.remove(coefficientParametrageFacade.find(id));
    }

    @GET
    @Path("helloWorld")
    @Produces({MediaType.APPLICATION_JSON})
    public String hello(){
        return "Hello World";
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CoefficientParametrageDto find(@PathParam("id") Integer id) {
        CoefficientParametrage coefficientParametrage = coefficientParametrageFacade.find(id);
        return coefficientParametrageConverter.entityToDto(coefficientParametrage);
    }
}
