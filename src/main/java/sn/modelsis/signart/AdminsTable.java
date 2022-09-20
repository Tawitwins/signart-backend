package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "AdminsTable", catalog = "signart", schema = "dbo")
@NamedQueries({
   
    @NamedQuery(name = "AdminsTable.findAll", query = "SELECT a FROM AdminsTable a")
    , @NamedQuery(name = "AdminsTable.findById", query = "SELECT a FROM AdminsTable a WHERE a.id = :id")
    , @NamedQuery(name = "AdminsTable.findByNom", query = "SELECT a FROM AdminsTable a WHERE a.nom = :nom")
    , @NamedQuery(name = "AdminsTable.findByPrenom", query = "SELECT a FROM AdminsTable a WHERE a.prenom = :prenom")
    , @NamedQuery(name = "AdminsTable.findByEmail", query = "SELECT a FROM AdminsTable a WHERE a.email = :email")
    , @NamedQuery(name = "AdminsTable.findByIdUser", query = "SELECT a FROM AdminsTable a WHERE a.idUser.id = :idUser")
    //, @NamedQuery(name = "Admin.findByDateNaissance", query = "SELECT c FROM Admin c WHERE c.dateNaissance = :dateNaissance")
    /*, @NamedQuery(name = "Admin.findByIdUser", query = "SELECT c FROM Admin c WHERE c.idUser.id = :idUser")*/})
public class AdminsTable implements Serializable {

    public static final String USER_TYPE_CLIENT = "CLIENT";
    public static final String USER_TYPE_ARTISTE = "ARTISTE";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "prenom", length = 100)
    private String prenom;
    @Column(name = "nom", length = 100)
    private String nom;
    @Column(name = "telephone", length = 30)
    private String telephone;
    @Column(name = "adresse", length = 200)
    private String adresse;
    @Column(name = "email", length = 80)
    private String email;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Set<Adresse> adresseSet;*/
  
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    private Utilisateur idUser;
    

    public AdminsTable() {
    }

    public AdminsTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
    
    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
//    @XmlTransient
//    public Set<Adresse> getAdresseSet() {
//        return adresseSet;
//    }
//
//    public void setAdresseSet(Set<Adresse> adresseSet) {
//        this.adresseSet = adresseSet;
//    }

   
    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminsTable)) {
            return false;
        }
        AdminsTable other = (AdminsTable) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.AdminsTable[ id=" + id + " ]";
    }

}
