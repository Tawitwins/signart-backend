package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.facade.ModePaiementFacade;
import sn.modelsis.signart.facade.PaiementFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LignePaiementConverter {

    @Inject
    PaiementFacade paiementFacade;
    @Inject
    ModePaiementFacade modePaiementFacade;

    /**
     * Converts an lignePaiement entity to DTO
     * @param entity
     * @return
     */
    public LignePaiementDto entityToDto(LignePaiement entity) {
        LignePaiementDto dto = new LignePaiementDto();
        dto.setId(entity.getId());
        dto.setMontant(entity.getMontant());
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setIdModePaiement(entity.getIdModePaiement().getId());
        dto.setCodeModePaiement(entity.getIdModePaiement().getCode());
        dto.setLibelleModePaiement(entity.getIdModePaiement().getLibelle());
        dto.setIdPaiement(entity.getIdPaiement().getId());
        return dto;
    }

    /**
     * 
     * @param dto
     * @return 
     */
    public LignePaiement dtoToEntity(LignePaiementDto dto) {
        LignePaiement entity = new LignePaiement();
        entity.setId(dto.getId());
        entity.setIdModePaiement(modePaiementFacade.find(dto.getCodeModePaiement()));
        entity.setDatePaiement(dto.getDatePaiement());
        entity.setMontant(dto.getMontant());
        entity.setIdPaiement(paiementFacade.find(dto.getIdPaiement()));
        return entity;
    }
}
