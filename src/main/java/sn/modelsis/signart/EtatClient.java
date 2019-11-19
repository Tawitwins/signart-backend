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
@Table(name = "EtatClient", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "EtatClient.findAll", query = "SELECT e FROM EtatClient e")
    , @NamedQuery(name = "EtatClient.findById", query = "SELECT e FROM EtatClient e WHERE e.id = :id")
    , @NamedQuery(name = "EtatClient.findByCode", query = "SELECT e FROM EtatClient e WHERE e.code = :code")
    , @NamedQuery(name = "EtatClient.findByLibelle", query = "SELECT e FROM EtatClient e WHERE e.libelle = :libelle")})
public class EtatClient implements Serializable {
    
    public static final String CODE_ETAT_CLIENT_ACTIF = "ACTIF";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 15)
    private String code;
    @Basic(optional = false)
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatClient")
    private Set<Client> clientSet;

    public EtatClient() {
    }

    public EtatClient(Integer id) {
        this.id = id;
    }

    public EtatClient(Integer id, String libelle) {
        this.id = id;
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
    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
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
        if (!(object instanceof EtatClient)) {
            return false;
        }
        EtatClient other = (EtatClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EtatClient[ id=" + id + " ]";
    }

}
