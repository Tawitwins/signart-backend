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
@Table(name = "Panier", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p")
    , @NamedQuery(name = "Panier.findById", query = "SELECT p FROM Panier p WHERE p.id = :id")
    , @NamedQuery(name = "Panier.findByMontant", query = "SELECT p FROM Panier p WHERE p.montant = :montant")
    , @NamedQuery(name = "Panier.findByIdEtatPanier", query = "SELECT p FROM Panier p WHERE p.idEtatPanier = :idEtatPanier")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant", precision = 19, scale = 4)
    private BigDecimal montant;
    @JoinColumn(name = "idEtatPanier", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatPanier idEtatPanier;
    @JoinColumn(name = "idDevise", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Devise idDevise;
    @OneToMany(mappedBy = "idPanier")
    private Set<LignePanier> lignePanierSet;
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Client idClient;

    public Panier() {
    }

    public Panier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public EtatPanier getIdEtatPanier() {
        return idEtatPanier;
    }

    public void setIdEtatPanier(EtatPanier idEtatPanier) {
        this.idEtatPanier = idEtatPanier;
    }

    @XmlTransient
    public Set<LignePanier> getLignePanierSet() {
        return lignePanierSet;
    }

    public void setLignePanierSet(Set<LignePanier> lignePanierSet) {
        this.lignePanierSet = lignePanierSet;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
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
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Panier[ id=" + id + " ]";
    }
    
}
