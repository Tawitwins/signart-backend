package sn.modelsis.signart.service;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.ParametreAlgo;
import sn.modelsis.signart.converter.ParametreAlgoConverteur;
import sn.modelsis.signart.dto.ParametrageDto;
import sn.modelsis.signart.dto.ParametreAlgoDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.ParametrageFacade;
import sn.modelsis.signart.facade.ParametreAlgoFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("parametreAlgo")
public class ParametreAlgoREST {
    @Inject
    ParametreAlgoFacade parametreAlgoFacade;
    @Inject
    ParametreAlgoConverteur parametreAlgoConverteur;
    @Inject
    ParametrageFacade parametrageFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(ParametreAlgoDto parametreAlgoDto) throws SignArtException {
        ParametreAlgo parametreAlgo = parametreAlgoConverteur.dtoToEntity(parametreAlgoDto);
        parametreAlgoFacade.create(parametreAlgo);
        ParametreAlgoDto dtoRes = parametreAlgoConverteur.entityToDto(parametreAlgo);
        return Response.status(Response.Status.CREATED).entity(dtoRes).build();
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ParametreAlgoDto parametreAlgoDto) throws SignArtException {
        ParametreAlgo parametreAlgo = parametreAlgoFacade.find(id);
        parametreAlgoFacade.save(dtoToEtityEdition(parametreAlgoDto,parametreAlgo));
        return Response.status(Response.Status.OK).build();
    }
    public ParametreAlgo dtoToEtityEdition(ParametreAlgoDto parametreAlgoDto, ParametreAlgo parametreAlgo){
        parametreAlgo.setLibelle(parametreAlgoDto.getLibelle());
        parametreAlgo.setNiveau(parametreAlgoDto.getNiveau());
        parametreAlgo.setNote(parametreAlgoDto.getNote());
        parametreAlgo.setBaseNote(parametreAlgoDto.getBaseNote());
        parametreAlgo.setPourcentReduction(parametreAlgoDto.getPourcentReduction());
        if(parametreAlgoDto.getBorneInf() != 0)
            parametreAlgo.setBorneInf(parametreAlgoDto.getBorneInf());
        if(parametreAlgoDto.getBorneSup() != 0)
            parametreAlgo.setBorneSup(parametreAlgoDto.getBorneSup());
        return parametreAlgo;
    }
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean  delete(@PathParam("id") Integer id){
        ParametreAlgo entity = parametreAlgoFacade.find(id);
        parametreAlgoFacade.remove(entity);
        return true;
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ParametreAlgoDto find(@PathParam("id") Integer id) throws SignArtException {
        ParametreAlgo parametreAlgo = parametreAlgoFacade.find(id);
        return parametreAlgoConverteur.entityToDto(parametreAlgo);
    }

    @GET
    @Path("byCodeParamCoef/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ParametreAlgoDto> findByCode(@PathParam("code") String code) {
        List<ParametreAlgoDto> parametreAlgoDtoList = new ArrayList<>();
        List<ParametreAlgo> parametreAlgoList = parametreAlgoFacade.findByCode(code);

        if(parametreAlgoList != null){
            parametreAlgoList.stream().map(parametrage -> {
                try {
                    return parametreAlgoConverteur.entityToDto(parametrage);
                } catch (SignArtException e) {
                    throw new RuntimeException(e);
                }
            }).forEachOrdered(dto ->
                    parametreAlgoDtoList.add(dto));
        }
        return parametreAlgoDtoList;
    }@GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ParametreAlgoDto> findAll() {
        List<ParametreAlgoDto> parametreAlgoDtoList = new ArrayList<>();
        List<ParametreAlgo> parametreAlgoList = parametreAlgoFacade.findAll();

        if(parametreAlgoList != null){
            parametreAlgoList.stream().map(parametrage -> {
                try {
                    return parametreAlgoConverteur.entityToDto(parametrage);
                } catch (SignArtException e) {
                    throw new RuntimeException(e);
                }
            }).forEachOrdered(dto ->
                    parametreAlgoDtoList.add(dto));
        }
        return parametreAlgoDtoList;
    }


    @POST
    @Path("prixOeuvreByListParam")
    @Produces({MediaType.APPLICATION_JSON})
    public float getPrix(List<ParametreAlgo> listParams) throws SignArtException {
        Parametrage parametrage = parametrageFacade.findByParamName("prixBaseOeuvreL");
        Float prixBase = Float.parseFloat(parametrage.getValue());

        //ParametreAlgo parametreAlgo = parametreAlgoFacade.find(id);
        float totalCoef = 0;
        float totalProduit = 0;
        for (ParametreAlgo parametreAlgo : listParams) {
            totalProduit += parametreAlgo.getNote() * parametreAlgo.getCoefficientParam().getValeurParametre() * parametreAlgo.getPourcentReduction();
            totalCoef += parametreAlgo.getCoefficientParam().getValeurParametre();
        }
        totalProduit = totalProduit / listParams.get(0).getBaseNote();
        totalProduit = totalProduit * prixBase / totalCoef;
        return totalProduit;
    }
    @POST
    @Path("prixMoyenneByListParam")
    @Produces({MediaType.APPLICATION_JSON})
    public float getPrixMoyen(List<List<ParametreAlgo>> listParams) throws SignArtException {
        Parametrage parametrage = parametrageFacade.findByParamName("prixBaseOeuvreL");
        Float prixBase = Float.parseFloat(parametrage.getValue());

        //ParametreAlgo parametreAlgo = parametreAlgoFacade.find(id);

        float totalCoef = 0;
        float totalProduit = 0;
        float prixMoyenne = 0;
        for (List<ParametreAlgo> parametreAlgoList : listParams) {
            totalCoef = 0;
            totalProduit = 0;
            for (ParametreAlgo parametreAlgo : parametreAlgoList) {
                totalProduit += parametreAlgo.getNote() * parametreAlgo.getCoefficientParam().getValeurParametre() * parametreAlgo.getPourcentReduction();
                totalCoef += parametreAlgo.getCoefficientParam().getValeurParametre();
            }
            totalProduit = totalProduit / parametreAlgoList.get(0).getBaseNote();
            totalProduit = totalProduit * prixBase / totalCoef;
            prixMoyenne += totalProduit;
        }
        return prixMoyenne/listParams.size();
    }
}
