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
@Table(name = "Devise", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Devise.findAll", query = "SELECT d FROM Devise d")
    , @NamedQuery(name = "Devise.findById", query = "SELECT d FROM Devise d WHERE d.id = :id")
    , @NamedQuery(name = "Devise.findByCode", query = "SELECT d FROM Devise d WHERE d.code = :code")
    , @NamedQuery(name = "Devise.findByLibelle", query = "SELECT d FROM Devise d WHERE d.libelle = :libelle")})
public class Devise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 5)
    private String code;
    @Column(name = "libelle", length = 70)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDevise")
    private Set<Commande> commandeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDevise")
    private Set<Client> clientSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDevise")
    private Set<Panier> panierSet;

    public Devise() {
    }

    public Devise(Integer id) {
        this.id = id;
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
    public Set<Commande> getCommandeSet() {
        return commandeSet;
    }

    public void setCommandeSet(Set<Commande> commandeSet) {
        this.commandeSet = commandeSet;
    }

    @XmlTransient
    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
    }

    @XmlTransient
    public Set<Panier> getPanierSet() {
        return panierSet;
    }

    public void setPanierSet(Set<Panier> panierSet) {
        this.panierSet = panierSet;
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
        if (!(object instanceof Devise)) {
            return false;
        }
        Devise other = (Devise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Devise[ id=" + id + " ]";
    }
    
}
