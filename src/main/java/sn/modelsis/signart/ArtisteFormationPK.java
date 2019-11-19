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
public class ArtisteFormationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArtiste")
    private int idArtiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFormation")
    private int idFormation;

    public ArtisteFormationPK() {
    }

    public ArtisteFormationPK(int idArtiste, int idFormation) {
        this.idArtiste = idArtiste;
        this.idFormation = idFormation;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArtiste;
        hash += (int) idFormation;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtisteFormationPK)) {
            return false;
        }
        ArtisteFormationPK other = (ArtisteFormationPK) object;
        if (this.idArtiste != other.idArtiste) {
            return false;
        }
        if (this.idFormation != other.idFormation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ArtisteFormationPK[ idArtiste=" + idArtiste + ", idFormation=" + idFormation + " ]";
    }
    
}
