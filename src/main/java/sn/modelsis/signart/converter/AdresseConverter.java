package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Adresse;
import sn.modelsis.signart.dto.AdresseDto;
import sn.modelsis.signart.facade.ClientFacade;
import sn.modelsis.signart.facade.PaysFacade;
import sn.modelsis.signart.facade.TypeAdresseFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class AdresseConverter {
    
    @Inject
    TypeAdresseFacade typeAdresseFacade;
    @Inject
    PaysFacade paysFacade;
    @Inject
    ClientFacade clientFacade;
    
    /**
     * Converts entity to Dto
     * @param entity
     * @return
     */
    public AdresseDto entityToDto(Adresse entity) {
        AdresseDto dto = new AdresseDto();
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setAdresse(entity.getAdresse());
        dto.setId(entity.getId());
        dto.setIdClient(entity.getIdClient().getId());
        dto.setIdPays(entity.getIdPays().getId());
        dto.setLibellePays(entity.getIdPays().getLibelle());
        dto.setIdTypeAdresse(entity.getIdTypeAdresse().getId());
        dto.setCodeTypeAdresse(entity.getIdTypeAdresse().getCode());
        dto.setLibelleTypeAdresse(entity.getIdTypeAdresse().getLibelle());
        dto.setVille(entity.getVille());
        dto.setRegion(entity.getRegion());
        dto.setIndicatif(entity.getIndicatif());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }
    
    /**
     * 
     * @param dto
     * @return 
     */
    public Adresse dtoToEntity(AdresseDto dto) {
        Adresse entity = new Adresse();
        entity.setId(dto.getId());
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        //entity.setIdTypeAdresse(typeAdresseFacade.find(dto.getIdTypeAdresse()));
        entity.setIdTypeAdresse(typeAdresseFacade.findByCode(dto.getCodeTypeAdresse()));
        entity.setIdPays(paysFacade.find(dto.getIdPays()));
        entity.setIdClient(clientFacade.find(dto.getIdClient()));
        entity.setAdresse(dto.getAdresse());
        entity.setVille(dto.getVille());
        entity.setRegion(dto.getRegion());
        entity.setIndicatif(dto.getIndicatif());
        entity.setTelephone(dto.getTelephone());
        return entity;
    }
}
