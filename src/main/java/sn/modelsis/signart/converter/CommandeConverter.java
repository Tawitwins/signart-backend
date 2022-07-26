package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.Commande;
import sn.modelsis.signart.LigneCommande;
import sn.modelsis.signart.dto.CommandeDto;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.facade.*;

/**
 *
 * @author SNLOM
 */
@Stateless
public class CommandeConverter {

    @Inject
    CommandeFacade commandeFacade;
    @Inject
    EtatCommandeFacade etatcommandeFacade;
    @Inject
    ClientFacade clientFacade;
    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    DeviseFacade deviseFacade;
    @Inject
    OeuvreConverter oeuvreConverter;
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
        dto.setTotal(entity.getMontant());
        dto.setCodeDevise(entity.getIdDevise().getCode());
        dto.setIdClient(entity.getIdClient().getId());
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
        dto.setTotalLivraison(livraison);
        dto.setTotalTaxes(taxes);
        dto.setState(entity.getEtat());
        //dto.setTotal(entity.get.multiply(BigDecimal.valueOf(entity.getQuantite())));
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public Commande dtoToEntity(CommandeDto dto) {
        Commande entity = new Commande();
        entity.setId(dto.getId());
        entity.setDateCommande(dto.getDateCreation());
        entity.setDelaiLivraison(10);
        //entity.setIdOeuvre(oeuvreFacade.find(dto.getOeuvre().getId()));
        //entity.setIdCommande(commandeFacade.findByIdClient(dto.getIdClient()));
        entity.setMontant(dto.getTotal());
        entity.setFraisLivraison(dto.getTotalLivraison());
        entity.setTokenPaiement(dto.getToken());
        //entity.setCommentaire(dto.getCommentaire);

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
