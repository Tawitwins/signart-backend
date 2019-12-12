package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.dto.OeuvreSouscriptionDto;

/**
 *
 * @author SNNGOM
 */
@Stateless
public class OeuvreSouscriptionConverter {


    public OeuvreSouscriptionDto entityToDto(OeuvreSouscription entity) {
        OeuvreSouscriptionDto dto = new OeuvreSouscriptionDto();
        ;
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        //dto.setImage(entity.getImage());
        dto.setNom(entity.getNom());
        dto.setPrix(entity.getPrix());
        return dto;
    }

    public OeuvreSouscription dtoToEntity(OeuvreSouscriptionDto dto) {
        OeuvreSouscription entity = new OeuvreSouscription();
        
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setNom(dto.getNom());
        entity.setPrix(dto.getPrix());
        return entity;
    }
}
