package sn.modelsis.signart.converter;

import javax.ejb.Stateless;

import sn.modelsis.signart.OeuvreSouscription;
import sn.modelsis.signart.Souscription;
import sn.modelsis.signart.dto.SouscriptionDto;

/**
 *
 * @author SNNGOM
 */
@Stateless
public class SouscriptionConverter {

    public SouscriptionDto entityToDto(Souscription entity) {
        SouscriptionDto dto = new SouscriptionDto();
        dto.setAdresseGalerie(entity.getAdresseGalerie());
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setCodePays(entity.getCodePays());
        dto.setPrenom(entity.getPrenom());
        dto.setTelephone(entity.getTelephone());
        dto.setEmail(entity.getEmail());
        dto.setSexe(entity.getSexe());
        dto.setSiteWeb(entity.getSiteWeb());
        dto.setVille(entity.getVille());
        dto.setNomGalerie(entity.getNomGalerie());
        dto.setSpecialite(entity.getSpecialite());
        dto.setFormation(entity.getFormation());
        dto.setExposition(entity.getExposition());
      
        return dto;
    }

    

    public Souscription dtoToEntity(SouscriptionDto dto) {
        OeuvreSouscription oeuvreSouscription = new OeuvreSouscription();
        Souscription entity = new Souscription();
        entity.setId(dto.getId());
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        entity.setVille(dto.getVille());
        entity.setSexe(dto.getSexe());
        entity.setTelephone(dto.getTelephone());
        entity.setEmail(dto.getEmail());
        entity.setAdresseGalerie(dto.getAdresseGalerie());
        entity.setNomGalerie(dto.getNomGalerie());
        entity.setSiteWeb(dto.getSiteWeb());
        entity.setCodePays(dto.getCodePays());
        entity.setSpecialite(dto.getSpecialite());
        entity.setFormation(dto.getFormation());
        entity.setExposition(dto.getExposition());
        return entity;
    }
}
