/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author SNNGOMN
 */
@Embeddable
public class ArtisteFonctionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArtiste")
    private int idArtiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFonction")
    private int idFonction;

    public ArtisteFonctionPK() {
    }

    public ArtisteFonctionPK(int idArtiste, int idFonction) {
        this.idArtiste = idArtiste;
        this.idFonction = idFonction;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public int getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(int idFonction) {
        this.idFonction = idFonction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArtiste;
        hash += (int) idFonction;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtisteFonctionPK)) {
            return false;
        }
        ArtisteFonctionPK other = (ArtisteFonctionPK) object;
        if (this.idArtiste != other.idArtiste) {
            return false;
        }
        if (this.idFonction != other.idFonction) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ArtisteFonctionPK[ idArtiste=" + idArtiste + ", idFonction=" + idFonction + " ]";
    }
    
}
