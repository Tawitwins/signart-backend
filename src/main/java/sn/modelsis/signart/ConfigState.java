package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "ConfigState", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigState.findAll", query = "SELECT s FROM ConfigState s")
    , @NamedQuery(name = "ConfigState.findById", query = "SELECT s FROM ConfigState s WHERE s.id = :id")
    , @NamedQuery(name = "ConfigState.findByEtat", query = "SELECT s FROM ConfigState s WHERE s.etat = :etat")
    , @NamedQuery(name = "ConfigState.findBySuivant", query = "SELECT s FROM ConfigState s WHERE s.suivant = :suivant")})
public class ConfigState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "etat", nullable = false, length = 15)
    private String etat;
    @Basic(optional = false)
    @Column(name = "suivant", nullable = false, length = 15)
    private String suivant;

    public ConfigState() {
    }

    public ConfigState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSuivant() {
        return suivant;
    }

    public void setSuivant(String suivant) {
        this.suivant = suivant;
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
        if (!(object instanceof ConfigState)) {
            return false;
        }
        ConfigState other = (ConfigState) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ConfigState[ id=" + id + " ]";
    }
    
}
