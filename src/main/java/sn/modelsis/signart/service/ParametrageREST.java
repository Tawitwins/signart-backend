package sn.modelsis.signart.service;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.dto.ParametrageDto;
import sn.modelsis.signart.facade.ParametrageFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("parametrage")
public class ParametrageREST {
    @Inject
    ParametrageFacade parametrageFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ParametrageDto parametrageDto){
        Parametrage parametrage = dtoToEntity(parametrageDto);
        parametrageFacade.create(parametrage);
        ParametrageDto dtoRes = entityDto(parametrage);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }

    public Parametrage dtoToEntity(ParametrageDto dto){
        Parametrage parametrage = new Parametrage();
        parametrage.setStatut(dto.getStatut());
        parametrage.setParamName(dto.getParamName());
        parametrage.setValue(dto.getValue());
        return parametrage;
    }

    public ParametrageDto entityDto(Parametrage parametrage){
        ParametrageDto parametrageDto = new ParametrageDto();

        parametrageDto.setParamName(parametrage.getParamName());
        parametrageDto.setStatut(parametrage.getStatut());
        parametrageDto.setValue(parametrage.getValue());
        return parametrageDto;
    }


    @PUT
    @Path("{paramName}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("paramName") String paramName, ParametrageDto parametrageDto){
        Parametrage parametrage = parametrageFacade.findByParamName(paramName);
        parametrageFacade.edit(dtoToEtityParamNam(parametrageDto, parametrage));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{paramName}")
    @Produces({MediaType.APPLICATION_JSON})
    public ParametrageDto find(@PathParam("paramName") String paramName) {
        Parametrage parametrage = parametrageFacade.find(paramName);
        return entityDto(parametrage);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ParametrageDto> findAll() {
        List<ParametrageDto> parametrageDtoList = new ArrayList<>();
        List<Parametrage> parametrageList = parametrageFacade.findAll();

        if(parametrageList != null){
            parametrageList.stream().map(parametrage ->  entityDto(parametrage)).forEachOrdered(dto ->
                parametrageDtoList.add(dto));
        }
        return parametrageDtoList;
    }

    public Parametrage dtoToEtityParamNam(ParametrageDto parametrageDto, Parametrage parametrage){
        parametrage.setValue(parametrageDto.getValue());
        parametrage.setParamName(parametrageDto.getParamName());
        parametrage.setStatut(parametrageDto.getStatut());
        return parametrage;
    }
}
