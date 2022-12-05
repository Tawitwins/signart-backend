package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

import sn.modelsis.signart.*;
import sn.modelsis.signart.dto.CommandeDto;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
public class CommandeConverter {

    @Inject
    TarificationFacade tarificationFacade;
    @Inject
    MagasinFacade magasinFacade;
    @Inject
    ServiceLivraisonFacade serviceLivraisonFacade;
    @Inject
    EtatCommandeFacade etatcommandeFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    DeviseFacade deviseFacade;
    @Inject
    LigneCommandeConverter ligneCommandeConverter;

    /**
     * Converts an ligneCommande entity to DTO
     *
     * @param entity
     * @return
     */
    public CommandeDto entityToDto(Commande entity) {
        CommandeDto dto = new CommandeDto();
        dto.setId(entity.getId());
        dto.setDateCreation(entity.getDateCommande());
        //dto.setDateCommande(entity.getDateCommande());
        dto.setTotal(entity.getMontant());
        dto.setCodeDevise(entity.getIdDevise().getCode());
        dto.setIdClient(entity.getIdClient().getId());
        if(entity.getIdMagasin() != null)
            dto.setIdMagasin(entity.getIdMagasin().getId());
        if(entity.getIdTarification() != null)
            dto.setIdTarification(entity.getIdTarification().getId());
        if(entity.getIdServiceLivraison() != null)
            dto.setIdServiceLivraison(entity.getIdServiceLivraison().getId());
        dto.setIdDevise(entity.getIdDevise().getId());
        dto.setIdEtatCommande(entity.getIdEtatCommande().getId());
        dto.setLibelleEtatCommande(entity.getIdEtatCommande().getLibelle());
        Set<LigneCommande> ligneCommandeSet = entity.getLigneCommandeSet();
        int nb = 0;
        BigDecimal montant = BigDecimal.ZERO, livraison = BigDecimal.ZERO, taxes = BigDecimal.ZERO;
        if (ligneCommandeSet != null && !ligneCommandeSet.isEmpty()) {
            Set<LigneCommandeDto> ligneCommandeDtoSet = new HashSet<>();
            LigneCommandeDto ligneCommandeDto;
            for (LigneCommande ligneCommande : ligneCommandeSet) {
                ligneCommandeDto = ligneCommandeConverter.entityToDto(ligneCommande);
                ligneCommandeDtoSet.add(ligneCommandeDto);
                nb++;
                montant = montant.add(ligneCommande.getIdOeuvre().getPrix() != null ? ligneCommande.getIdOeuvre().getPrix() : BigDecimal.ZERO);
                livraison = livraison.add(ligneCommande.getIdOeuvre().getFraisLivraison() != null ? ligneCommande.getIdOeuvre().getFraisLivraison() : BigDecimal.ZERO);
                taxes = taxes.add(ligneCommande.getIdOeuvre().getTaxes() != null ? ligneCommande.getIdOeuvre().getTaxes() : BigDecimal.ZERO);
            }
            dto.setLignesCommande(ligneCommandeDtoSet);
        }
        dto.setToken(entity.getTokenPaiement());
        dto.setNbTotal(nb);
        dto.setNumero(entity.getNumero());
        //dto.setPaymentAdress(paymentAdress);
        //dto.setBillAdress(billAdress);
        //dto.setPayments(payments);
        dto.setRisque(false);
        dto.setTotal(montant);
        dto.setTotalLivraison(entity.getFraisLivraison());
        dto.setTotalTaxes(taxes);
        dto.setState(entity.getEtat());
        //dto.setTotal(entity.get.multiply(BigDecimal.valueOf(entity.getQuantite())));
        dto.setFraisLivraison(entity.getFraisLivraison());
        if(entity.getMontant() != null){
            dto.setMontant(entity.getMontant());
        }
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public Commande dtoToEntity(CommandeDto dto) throws SignArtException {
        Commande entity = new Commande();
        entity.setId(dto.getId());
        //entity.setDateCommande(dto.getDateCommande());
        entity.setDateCommande(dto.getDateCreation());
        entity.setDelaiLivraison(10);
        //entity.setIdOeuvre(oeuvreFacade.find(dto.getOeuvre().getId()));
        //entity.setIdCommande(commandeFacade.findByIdClient(dto.getIdClient()));
        entity.setMontant(dto.getTotal());
        entity.setFraisLivraison(dto.getTotalLivraison());
        entity.setTokenPaiement(dto.getToken());
        //entity.setCommentaire(dto.getCommentaire);

        if(dto.getIdServiceLivraison() != null){
            entity.setIdServiceLivraison(serviceLivraisonFacade.findById(dto.getIdServiceLivraison()));
        }
        if(dto.getIdMagasin() != null){
            entity.setIdMagasin(magasinFacade.findById(dto.getIdMagasin()));
        }
        if(dto.getIdTarification() != null){
            entity.setIdTarification(tarificationFacade.findById(dto.getIdTarification()));
        }
        if(dto.getIdClient() != null){
            entity.setIdClient(clientFacade.find(dto.getIdClient()));
        }
        if(dto.getIdEtatCommande() != null) {
            entity.setEtat(etatcommandeFacade.find(dto.getIdEtatCommande()).getLibelle());
        }
        if(dto.getIdDevise() != null) {
            entity.setIdDevise(deviseFacade.find(dto.getIdDevise()));
        }
        entity.setNumero(dto.getNumero());
        if(dto.getIdEtatCommande() != null) {
            entity.setIdEtatCommande(etatcommandeFacade.find(dto.getIdEtatCommande()));
        }

        //entity.setQuantite(dto.getQuantite());
        return entity;
    }
}
