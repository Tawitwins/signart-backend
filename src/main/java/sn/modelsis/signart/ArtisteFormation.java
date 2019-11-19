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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNNGOMN
 */
@Entity
@Table(name = "Artiste_Formation", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtisteFormation.findAll", query = "SELECT a FROM ArtisteFormation a"),
    @NamedQuery(name = "ArtisteFormation.findByIdArtiste", query = "SELECT a FROM ArtisteFormation a WHERE a.artisteFormationPK.idArtiste = :idArtiste"),
    @NamedQuery(name = "ArtisteFormation.findByIdFormation", query = "SELECT a FROM ArtisteFormation a WHERE a.artisteFormationPK.idFormation = :idFormation"),
    @NamedQuery(name = "ArtisteFormation.findBySpecialisation", query = "SELECT a FROM ArtisteFormation a WHERE a.specialisation = :specialisation"),
    @NamedQuery(name = "ArtisteFormation.findByAnneeDebut", query = "SELECT a FROM ArtisteFormation a WHERE a.anneeDebut = :anneeDebut"),
    @NamedQuery(name = "ArtisteFormation.findByAnneeFin", query = "SELECT a FROM ArtisteFormation a WHERE a.anneeFin = :anneeFin")})
public class ArtisteFormation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtisteFormationPK artisteFormationPK;
    @Size(max = 50)
    @Column(name = "specialisation")
    private String specialisation;
    @Column(name = "anneeDebut")
    private Integer anneeDebut;
    @Column(name = "anneeFin")
    private Integer anneeFin;

    public ArtisteFormation() {
    }

    public ArtisteFormation(ArtisteFormationPK artisteFormationPK) {
        this.artisteFormationPK = artisteFormationPK;
    }

    public ArtisteFormation(int idArtiste, int idFormation) {
        this.artisteFormationPK = new ArtisteFormationPK(idArtiste, idFormation);
    }

    public ArtisteFormationPK getArtisteFormationPK() {
        return artisteFormationPK;
    }

    public void setArtisteFormationPK(ArtisteFormationPK artisteFormationPK) {
        this.artisteFormationPK = artisteFormationPK;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artisteFormationPK != null ? artisteFormationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtisteFormation)) {
            return false;
        }
        ArtisteFormation other = (ArtisteFormation) object;
        if ((this.artisteFormationPK == null && other.artisteFormationPK != null) || (this.artisteFormationPK != null && !this.artisteFormationPK.equals(other.artisteFormationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ArtisteFormation[ artisteFormationPK=" + artisteFormationPK + " ]";
    }
    
}
