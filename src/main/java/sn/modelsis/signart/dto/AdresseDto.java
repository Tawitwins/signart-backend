package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class AdresseDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String region;
    private Integer idClient;
    private Integer idPays;
    private String libellePays;
    private Integer idTypeAdresse;
    private String codeTypeAdresse;
    private String libelleTypeAdresse;
    private String codePostal;
    private String telephone;
    private String indicatif;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public String getLibellePays() {
        return libellePays;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
    }

    public Integer getIdTypeAdresse() {
        return idTypeAdresse;
    }

    public void setIdTypeAdresse(Integer idTypeAdresse) {
        this.idTypeAdresse = idTypeAdresse;
    }

    public String getLibelleTypeAdresse() {
        return libelleTypeAdresse;
    }

    public void setLibelleTypeAdresse(String libelleTypeAdresse) {
        this.libelleTypeAdresse = libelleTypeAdresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCodeTypeAdresse() {
        return codeTypeAdresse;
    }

    public void setCodeTypeAdresse(String codeTypeAdresse) {
        this.codeTypeAdresse = codeTypeAdresse;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

}
