package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.LignePanier;
import sn.modelsis.signart.dto.LignePanierDto;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.PanierFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LignePanierConverter {

    @Inject
    PanierFacade panierFacade;
    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    OeuvreConverter oeuvreConverter;

    /**
     * Converts an lignePanier entity to DTO
     * @param entity
     * @return
     */
    public LignePanierDto entityToDto(LignePanier entity) {
        LignePanierDto dto = new LignePanierDto();
        dto.setId(entity.getId());
        dto.setOeuvre(oeuvreConverter.entityToDto(oeuvreFacade.find(entity.getIdOeuvre().getId())));
        dto.setPrix(entity.getPrix());
        dto.setQuantite(entity.getQuantite());
        dto.setTotal(entity.getPrix().multiply(BigDecimal.valueOf(entity.getQuantite())));
        return dto;
    }

    /**
     * 
     * @param dto
     * @return 
     */
    public LignePanier dtoToEntity(LignePanierDto dto) {
        LignePanier entity = new LignePanier();
        entity.setIdOeuvre(oeuvreFacade.find(dto.getOeuvre().getId()));
        entity.setIdPanier(panierFacade.findByIdClient(dto.getIdClient()));
        //entity.setIdEtatLignePanier(etatLigneanierFacade.findByLibelle("Nouveau"));
        entity.setPrix(dto.getPrix());
        entity.setQuantite(dto.getQuantite());
        return entity;
    }
}
