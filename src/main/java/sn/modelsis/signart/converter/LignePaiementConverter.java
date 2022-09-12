package sn.modelsis.signart.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sn.modelsis.signart.EtatPaiement;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.facade.*;

import java.text.SimpleDateFormat;

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
    @Inject
    LigneCommandeConverter ligneCommandeConverter;
    @Inject
    LigneCommandeFacade ligneCommandeFacade;
    @Inject
    EtatPaiementFacade etatPaiementFacade;

    @Inject
    PaymentDetailsFacade paymentDetailsFacade;
    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");

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
        if(entity.getIdPaymentDetails() != null)
            dto.setIdPaymentDetails(entity.getIdPaymentDetails().getId());
        dto.setLigneCommande(ligneCommandeConverter.entityToDto(entity.getIdLigneCommande()));
        dto.setCodeEtatPaiement(entity.getIdEtatPaiement().getCode());
        dto.setLibelleEtatPaiement(entity.getIdEtatPaiement().getLibelle());
        dto.setIdEtatPaiement(entity.getIdEtatPaiement().getId());
        dto.setStringPaymentDate(DateFor.format(entity.getDatePaiement()));
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
        entity.setIdModePaiement(modePaiementFacade.findByCode(dto.getCodeModePaiement()));
        entity.setDatePaiement(dto.getDatePaiement());
        entity.setMontant(dto.getMontant());
        entity.setIdLigneCommande(ligneCommandeFacade.find(dto.getLigneCommande()));
        entity.setIdEtatPaiement(etatPaiementFacade.find(dto.getIdEtatPaiement()));
        if(dto.getIdPaymentDetails() != null)
            entity.setIdPaymentDetails(paymentDetailsFacade.find(dto.getIdPaymentDetails()));
        if(dto.getIdPaiement()!= null)
            entity.setIdPaiement(paiementFacade.find(dto.getIdPaiement()));
        return entity;
    }
}
