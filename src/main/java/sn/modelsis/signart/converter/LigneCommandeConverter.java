package sn.modelsis.signart.converter;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.LigneCommande;
import sn.modelsis.signart.LigneLivraison;
import sn.modelsis.signart.dto.LigneCommandeDto;
import sn.modelsis.signart.facade.EtatLigneCommandeFacade;
import sn.modelsis.signart.facade.OeuvreFacade;
import sn.modelsis.signart.facade.CommandeFacade;

/**
 *
 * @author SNLOM
 */
@Stateless
public class LigneCommandeConverter {

    @Inject
    CommandeFacade commandeFacade;
    @Inject
    OeuvreFacade oeuvreFacade;
    @Inject
    OeuvreConverter oeuvreConverter;
    @Inject
    EtatLigneCommandeFacade etatLigneCommandeFacade;
    @Inject
    LigneLivraisonConverter ligneLivraisonConverter;

    /**
     * Converts an ligneCommande entity to DTO
     * @param entity
     * @return
     */
    public LigneCommandeDto entityToDto(LigneCommande entity) {
        LigneCommandeDto dto = new LigneCommandeDto();
        dto.setId(entity.getId());
        dto.setIdCommande(entity.getIdCommande().getId());
        dto.setNumeroCommande(entity.getIdCommande().getNumero());
        dto.setOeuvre(oeuvreConverter.entityToDto(oeuvreFacade.find(entity.getIdOeuvre().getId())));
        dto.setPrix(entity.getPrix());
        dto.setQuantite(entity.getQuantite());
        dto.setTotal(entity.getPrix().multiply(BigDecimal.valueOf(entity.getQuantite())));
        return dto;
    }
    public LigneCommandeDto entityToDtoPlusLigneLivraison(LigneCommande entity) {
        LigneCommandeDto dto = new LigneCommandeDto();
        dto.setId(entity.getId());
        dto.setIdCommande(entity.getIdCommande().getId());
        dto.setNumeroCommande(entity.getIdCommande().getNumero());
        dto.setOeuvre(oeuvreConverter.entityToDto(oeuvreFacade.find(entity.getIdOeuvre().getId())));
        dto.setPrix(entity.getPrix());
        dto.setQuantite(entity.getQuantite());
        dto.setTotal(entity.getPrix().multiply(BigDecimal.valueOf(entity.getQuantite())));
        LigneLivraison  ligneLiv= new LigneLivraison();
        for (LigneLivraison ligneLivraison : entity.getLigneLivraisonSet()) {
            ligneLiv = ligneLivraison;
        }
        dto.setLigneLivraison(ligneLivraisonConverter.entityToDto(ligneLiv,false));
        return dto;
    }

    /**
     * 
     * @param dto
     * @return 
     */
    public LigneCommande dtoToEntity(LigneCommandeDto dto) {
        LigneCommande entity = new LigneCommande();
        entity.setIdOeuvre(oeuvreFacade.find(dto.getOeuvre().getId()));
        entity.setIdCommande(commandeFacade.find(dto.getIdCommande()));
        if(dto.getEtatLigneCommande()!= null)
            entity.setIdEtatLigneCommande(etatLigneCommandeFacade.findByCode(dto.getEtatLigneCommande()));
        else
            entity.setIdEtatLigneCommande(etatLigneCommandeFacade.find(dto.getIdEtatLigneCommande()));
        entity.setPrix(dto.getPrix());
        entity.setQuantite(dto.getQuantite());
        return entity;
        
       
    }
}
