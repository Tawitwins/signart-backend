package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Parametrage", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Parametrage.findAll", query = "SELECT p FROM Panier p")
    , @NamedQuery(name = "Parametrage.findById", query = "SELECT p FROM Panier p WHERE p.id = :id")
    , @NamedQuery(name = "Parametrage.findByParamName", query = "SELECT p FROM Parametrage p WHERE p.paramName = :paramName")})
public class Parametrage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ParamName")
    private String paramName;
    @Column(name = "Statut")
    private Boolean statut;
    @Column(name = "Value")
    private String value;

    public Parametrage() {
    }


    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paramName != null ? paramName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrage)) {
            return false;
        }
        Parametrage other = (Parametrage) object;
        return !((this.paramName == null && other.paramName != null) || (this.paramName != null && !this.paramName.equals(other.paramName)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Parametrage[ paramName=" + paramName + " ]";
    }
    
}
