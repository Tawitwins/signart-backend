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
@Table(name = "Licence", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Licence.findById", query = "SELECT a FROM Licence a WHERE a.id = :id")
    , @NamedQuery(name = "Licence.findByIdAbonnement", query = "SELECT a FROM Licence a WHERE a.idAbonnement.id = :idAbonnement")})
public class Licence implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "valeur", length = 50)
    private String valeur;
    @JoinColumn(name = "idAbonnement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Abonnement idAbonnement;

    public Licence(Integer id, String valeur, Abonnement idAbonnement) {
        this.id = id;
        this.valeur = valeur;
        this.idAbonnement = idAbonnement;
    }

    public Licence() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Abonnement getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Abonnement idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

   
    
    
    
}
