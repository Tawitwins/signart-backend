package sn.modelsis.signart.dto;

import sn.modelsis.signart.Profil;
import sn.modelsis.signart.Utilisateur;

/**
 * Jwt subject
 *
 */
public class JwtSubject {

    Integer id;

    String userName;

    String firstName;

    String lastName;

    String email;

    String userType;

    String profil;
    Boolean superAdmin;
    /**
     * Constructor from user Constructor.
     *
     * @param user
     * @param prenom
     * @param nom
     */
    public JwtSubject(final Utilisateur user, String prenom, String nom) {
        this(user.getId(), user.getMail(), prenom, nom, user.getMail(),
                user.getUserType(), user.getIdProfil(), user.getSuperAdmin());
    }

    public JwtSubject(final Integer id, final String userName, final String firstName, final String lastName,
            final String email, final String userType, final Profil profil, final Boolean superAdmin) {
        super();
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.profil = profil.getCode();
        this.superAdmin = superAdmin;
    }


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
