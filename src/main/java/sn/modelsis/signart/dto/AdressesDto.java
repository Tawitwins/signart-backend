package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class AdressesDto {

    private AdresseDto adresseLivraison;
    private AdresseDto adresseFacturation;

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

}
