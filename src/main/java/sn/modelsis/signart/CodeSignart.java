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
 * @author snfayemp
 */

@Entity
@Table(name = "CodeSignart", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "CodeSignart.findById", query = "SELECT c FROM CodeSignart c WHERE c.id = :id")})
public class CodeSignart implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "keyValue", length = 1000)
    private String keyValue;
    @Column(name = "keyAlias", length = 100)
    private String keyAlias;
    @JoinColumn(name = "idAbonnement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Abonnement idAbonnement;

    public CodeSignart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public Abonnement getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Abonnement idAbonnement) {
        this.idAbonnement = idAbonnement;
    }
    
    
    
}
