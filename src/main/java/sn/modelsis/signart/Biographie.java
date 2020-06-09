/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNNGOMN
 */
@Entity
@Table(name = "Biographie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biographie.findAll", query = "SELECT b FROM Biographie b"),
    @NamedQuery(name = "Biographie.findById", query = "SELECT b FROM Biographie b WHERE b.id = :id"),
    @NamedQuery(name = "Biographie.findByIdArtiste", query = "SELECT b FROM Biographie b WHERE b.idArtiste.id = :idArtiste"),
    @NamedQuery(name = "Biographie.findByDateNaissance", query = "SELECT b FROM Biographie b WHERE b.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Biographie.findByLieuNaissance", query = "SELECT b FROM Biographie b WHERE b.lieuNaissance = :lieuNaissance"),
    @NamedQuery(name = "Biographie.findByNationalite", query = "SELECT b FROM Biographie b WHERE b.nationalite = :nationalite")})
public class Biographie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;
    @Size(max = 50)
    @Column(name = "lieuNaissance")
    private String lieuNaissance;
    @Size(max = 50)
    @Column(name = "nationalite")
    private String nationalite;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "etatBiographie")
    private Boolean etatBiographie;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    @OneToMany(mappedBy = "idBiographie")
    private Set<Artiste> artisteSet;

    public Biographie() {
    }

    public Biographie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }
    
    

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @XmlTransient
    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getEtatBiographie() {
        return etatBiographie;
    }

    public void setEtatBiographie(Boolean etatBiographie) {
        this.etatBiographie = etatBiographie;
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
        if (!(object instanceof Biographie)) {
            return false;
        }
        Biographie other = (Biographie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Biographie[ id=" + id + " ]";
    }
    
}
