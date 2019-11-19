package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "TypeMarquage", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeMarquage.findAll", query = "SELECT t FROM TypeMarquage t")
    , @NamedQuery(name = "TypeMarquage.findById", query = "SELECT t FROM TypeMarquage t WHERE t.id = :id")
    , @NamedQuery(name = "TypeMarquage.findByCode", query = "SELECT t FROM TypeMarquage t WHERE t.code = :code")
    , @NamedQuery(name = "TypeMarquage.findByLibelle", query = "SELECT t FROM TypeMarquage t WHERE t.libelle = :libelle")})
public class TypeMarquage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeMarquage")
    private Set<MarquageOeuvre> marquageOeuvreSet;

    public TypeMarquage() {
    }

    public TypeMarquage(Integer id) {
        this.id = id;
    }

    public TypeMarquage(Integer id, String code, String libelle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public Set<MarquageOeuvre> getMarquageOeuvreSet() {
        return marquageOeuvreSet;
    }

    public void setMarquageOeuvreSet(Set<MarquageOeuvre> marquageOeuvreSet) {
        this.marquageOeuvreSet = marquageOeuvreSet;
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
        if (!(object instanceof TypeMarquage)) {
            return false;
        }
        TypeMarquage other = (TypeMarquage) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TypeMarquage[ id=" + id + " ]";
    }
    
}
