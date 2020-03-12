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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Technique", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Technique.findAll", query = "SELECT t FROM Technique t")
    , @NamedQuery(name = "Technique.findById", query = "SELECT t FROM Technique t WHERE t.id = :id")
    , @NamedQuery(name = "Technique.findByLibelle", query = "SELECT t FROM Technique t WHERE t.libelle = :libelle")
    , @NamedQuery(name = "Technique.findByMenu", query = "SELECT t FROM Technique t WHERE t.menu.id = :idMenu")})
public class Technique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @JoinColumn(name = "idMenu", referencedColumnName = "id", nullable = false, unique = true)
    @OneToOne
    private Menu menu;
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTechnique")
    //private Set<SousTechnique> sousTechniqueSet;

    public Technique() {
    }

    public Technique(Integer id) {
        this.id = id;
    }

    public Technique(Integer id, String libelle) {
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

    /*@XmlTransient
    public Set<SousTechnique> getSousTechniqueSet() {
        return sousTechniqueSet;
    }

    public void setSousTechniqueSet(Set<SousTechnique> sousTechniqueSet) {
        this.sousTechniqueSet = sousTechniqueSet;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Technique)) {
            return false;
        }
        Technique other = (Technique) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Technique[ id=" + id + " ]";
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
}
