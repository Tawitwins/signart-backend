/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */
@Entity
@Table(name = "Presentation", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Presentation.findAll", query = "SELECT p FROM Presentation p")
    , @NamedQuery(name = "Presentation.findById", query = "SELECT p FROM Presentation p WHERE p.id = :id")
    , @NamedQuery(name = "Presentation.findByArtiste", query = "SELECT p FROM Presentation p WHERE p.idArtiste.id = :idArtiste")})
public class Presentation implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "libelle", length = 5000)
    private String libelle;
    
    @Column(name = "videoId", length = 500)
    private String videoId;
    
    @Column(name = "etatPubPresentation", nullable = false, length = 50)
    private Boolean etatPubPresentation;
     
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    

    public Presentation() {
    }

    public Presentation(String libelle, String videoId, Artiste idArtiste) {
        this.libelle = libelle;
        this.videoId = videoId;
        this.idArtiste = idArtiste;
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Boolean getEtatPubPresentation() {
        return etatPubPresentation;
    }

    public void setEtatPubPresentation(Boolean etatPubPresentation) {
        this.etatPubPresentation = etatPubPresentation;
    }
    
    
    
    
    
    
    
    
}
