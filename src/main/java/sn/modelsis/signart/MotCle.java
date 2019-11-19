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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "MotCle", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "MotCle.findAll", query = "SELECT m FROM MotCle m")
    , @NamedQuery(name = "MotCle.findById", query = "SELECT m FROM MotCle m WHERE m.id = :id")
    , @NamedQuery(name = "MotCle.findByLibelle", query = "SELECT m FROM MotCle m WHERE m.libelle = :libelle")})
public class MotCle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "libelle", length = 50)
    private String libelle;
    @JoinTable(name = "Oeuvre_MotCle", joinColumns = {
        @JoinColumn(name = "idMotCle", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Oeuvre> oeuvreSet;

    public MotCle() {
    }

    public MotCle(Integer id) {
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

    @XmlTransient
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
        if (!(object instanceof MotCle)) {
            return false;
        }
        MotCle other = (MotCle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.MotCle[ id=" + id + " ]";
    }
    
}
