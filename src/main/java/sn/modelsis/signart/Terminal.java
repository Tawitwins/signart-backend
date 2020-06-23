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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */

@Entity
@Table(name = "Terminal", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Terminal.findAll", query = "SELECT t FROM Terminal t")
    , @NamedQuery(name = "Terminal.findById", query = "SELECT t FROM Terminal t WHERE t.id = :id")})
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", nullable = false)
    private String libelle;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "prix", nullable = false)
    private Integer prix;

    public Terminal() {
    }
    
    

    public Terminal(Integer id, String libelle, String description, Integer prix) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
    }

    public Terminal(String libelle, String description, Integer prix) {
        this.libelle = libelle;
        this.description = description;
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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    
    
    
}
