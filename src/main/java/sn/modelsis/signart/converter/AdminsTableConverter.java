package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import sn.modelsis.signart.AdminsTable;
import sn.modelsis.signart.dto.AdminsTableDto;

/**
 *
 * @author SNLOM
 */
@Stateless
public class AdminsTableConverter {

    public AdminsTableDto entityToDto(AdminsTable entity) {
        AdminsTableDto dto = new AdminsTableDto();
        dto.setId(entity.getId());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());       
        dto.setTelephone(entity.getTelephone());
        dto.setEmail(entity.getEmail());
        dto.setAdresse(entity.getAdresse());
        return dto;
    }

    public AdminsTable dtoToEntity(AdminsTableDto dto) {
        AdminsTable entity = new AdminsTable();
        entity.setId(dto.getId());
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        entity.setTelephone(dto.getTelephone());
        entity.setEmail(dto.getEmail());
        entity.setAdresse(dto.getAdresse());
        return entity;
    }
}
