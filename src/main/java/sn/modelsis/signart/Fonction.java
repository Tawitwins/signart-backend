/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNNGOMN
 */
@Entity
@Table(name = "Fonction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f"),
    @NamedQuery(name = "Fonction.findById", query = "SELECT f FROM Fonction f WHERE f.id = :id"),
    @NamedQuery(name = "Fonction.findByLibelle", query = "SELECT f FROM Fonction f WHERE f.libelle = :libelle")})
public class Fonction implements Serializable {
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "fonction")
    private Collection<ArtisteFonction> artisteFonctionCollection;
    private static final long serialVersionUID = 1L;*/
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "libelle")
    private String libelle;
  /*  @ManyToMany(mappedBy = "fonctionSet")
    private Set<Artiste> artisteSet;*/

    public Fonction() {
    }

    public Fonction(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Fonction[ id=" + id + " ]";
    }

    /**
     * @return the artisteSet
     */
/*    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
    }*/

/*    @XmlTransient
    public Collection<ArtisteFonction> getArtisteFonctionCollection() {
        return artisteFonctionCollection;
    }

    public void setArtisteFonctionCollection(Collection<ArtisteFonction> artisteFonctionCollection) {
        this.artisteFonctionCollection = artisteFonctionCollection;
    }
    */
}
