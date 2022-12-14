package sn.modelsis.signart.converter;

import sn.modelsis.signart.Profil;
import sn.modelsis.signart.dto.ProfilDto;

import javax.ejb.Stateless;

@Stateless
public class ProfilConverter {

    public Profil dtoToEntity(ProfilDto dto){
        Profil entity = new Profil();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }

    public ProfilDto entityToDto(Profil entity){
        ProfilDto dto = new ProfilDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setLibelle(entity.getLibelle());
        return dto;
    }
}
