package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
*/
@Entity
@Table(name = "SousTechnique", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "SousTechnique.findAll", query = "SELECT s FROM SousTechnique s")
    , @NamedQuery(name = "SousTechnique.findById", query = "SELECT s FROM SousTechnique s WHERE s.id = :id")
    , @NamedQuery(name = "SousTechnique.findByLibelle", query = "SELECT s FROM SousTechnique s WHERE s.libelle = :libelle")
    , @NamedQuery(name = "SousTechnique.findByTechnique", query = "SELECT s FROM SousTechnique s WHERE s.idTechnique.id = :idTechnique")})
public class SousTechnique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @JoinColumn(name = "idTechnique", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Technique idTechnique;
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSousTechnique")
    //private Set<Oeuvre> oeuvreSet;

    public SousTechnique() {
    }

    public SousTechnique(Integer id) {
        this.id = id;
    }

    public SousTechnique(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    public Technique getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Technique idTechnique) {
        this.idTechnique = idTechnique;
    }

   /* @XmlTransient
    public Set<Oeuvre> getOeuvreSet() {
        return oeuvreSet;
    }

    public void setOeuvreSet(Set<Oeuvre> oeuvreSet) {
        this.oeuvreSet = oeuvreSet;
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
        if (!(object instanceof SousTechnique)) {
            return false;
        }
        SousTechnique other = (SousTechnique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.SousTechnique[ id=" + id + " ]";
    }*/
    
} 
