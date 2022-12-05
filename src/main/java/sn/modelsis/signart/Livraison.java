package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Livraison", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Livraison.findAll", query = "SELECT l FROM Livraison l")
    , @NamedQuery(name = "Livraison.findById", query = "SELECT l FROM Livraison l WHERE l.id = :id")
    , @NamedQuery(name = "Livraison.findByDate", query = "SELECT l FROM Livraison l WHERE l.dateLivraisonPrevue = :dateLivraison")})
public class Livraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer idCommande;
    @Basic(optional = false)
    @Column(name = "dateLivraisonPrevue", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private Date dateLivraisonPrevue;
    @Basic(optional = false)
    @Column(name = "dateLivraisonEffective", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private Date dateLivraisonEffective;
    @JoinColumn(name = "idAdresseLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Adresse idAdresseLivraison;
    @JoinColumn(name = "idAdresseFacturation", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Adresse idAdresseFacturation;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne
    // @PrimaryKeyJoinColumn(name="idCommande", referencedColumnName="idCommandeClient")
    private Commande commande;
    @JoinColumn(name = "idEtatLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatLivraison idEtatLivraison;
    @JoinColumn(name = "idModeLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ModeLivraison idModeLivraison;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivraison")
    private Set<LigneLivraison> ligneLivraisonSet;

    public Livraison() {
    }

    public Livraison(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Livraison(Integer idCommande, Date dateLivraisonPrevue) {
        this.idCommande = idCommande;
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }



    public Date getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public void setDateLivraisonPrevue(Date dateLivraison) {
        this.dateLivraisonPrevue = dateLivraison;
    }

    public Adresse getIdAdresseLivraison() {
        return idAdresseLivraison;
    }

    public void setIdAdresseLivraison(Adresse idAdresseLivraison) {
        this.idAdresseLivraison = idAdresseLivraison;
    }

    public Adresse getIdAdresseFacturation() {
        return idAdresseFacturation;
    }

    public void setIdAdresseFacturation(Adresse idAdresseFacturation) {
        this.idAdresseFacturation = idAdresseFacturation;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
        this.idCommande = commande.getId();
    }

    public EtatLivraison getIdEtatLivraison() {
        return idEtatLivraison;
    }

    public void setIdEtatLivraison(EtatLivraison idEtatLivraison) {
        this.idEtatLivraison = idEtatLivraison;
    }

    public ModeLivraison getIdModeLivraison() {
        return idModeLivraison;
    }

    public void setIdModeLivraison(ModeLivraison idModeLivraison) {
        this.idModeLivraison = idModeLivraison;
    }

    @XmlTransient
    public Set<LigneLivraison> getLigneLivraisonSet() {
        return ligneLivraisonSet;
    }

    public void setLigneLivraisonSet(Set<LigneLivraison> ligneLivraisonSet) {
        this.ligneLivraisonSet = ligneLivraisonSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraison)) {
            return false;
        }
        Livraison other = (Livraison) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Livraison[ id=" + idCommande + " ]";
    }

    public Date getDateLivraisonEffective() {
        return dateLivraisonEffective;
    }

    public void setDateLivraisonEffective(Date dateLivraisonEffective) {
        this.dateLivraisonEffective = dateLivraisonEffective;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }
    
}
