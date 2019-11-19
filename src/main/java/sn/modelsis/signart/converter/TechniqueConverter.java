package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sn.modelsis.signart.Technique;
import sn.modelsis.signart.dto.TechniqueDto;
import sn.modelsis.signart.facade.MenuFacade;
/**
 * @author SNNGOMN
 */
@Stateless
public class TechniqueConverter {

    @Inject
    MenuFacade menuFacade;

    public TechniqueDto toDto(Technique entity) {
        TechniqueDto dto = new TechniqueDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setIdMenu(entity.getMenu()!=null ? entity.getMenu().getId() : null);
        return dto;
    }

    public Technique toEntity(TechniqueDto dto){
        Technique entity = new Technique();

        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setMenu(menuFacade.find(dto.getIdMenu()));
        return entity;
    }
}