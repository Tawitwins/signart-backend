package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Theme", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Theme.findAll", query = "SELECT t FROM Theme t")
    , @NamedQuery(name = "Theme.findById", query = "SELECT t FROM Theme t WHERE t.id = :id")
    , @NamedQuery(name = "Theme.findByLibelle", query = "SELECT t FROM Theme t WHERE t.libelle = :libelle")
    , @NamedQuery(name = "Theme.findByDescription", query = "SELECT t FROM Theme t WHERE t.description = :description")})
public class Theme implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @Column(name = "description", length = 100)
    private String description;
    @ManyToMany(mappedBy = "themeSet")
    private Set<Oeuvre> oeuvreSet;

    public Theme() {
    }

    public Theme(Integer id) {
        this.id = id;
    }

    public Theme(Integer id, String libelle) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Theme[ id=" + id + " ]";
    }
    
}
