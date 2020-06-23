package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Profil", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p")
    , @NamedQuery(name = "Profil.findById", query = "SELECT p FROM Profil p WHERE p.id = :id")
        , @NamedQuery(name = "Profil.findByCode", query = "SELECT p FROM Profil p WHERE p.code = :code")
    , @NamedQuery(name = "Profil.findByLibelle", query = "SELECT p FROM Profil p WHERE p.libelle = :libelle")})
public class Profil implements Serializable {
    
    public static final String CODE_PROFIL_ARTISTE = "ARTISTE";
    public static final String CODE_PROFIL_CLIENT = "CLIENT";
    public static final String CODE_PROFIL_ADMIN = "ADMIN";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
   


    public Profil() {
    }

    public Profil(Integer id) {
        this.id = id;
    }

    public Profil(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

//    public Set<Menu> getMenuSet() {
//        return menuSet;
//    }
//
//    public void setMenuSet(Set<Menu> menuSet) {
//        this.menuSet = menuSet;
//    }
//
//    @XmlTransient
//    public Set<Utilisateur> getUtilisateurSet() {
//        return utilisateurSet;
//    }
//
//    public void setUtilisateurSet(Set<Utilisateur> utilisateurSet) {
//        this.utilisateurSet = utilisateurSet;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Profil[ id=" + id + " ]";
    }
    
}
