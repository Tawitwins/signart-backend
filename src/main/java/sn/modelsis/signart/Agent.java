package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNMBENGUEO
 */
@Entity
@Table(name = "Agent", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a")
    , @NamedQuery(name = "Agent.findById", query = "SELECT a FROM Agent a WHERE a.id = :id")
    , @NamedQuery(name = "Agent.findByNom", query = "SELECT a FROM Agent a WHERE a.nom = :nom")
    , @NamedQuery(name = "Agent.findByIdUser", query = "SELECT a FROM Agent a WHERE a.idUser.id = :idUser")})

public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "prenom", length = 50)
    private String prenom;
    @Column(name = "surnom", length = 50)
    private String surnom;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "adresse", length = 200)
    private String adresse;
    @Column(name = "ville", length = 50)
    private String ville;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgent")
    private Set<LigneLivraison> ligneLivraisonSet;
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Utilisateur idUser;

    @JoinColumn(name = "idMagasin", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Magasin idMagasin;
    /*@JoinColumn(name = "idUser", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Utilisateur idUser;*/
    @JoinColumn(name = "idServiceLivraison", referencedColumnName = "id")
    @ManyToOne
    private ServiceLivraison idServiceLivraison;

    @JoinColumn(name = "idProfil", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Profil idProfil;


    public Agent() {
    }

    public Agent(String nom, String prenom, String surnom, String telephone, String email, String adresse, String ville, Pays idPays) {
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
    }

    public Agent(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }
    @XmlTransient
    public Set<LigneLivraison> getLivraisonSet() {
        return ligneLivraisonSet;
    }

    public void setLivraisonSet(Set<LigneLivraison> livraisonSet) {
        this.ligneLivraisonSet = livraisonSet;
    }

    public Magasin getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Magasin idMagasin) {
        this.idMagasin = idMagasin;
    }

    public ServiceLivraison getIdServiceLivraison() {
        return idServiceLivraison;
    }

    public void setIdServiceDeLivraison(ServiceLivraison idServiceLivraison) {
        this.idServiceLivraison = idServiceLivraison;
    }

    public Profil getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Profil idProfil) {
        this.idProfil = idProfil;
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
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Agent[ id=" + id + " ]";
    }


}
