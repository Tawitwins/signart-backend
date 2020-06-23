/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */

@Entity
@Table(name = "Delai", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Delai.findAll", query = "SELECT d FROM Delai d")
    , @NamedQuery(name = "Delai.findById", query = "SELECT d FROM Delai d WHERE d.id = :id")})
public class Delai {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", nullable = false)
    private String libelle;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "nbMois", nullable = false)
    private Integer nbMois;
    @Column(name = "prix", nullable = false)
    private Integer prix;

    public Delai(Integer id, String libelle, String description, Integer nbMois) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.nbMois = nbMois;
    }

    public Delai(String libelle, String description, Integer nbMois) {
        this.libelle = libelle;
        this.description = description;
        this.nbMois = nbMois;
    }

    public Delai() {
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    
    

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbMois() {
        return nbMois;
    }

    public void setNbMois(Integer nbMois) {
        this.nbMois = nbMois;
    }
    
    
    
}
