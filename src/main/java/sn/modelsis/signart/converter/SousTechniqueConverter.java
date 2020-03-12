package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;

//import sn.modelsis.signart.SousTechnique;
import sn.modelsis.signart.Technique;
//import sn.modelsis.signart.dto.SousTechniqueDto;
import sn.modelsis.signart.dto.TechniqueDto;
import sn.modelsis.signart.facade.MenuFacade;
import sn.modelsis.signart.facade.TechniqueFacade;
/**
 * @author SNNGOMN

@Stateless
public class SousTechniqueConverter {

    @Inject
    TechniqueFacade techniqueFacade;

    public SousTechniqueDto toDto(SousTechnique entity) {
        SousTechniqueDto dto = new SousTechniqueDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setIdTechnique(entity.getIdTechnique()!=null ? entity.getIdTechnique().getId() : null);
        return dto;
    }

    public SousTechnique toEntity(SousTechniqueDto dto){
        SousTechnique entity = new SousTechnique();

        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setIdTechnique(techniqueFacade.find(dto.getIdTechnique()));
        return entity;
    }
} */