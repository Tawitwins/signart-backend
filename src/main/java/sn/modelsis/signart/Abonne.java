/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */
@Entity
@Table(name = "Abonne", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Abonne.findAll", query = "SELECT a FROM Abonne a")
    , @NamedQuery(name = "Abonne.findById", query = "SELECT a FROM Abonne a WHERE a.id = :id")
    , @NamedQuery(name = "Abonne.findByIdListeSelection", query = "SELECT a FROM Abonne a WHERE a.idListeSelection.id = :idListeSelection")    
        , @NamedQuery(name = "Abonne.findByIdUtilisateur", query = "SELECT a FROM Abonne a WHERE a.idUtilisateur.id = :idUtilisateur")})

public class Abonne implements Serializable {

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
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "pays", length = 50)
    private String pays;
    @Column(name = "region", length = 50)
    private String region;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "adresse", length = 200)
    private String adresse;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @JoinColumn(name = "idListeSelection", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ListeSelection idListeSelection;

    public Abonne(Integer id, String nom, String prenom, String email, String telephone, String pays, String region, String ville, String adresse, Utilisateur idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
        this.idUtilisateur = idUtilisateur;
    }

    public Abonne(String nom, String prenom, String email, String telephone, String pays, String region, String ville, String adresse, Utilisateur idUtilisateur) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
        this.idUtilisateur = idUtilisateur;
    }

    public Abonne() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public ListeSelection getIdListeSelection() {
        return idListeSelection;
    }

    public void setIdListeSelection(ListeSelection idListeSelection) {
        this.idListeSelection = idListeSelection;
    }
    
    
    
    
    
}
