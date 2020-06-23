/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */

@Entity
@Table(name = "ListeSelection_Oeuvres", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListeSelection_Oeuvres.findById", query = "SELECT a FROM ListeSelection_Oeuvres a WHERE a.id = :Id")
   ,@NamedQuery(name = "ListeSelection_Oeuvres.findByIdListe", query = "SELECT a FROM ListeSelection_Oeuvres a WHERE a.idListe.id = :idListe")})
public class ListeSelection_Oeuvres implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "idListe", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ListeSelection idListe;
    @Column(name = "nomOeuvre", nullable = false)
    private String nomOeuvre;


    public ListeSelection_Oeuvres() {
    }

    public ListeSelection_Oeuvres(Integer id, ListeSelection idListe, String nomOeuvre) {
        this.id = id;
        this.idListe = idListe;
        this.nomOeuvre = nomOeuvre;
    }

    public ListeSelection_Oeuvres(ListeSelection idListe, String nomOeuvre) {
        this.idListe = idListe;
        this.nomOeuvre = nomOeuvre;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListeSelection getIdListe() {
        return idListe;
    }

    public void setIdListe(ListeSelection idListe) {
        this.idListe = idListe;
    }
  
    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }


   
   
   

    
    
    

   
    
    
    
}
