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
@Table(name = "Domaine", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Domaine.findAll", query = "SELECT d FROM Domaine d")
    , @NamedQuery(name = "Domaine.findById", query = "SELECT d FROM Domaine d WHERE d.id = :id")
    , @NamedQuery(name = "Domaine.findByLibelle", query = "SELECT d FROM Domaine d WHERE d.libelle = :libelle")
    , @NamedQuery(name = "Domaine.findByDescription", query = "SELECT d FROM Domaine d WHERE d.description = :description")})
public class Domaine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @Column(name = "description", length = 300)
    private String description;
    @JoinTable(name = "Domaine_Oeuvre", joinColumns = {
        @JoinColumn(name = "idDomaine", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Oeuvre> oeuvreSet;

    public Domaine() {
    }

    public Domaine(Integer id) {
        this.id = id;
    }

    public Domaine(Integer id, String libelle) {
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
        if (!(object instanceof Domaine)) {
            return false;
        }
        Domaine other = (Domaine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Domaine[ id=" + id + " ]";
    }
    
}
