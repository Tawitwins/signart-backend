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
      @NamedQuery(name = "CodeSignart.findById", query = "SELECT c FROM CodeSignart c WHERE c.id = :id")
     ,@NamedQuery(name = "CodeSignart.findBycode", query = "SELECT c FROM CodeSignart c WHERE c.code = :code")
     ,@NamedQuery(name = "CodeSignart.findByIdAbonne", query = "SELECT c FROM CodeSignart c WHERE c.idAbonne.id = :idAbonne")})
public class CodeSignart implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 1000)
    private String code;
    @JoinColumn(name = "idLicence", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Licence idLicence;
    @JoinColumn(name = "idAbonne", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Abonne idAbonne;

    public CodeSignart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Licence getIdLicence() {
        return idLicence;
    }

    public void setIdLicence(Licence idLicence) {
        this.idLicence = idLicence;
    }

    public Abonne getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(Abonne idAbonne) {
        this.idAbonne = idAbonne;
    }

  

    
    
    
}
