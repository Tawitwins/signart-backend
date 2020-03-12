package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Pays;
import sn.modelsis.signart.dto.PaysDto;
import sn.modelsis.signart.facade.MenuFacade;
/**
 * @author SNNGOMN
 */
@Stateless
public class PaysConverter {

    @Inject
    MenuFacade menuFacade;

    public PaysDto toDto(Pays entity) {
        PaysDto dto = new PaysDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setLibelle(entity.getLibelle());
        return dto;
    }

    public Pays toEntity(PaysDto dto){
        Pays entity = new Pays();

        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }
}