package sn.modelsis.signart.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class LivraisonDto {
    private Integer id;
    private LocalDate dateLivraison;
    private Integer idAdresseLivraison;
    private Integer idAdresseFacturation;
    private Integer idEtatLivraison;
    private Integer idModeLivraison;
    private AdresseDto adresseLivraison;
    private AdresseDto adresseFacturation;
    private String codeEtatLivraison;
    private String codeModeLivraison;
    private String libelleEtatLivraison;
    private String libelleModeLivraison;
    private Set<LigneLivraisonDto> ligneLivraisons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Integer getIdAdresseLivraison() {
        return idAdresseLivraison;
    }

    public void setIdAdresseLivraison(Integer idAdresseLivraison) {
        this.idAdresseLivraison = idAdresseLivraison;
    }

    public Integer getIdAdresseFacturation() {
        return idAdresseFacturation;
    }

    public void setIdAdresseFacturation(Integer idAdresseFacturation) {
        this.idAdresseFacturation = idAdresseFacturation;
    }

    public Integer getIdEtatLivraison() {
        return idEtatLivraison;
    }

    public void setIdEtatLivraison(Integer idEtatLivraison) {
        this.idEtatLivraison = idEtatLivraison;
    }

    public Integer getIdModeLivraison() {
        return idModeLivraison;
    }

    public void setIdModeLivraison(Integer idModeLivraison) {
        this.idModeLivraison = idModeLivraison;
    }

    public AdresseDto getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(AdresseDto adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public AdresseDto getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(AdresseDto adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public String getCodeEtatLivraison() {
        return codeEtatLivraison;
    }

    public void setCodeEtatLivraison(String codeEtatLivraison) {
        this.codeEtatLivraison = codeEtatLivraison;
    }

    public String getCodeModeLivraison() {
        return codeModeLivraison;
    }

    public void setCodeModeLivraison(String codeModeLivraison) {
        this.codeModeLivraison = codeModeLivraison;
    }

    public String getLibelleEtatLivraison() {
        return libelleEtatLivraison;
    }

    public void setLibelleEtatLivraison(String libelleEtatLivraison) {
        this.libelleEtatLivraison = libelleEtatLivraison;
    }

    public String getLibelleModeLivraison() {
        return libelleModeLivraison;
    }

    public void setLibelleModeLivraison(String libelleModeLivraison) {
        this.libelleModeLivraison = libelleModeLivraison;
    }

    public Set<LigneLivraisonDto> getLigneLivraisons() {
        return ligneLivraisons;
    }

    public void setLigneLivraisons(Set<LigneLivraisonDto> ligneLivraisons) {
        this.ligneLivraisons = ligneLivraisons;
    }

}
