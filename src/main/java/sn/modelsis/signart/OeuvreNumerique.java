/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

/**
 *
 * @author Pendaaa
 */



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


@Entity
@Table(name = "OeuvreNumerique", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "OeuvreNumerique.findAll", query = "SELECT i FROM OeuvreNumerique i")
    , @NamedQuery(name = "OeuvreNumerique.findById", query = "SELECT i FROM OeuvreNumerique i WHERE i.id = :id")
    , @NamedQuery(name = "OeuvreNumerique.findByName", query = "SELECT i FROM OeuvreNumerique i WHERE i.nom = :nom")})
public class OeuvreNumerique implements Serializable{
        private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "annee", nullable = false)
    private Integer annee;
    @JoinColumn(name = "identiteAuteur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste identiteAuteur;
    @Column(name = "titre", nullable = false)
    private String titre;
    @Column(name = "largeur", nullable = false)
    private Integer largeur;
    @Column(name = "longueur", nullable = false)
    private Integer longueur;
    @Column(name = "technique", nullable = false)
    private String technique;
    @Column(name = "motscles", nullable = false)
    private String motscles;
    @Column(name = "tarif", nullable = false, length = 5000)
    private Integer tarif;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "nom", nullable = false)
    private String nom;

    public OeuvreNumerique() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Artiste getIdentiteAuteur() {
        return identiteAuteur;
    }

    public void setIdentiteAuteur(Artiste identiteAuteur) {
        this.identiteAuteur = identiteAuteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public Integer getLongueur() {
        return longueur;
    }

    public void setLongueur(Integer longueur) {
        this.longueur = longueur;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getMotscles() {
        return motscles;
    }

    public void setMotscles(String motscles) {
        this.motscles = motscles;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    
    
    
    

    
    
    
}
