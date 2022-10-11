package sn.modelsis.signart.converter;

import sn.modelsis.signart.Parametrage;
import sn.modelsis.signart.ParametreAlgo;
import sn.modelsis.signart.dto.ParametrageDto;
import sn.modelsis.signart.dto.ParametreAlgoDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.UtilisateurFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author SNMBENGUEO
 */
@Stateless
public class ParametreAlgoConverteur {

    @Inject
    UtilisateurFacade utilisateurFacade;
    public ParametreAlgoDto entityToDto(ParametreAlgo entity) throws SignArtException {
        ParametreAlgoDto dto = new ParametreAlgoDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setNiveau(entity.getNiveau());
        dto.setNote(entity.getNote());
        dto.setBaseNote(entity.getBaseNote());
        dto.setIdCoefficientParametrage(entity.getCoefficientParam().getId());
        dto.setPourcentReduction(entity.getPourcentReduction());
        return dto;
    }

    public ParametreAlgo dtoToEntity(ParametreAlgoDto dto)  throws SignArtException {
        ParametreAlgo entity = new ParametreAlgo();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setNiveau(dto.getNiveau());
        entity.setNote(dto.getNote());
        entity.setBaseNote(dto.getBaseNote());
        entity.setPourcentReduction(dto.getPourcentReduction());
        return entity;
    }
}
