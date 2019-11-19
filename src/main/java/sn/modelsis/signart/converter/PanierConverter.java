package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Panier;
import sn.modelsis.signart.LignePanier;
import sn.modelsis.signart.dto.PanierDto;
import sn.modelsis.signart.dto.LignePanierDto;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.PanierFacade;
import sn.modelsis.signart.facade.DeviseFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class PanierConverter {

    @Inject
    PanierFacade panierFacade;
    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    DeviseFacade deviseFacade;
    @Inject
    OeuvreConverter oeuvreConverter;
    @Inject
    LignePanierConverter lignePanierConverter;

    /**
     * Converts an lignePanier entity to DTO
     *
     * @param entity
     * @return
     */
    public PanierDto entityToDto(Panier entity) {
        PanierDto dto = new PanierDto();
        dto.setId(entity.getId());
        //dto.setDateCreation(entity.);
        dto.setTotal(entity.getMontant());
        dto.setCodeDevise(entity.getIdDevise().getCode());
        dto.setIdClient(entity.getIdClient().getId());
        dto.setIdDevise(entity.getIdDevise().getId());
        dto.setIdEtatPanier(entity.getIdEtatPanier().getId());
        dto.setLibelleEtatPanier(entity.getIdEtatPanier().getLibelle());
        Set<LignePanier> lignePanierSet = entity.getLignePanierSet();
        int nb = 0;
        BigDecimal montant = BigDecimal.ZERO, livraison = BigDecimal.ZERO, taxes = BigDecimal.ZERO;
        if (lignePanierSet != null && !lignePanierSet.isEmpty()) {
            Set<LignePanierDto> lignePanierDtoSet = new HashSet<>();
            LignePanierDto lignePanierDto;
            for (LignePanier lignePanier : lignePanierSet) {
                lignePanierDto = lignePanierConverter.entityToDto(lignePanier);
                lignePanierDtoSet.add(lignePanierDto);
                nb++;
                montant = montant.add(lignePanier.getIdOeuvre().getPrix() != null ? lignePanier.getIdOeuvre().getPrix() : BigDecimal.ZERO);
                livraison = livraison.add(lignePanier.getIdOeuvre().getFraisLivraison() != null ? lignePanier.getIdOeuvre().getFraisLivraison() : BigDecimal.ZERO);
                taxes = taxes.add(lignePanier.getIdOeuvre().getTaxes() != null ? lignePanier.getIdOeuvre().getTaxes() : BigDecimal.ZERO);
            }
            dto.setLignesPanier(lignePanierDtoSet);
        }

        dto.setNbTotal(nb);
        //dto.setNumero(entity.getNumero());
        //dto.setPaymentAdress(paymentAdress);
        //dto.setBillAdress(billAdress);
        //dto.setPayments(payments);
        dto.setRisque(false);
        dto.setTotal(montant);
        dto.setTotalLivraison(livraison);
        dto.setTotalTaxes(taxes);
        //dto.setTotal(entity.get.multiply(BigDecimal.valueOf(entity.getQuantite())));
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public Panier dtoToEntity(PanierDto dto) {
        Panier entity = new Panier();
        //entity.setIdOeuvre(oeuvreFacade.find(dto.getOeuvre().getId()));
        //entity.setIdPanier(panierFacade.findByIdClient(dto.getIdClient()));
        entity.setMontant(dto.getTotal());
        //entity.setQuantite(dto.);
        return entity;
    }
}
