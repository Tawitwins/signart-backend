package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.facade.EtatPaiementFacade;
import sn.modelsis.signart.facade.LigneCommandeFacade;
import sn.modelsis.signart.facade.ModePaiementFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PaiementConverter {

    @Inject
    EtatPaiementFacade etatPaiementFacade;
    @Inject
    ModePaiementFacade modePaiementFacade;
    @Inject
    LignePaiementConverter lignePaiementConverter;

    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    public PaiementDto entityToDto(Paiement entity) {
        PaiementDto dto = new PaiementDto();
        BigDecimal montantTotal = null;
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setId(entity.getId());
        dto.setIdEtatPaiement(entity.getIdEtatPaiement().getId());
        dto.setCodeEtatPaiement(entity.getIdEtatPaiement().getCode());
        dto.setLibelleEtatPaiement(entity.getIdEtatPaiement().getLibelle());
        dto.setIdModePaiement(entity.getIdModePaiement().getId());
        dto.setCodeModePaiement(entity.getIdModePaiement().getCode());
        dto.setLibelleModePaiement(entity.getIdModePaiement().getLibelle());
        Set<LignePaiement> lignePaiementSet = entity.getLignePaiementSet();
        if (lignePaiementSet != null && !lignePaiementSet.isEmpty()) {
            Set<LignePaiementDto> lignePaiementDtoSet = new HashSet<>();
            LignePaiementDto lignePaiementDto;
            for (LignePaiement lignePaiement : lignePaiementSet) {
                lignePaiementDto = lignePaiementConverter.entityToDto(lignePaiement);
                lignePaiementDtoSet.add(lignePaiementDto);
                //dto.setMontantTotal(lignePaiement.getMontant());

            }
            dto.setLignePaiements(lignePaiementDtoSet);
            dto.setStringPaymentDate(DateFor.format(entity.getDatePaiement()));
            //dto.getStringCreationDate(DateFor.format(entity.get));
        }
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public Paiement dtoToEntity(PaiementDto dto) {
        Paiement entity = new Paiement();
        entity.setId(dto.getId());
        entity.setIdEtatPaiement(etatPaiementFacade.findByCode(dto.getCodeEtatPaiement()));
        entity.setIdModePaiement(modePaiementFacade.findByCode(dto.getCodeModePaiement()));
        entity.setDatePaiement(dto.getDatePaiement());
        Set<LignePaiement> lignePaiementSet = new HashSet();
        LignePaiement lignePaiement;
        for (LignePaiementDto lignePaiementDto : dto.getLignePaiements()) {
            lignePaiement = lignePaiementConverter.dtoToEntity(lignePaiementDto);
            lignePaiement.setIdPaiement(entity);
            lignePaiementSet.add(lignePaiement);
        }
        entity.setLignePaiementSet(lignePaiementSet);
        return entity;
    }
}
