package sn.modelsis.signart.dto;

/**
 *
 * @author SNNGOMN
 */
public class AccountDto {
    private String codeProfil;
    private String codePays;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String mobile;
    private Character gender;
    private Integer idUser;
    private Integer idClient;
    private Integer idArtiste;

    /**
     * 
     * @return 
     */
    public String getCodeProfil() {
        return codeProfil;
    }

    /**
     * 
     * @param codeProfil 
     */
    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the gender
     */
    public Character getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * @return the codePays
     */
    public String getCodePays() {
        return codePays;
    }

    /**
     * @param codePays the codePays to set
     */
    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }
}
