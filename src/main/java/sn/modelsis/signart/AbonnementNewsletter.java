package sn.modelsis.signart;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "AbonnementNewsletter", catalog = "signart", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idTypeNewsletter", "email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AbonnementNewsletter.findAll", query = "SELECT a FROM AbonnementNewsletter a")
    , @NamedQuery(name = "AbonnementNewsletter.findById", query = "SELECT a FROM AbonnementNewsletter a WHERE a.id = :id")
    , @NamedQuery(name = "AbonnementNewsletter.findByEmail", query = "SELECT a FROM AbonnementNewsletter a WHERE a.email = :email")})
public class AbonnementNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 70)
    private String email;
    @JoinColumn(name = "idTypeNewsletter", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TypeNewsletter idTypeNewsletter;

    public AbonnementNewsletter() {
    }

    public AbonnementNewsletter(Integer id) {
        this.id = id;
    }

    public AbonnementNewsletter(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeNewsletter getIdTypeNewsletter() {
        return idTypeNewsletter;
    }

    public void setIdTypeNewsletter(TypeNewsletter idTypeNewsletter) {
        this.idTypeNewsletter = idTypeNewsletter;
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
        if (!(object instanceof AbonnementNewsletter)) {
            return false;
        }
        AbonnementNewsletter other = (AbonnementNewsletter) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.AbonnementNewsletter[ id=" + id + " ]";
    }

}
