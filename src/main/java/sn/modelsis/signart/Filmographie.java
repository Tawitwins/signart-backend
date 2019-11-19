/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNNGOMN
 */
@Entity
@Table(name = "Filmographie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filmographie.findByArtiste", query = "SELECT a FROM Filmographie a WHERE a.idArtiste.id = :idArtiste"),
    @NamedQuery(name = "Filmographie.findAll", query = "SELECT f FROM Filmographie f"),
    @NamedQuery(name = "Filmographie.findById", query = "SELECT f FROM Filmographie f WHERE f.id = :id"),
    @NamedQuery(name = "Filmographie.findByLibelle", query = "SELECT f FROM Filmographie f WHERE f.libelle = :libelle"),
    @NamedQuery(name = "Filmographie.findByType", query = "SELECT f FROM Filmographie f WHERE f.type = :type"),
    @NamedQuery(name = "Filmographie.findByAuteur", query = "SELECT f FROM Filmographie f WHERE f.auteur = :auteur"),
    @NamedQuery(name = "Filmographie.findByDuree", query = "SELECT f FROM Filmographie f WHERE f.duree = :duree")})
public class Filmographie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 20)
    @Column(name = "auteur")
    private String auteur;
    @Column(name = "duree")
    @Temporal(TemporalType.TIME)
    private Date duree;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id")
    @ManyToOne
    private Artiste idArtiste;

    public Filmographie() {
    }

    public Filmographie(Integer id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDuree() {
        return duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filmographie)) {
            return false;
        }
        Filmographie other = (Filmographie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Filmographie[ id=" + id + " ]";
    }
    
}
