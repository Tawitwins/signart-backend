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
@Entity
@Table(name = "EtatAbonnement", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "EtatAbonnement.findAll", query = "SELECT e FROM EtatAbonnement e")
    , @NamedQuery(name = "EtatAbonnement.findById", query = "SELECT e FROM EtatAbonnement e WHERE e.id = :id")
    , @NamedQuery(name = "EtatAbonnement.findByLibelle", query = "SELECT e FROM EtatAbonnement e WHERE e.libelle = :libelle")})
public class EtatAbonnement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", nullable = false)
    private String libelle;
    @Column(name = "description", nullable = false)
    private String description;

    public EtatAbonnement(Integer id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public EtatAbonnement() {
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
    
    
    
}
