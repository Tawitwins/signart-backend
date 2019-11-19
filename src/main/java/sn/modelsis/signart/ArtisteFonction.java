/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNNGOMN
 */
@Entity
@Table(name = "Artiste_Fonction", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtisteFonction.findAll", query = "SELECT a FROM ArtisteFonction a"),
    @NamedQuery(name = "ArtisteFonction.findByAnneeDebut", query = "SELECT a FROM ArtisteFonction a WHERE a.anneeDebut = :anneeDebut"),
    @NamedQuery(name = "ArtisteFonction.findByAnneeFin", query = "SELECT a FROM ArtisteFonction a WHERE a.anneeFin = :anneeFin"),
    @NamedQuery(name = "ArtisteFonction.findByIdArtiste", query = "SELECT a FROM ArtisteFonction a WHERE a.artisteFonctionPK.idArtiste = :idArtiste"),
    @NamedQuery(name = "ArtisteFonction.findByIdFonction", query = "SELECT a FROM ArtisteFonction a WHERE a.artisteFonctionPK.idFonction = :idFonction")})
public class ArtisteFonction implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtisteFonctionPK artisteFonctionPK;
    @Column(name = "anneeDebut")
    private Integer anneeDebut;
    @Column(name = "anneeFin")
    private Integer anneeFin;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artiste artiste;
    @JoinColumn(name = "idFonction", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fonction fonction;

    public ArtisteFonction() {
    }

    public ArtisteFonction(ArtisteFonctionPK artisteFonctionPK) {
        this.artisteFonctionPK = artisteFonctionPK;
    }

    public ArtisteFonction(int idArtiste, int idFonction) {
        this.artisteFonctionPK = new ArtisteFonctionPK(idArtiste, idFonction);
    }

    public ArtisteFonctionPK getArtisteFonctionPK() {
        return artisteFonctionPK;
    }

    public void setArtisteFonctionPK(ArtisteFonctionPK artisteFonctionPK) {
        this.artisteFonctionPK = artisteFonctionPK;
    }

    public Integer getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Integer anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Integer getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Integer anneeFin) {
        this.anneeFin = anneeFin;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artisteFonctionPK != null ? artisteFonctionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtisteFonction)) {
            return false;
        }
        ArtisteFonction other = (ArtisteFonction) object;
        if ((this.artisteFonctionPK == null && other.artisteFonctionPK != null) || (this.artisteFonctionPK != null && !this.artisteFonctionPK.equals(other.artisteFonctionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ArtisteFonction[ artisteFonctionPK=" + artisteFonctionPK + " ]";
    }
    
}
