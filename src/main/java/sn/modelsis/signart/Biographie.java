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
import javax.persistence.Id;
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
    @NamedQuery(name = "Biographie.findByDateNaissance", query = "SELECT b FROM Biographie b WHERE b.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Biographie.findByLieuNaissance", query = "SELECT b FROM Biographie b WHERE b.lieuNaissance = :lieuNaissance"),
    @NamedQuery(name = "Biographie.findByNationalite", query = "SELECT b FROM Biographie b WHERE b.nationalite = :nationalite")})
public class Biographie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Size(max = 50)
    @Column(name = "lieuNaissance")
    private String lieuNaissance;
    @Size(max = 50)
    @Column(name = "nationalite")
    private String nationalite;
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
