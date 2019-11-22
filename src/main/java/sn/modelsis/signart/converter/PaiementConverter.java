package sn.modelsis.signart.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import sn.modelsis.signart.LignePaiement;
import sn.modelsis.signart.LignePanier;
import sn.modelsis.signart.Commande;
import sn.modelsis.signart.LigneCommande;
import sn.modelsis.signart.Paiement;
import sn.modelsis.signart.dto.LignePaiementDto;
import sn.modelsis.signart.dto.PaiementDto;
import sn.modelsis.signart.dto.CommandeDto;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.facade.CommandeFacade;
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
    CommandeConverter commandeConverter;
    @Inject
    LigneCommandeConverter lignecommandeConverter;
    @Inject
    LigneCommandeFacade lignecommandeFacade;
    @Inject
    CommandeFacade commandeFacade;
    @Inject
    LignePaiementConverter lignePaiementConverter;

    /**
     * Converts entity to Dto
     *
     * @param entity
     * @return
     */
    public PaiementDto entityToDto(Paiement entity) {
        PaiementDto dto = new PaiementDto();
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setId(entity.getId());
        dto.setIdEtatPaiement(entity.getIdEtatPaiement());
        dto.setCodeEtatPaiement(etatPaiementFacade.findById(entity.getIdEtatPaiement()).getCode());
        dto.setLibelleEtatPaiement(etatPaiementFacade.findById(entity.getIdEtatPaiement()).getLibelle());
        dto.setIdModePaiement(entity.getIdModePaiement());
        dto.setCodeModePaiement(modePaiementFacade.findById(entity.getIdModePaiement()).getCode());
        dto.setLibelleModePaiement(etatPaiementFacade.findById(entity.getIdModePaiement()).getLibelle());
        Set<LignePaiement> lignePaiementSet = entity.getLignePaiementSet();
        if (lignePaiementSet != null && !lignePaiementSet.isEmpty()) {
            Set<LignePaiementDto> lignePaiementDtoSet = new HashSet<>();
            LignePaiementDto lignePaiementDto;
            for (LignePaiement lignePaiement : lignePaiementSet) {
                lignePaiementDto = lignePaiementConverter.entityToDto(lignePaiement);
                lignePaiementDtoSet.add(lignePaiementDto);
            }
            dto.setLignePaiements(lignePaiementDtoSet);
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
        //entity.setIdEtatPaiement(etatPaiementFacade.findByCode(dto.getCodeEtatPaiement()));
        entity.setIdEtatPaiement(etatPaiementFacade.findByCode(dto.getCodeEtatPaiement()).getId());
        entity.setIdModePaiement(dto.getIdModePaiement());
        entity.setDatePaiement(dto.getDatePaiement());
        //LignePaiement lignePaiement;
        //List<LigneCommande> ligneCommandeSet = lignecommandeFacade.findByIdCommande(dto.getIdCommande());
        //Set<LignePaiement> lignePaiementSet = lignePaiementConverter.dtoToEntity(dto.getLignePaiements());
        //ligneCommandeSet = lignecommandeConverter.entityToDto(commande).getLignesCommande();
        /*if(lignecommande != null) {
            lignePaiement.set
        }*/
        //if (ligneCommandeSet != null && !ligneCommandeSet.isEmpty()) {
            //Set<LigneCommandeDto> ligneCommandeDtoSet = new HashSet<>();
            //LignePaiementDto lignePaiementDto;
            //for (LigneCommande ligneCommande : ligneCommandeSet) {
                //lignePaiementDto = lignePaiementConverter.entityToDto(ligneCommande);
                //lignePaiementConverter.dtoToEntity(dto);
                //ligneCommandeDto = ligneCommandeConverter.entityToDto(ligneCommande);
                //ligneCommandeDtoSet.add(ligneCommandeDto);
                //nb++;
                //montant = montant.add(ligneCommande.getIdOeuvre().getPrix() != null ? ligneCommande.getIdOeuvre().getPrix() : BigDecimal.ZERO);
               // livraison = livraison.add(ligneCommande.getIdOeuvre().getFraisLivraison() != null ? ligneCommande.getIdOeuvre().getFraisLivraison() : BigDecimal.ZERO);
               // taxes = taxes.add(ligneCommande.getIdOeuvre().getTaxes() != null ? ligneCommande.getIdOeuvre().getTaxes() : BigDecimal.ZERO);
            //}
           // dto.setLignesCommande(ligneCommandeDtoSet);
        //}

        //entity.setLignePaiementSet(lignePaiementSet);
        return entity;
    }
}
