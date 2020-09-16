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
 * @author snfayemp
 */
@Entity
@Table(name = "EtatLicence", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "EtatLicence.findAll", query = "SELECT e FROM EtatLicence e")
    , @NamedQuery(name = "EtatLicence.findById", query = "SELECT e FROM EtatLicence e WHERE e.id = :id")
    , @NamedQuery(name = "EtatLicence.findByLibelle", query = "SELECT e FROM EtatLicence e WHERE e.libelle = :libelle")})
public class EtatLicence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", nullable = false)
    private String libelle;


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

    public EtatLicence() {
    }

    
    
    
}
