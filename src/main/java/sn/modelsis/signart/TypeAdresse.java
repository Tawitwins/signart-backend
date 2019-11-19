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
@Table(name = "TypeAdresse", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeAdresse.findAll", query = "SELECT t FROM TypeAdresse t")
    , @NamedQuery(name = "TypeAdresse.findById", query = "SELECT t FROM TypeAdresse t WHERE t.id = :id")
    , @NamedQuery(name = "TypeAdresse.findByCode", query = "SELECT t FROM TypeAdresse t WHERE t.code = :code")
    , @NamedQuery(name = "TypeAdresse.findByLibelle", query = "SELECT t FROM TypeAdresse t WHERE t.libelle = :libelle")})
public class TypeAdresse implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeAdresse")
    private Set<Adresse> adresseSet;

    public TypeAdresse() {
    }

    public TypeAdresse(Integer id) {
        this.id = id;
    }

    public TypeAdresse(Integer id, String code, String libelle) {
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
    public Set<Adresse> getAdresseSet() {
        return adresseSet;
    }

    public void setAdresseSet(Set<Adresse> adresseSet) {
        this.adresseSet = adresseSet;
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
        if (!(object instanceof TypeAdresse)) {
            return false;
        }
        TypeAdresse other = (TypeAdresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TypeAdresse[ id=" + id + " ]";
    }
    
}
