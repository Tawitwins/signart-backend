package sn.modelsis.signart.converter;

import sn.modelsis.signart.CoefficientParametrage;
import sn.modelsis.signart.EnumTypeParam;
import sn.modelsis.signart.dto.CoefficientParametrageDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.CoefficientParametrageFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CoefficientParametrageConverter {

    @Inject
    CoefficientParametrageFacade coefficientParametrageFacade;

    public CoefficientParametrageDto entityToDto(CoefficientParametrage entity){
        CoefficientParametrageDto dto = new CoefficientParametrageDto();
        dto.setId(entity.getId());
        dto.setCodeParametre(entity.getCodeParametre());
        dto.setValeurParametre(entity.getValeurParametre());
        dto.setStatut(entity.getStatut());
        dto.setEnumTypeParam(entity.getEnumTypeParam().toString());
        return dto;
    }

    public CoefficientParametrage dtoToEntity(CoefficientParametrageDto dto) throws SignArtException {

        CoefficientParametrage entity = new CoefficientParametrage();

        entity.setId(dto.getId());
        entity.setCodeParametre(dto.getCodeParametre());
        entity.setValeurParametre(dto.getValeurParametre());
        entity.setStatut(dto.getStatut());
        entity.setEnumTypeParam(getEnumParam(dto.getEnumTypeParam()));
        return entity;
    }
    public EnumTypeParam getEnumParam(String typeParam){

        EnumTypeParam param = null;

        switch (typeParam){
            case "ABONNEMENT":
                param = param.ABONNEMENT;
                break;
            case "LOCATION_TERMINAL":
                param = param.LOCATION_TERMINAL;
                break;
            case "LIVRAISON":
                param = param.LIVRAISON;
                break;
            case "ACHAT":
                param = param.ACHAT;
                break;
            default:
                break;
        }
        return param;
    }
}
