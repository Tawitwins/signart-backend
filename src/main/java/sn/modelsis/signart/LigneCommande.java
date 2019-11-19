package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "LigneCommande", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneCommande.findAll", query = "SELECT l FROM LigneCommande l")
    , @NamedQuery(name = "LigneCommande.findById", query = "SELECT l FROM LigneCommande l WHERE l.id = :id")
    , @NamedQuery(name = "LigneCommande.findByPrix", query = "SELECT l FROM LigneCommande l WHERE l.prix = :prix")
    , @NamedQuery(name = "LigneCommande.findByQuantite", query = "SELECT l FROM LigneCommande l WHERE l.quantite = :quantite")})
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "prix", nullable = false, precision = 19, scale = 4)
    private BigDecimal prix;
    @Basic(optional = false)
    @Column(name = "quantite", nullable = false)
    private int quantite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLigneCommande")
    private Set<LigneLivraison> ligneLivraisonSet;
    @JoinColumn(name = "idCommande", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Commande idCommande;
    @JoinColumn(name = "idEtatLigneCommande", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatLigneCommande idEtatLigneCommande;
    @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Oeuvre idOeuvre;

    public LigneCommande() {
    }

    public LigneCommande(Integer id) {
        this.id = id;
    }

    public LigneCommande(Integer id, BigDecimal prix, int quantite) {
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Commande getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Commande idCommande) {
        this.idCommande = idCommande;
    }

    @XmlTransient
    public Set<LigneLivraison> getLigneLivraisonSet() {
        return ligneLivraisonSet;
    }

    public void setLigneLivraisonSet(Set<LigneLivraison> ligneLivraisonSet) {
        this.ligneLivraisonSet = ligneLivraisonSet;
    }

    public EtatLigneCommande getIdEtatLigneCommande() {
        return idEtatLigneCommande;
    }

    public void setIdEtatLigneCommande(EtatLigneCommande idEtatLigneCommande) {
        this.idEtatLigneCommande = idEtatLigneCommande;
    }

    public Oeuvre getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Oeuvre idOeuvre) {
        this.idOeuvre = idOeuvre;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.idCommande);
        hash = 79 * hash + Objects.hashCode(this.idEtatLigneCommande);
        hash = 79 * hash + Objects.hashCode(this.idOeuvre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LigneCommande other = (LigneCommande) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idCommande, other.idCommande)) {
            return false;
        }
        if (!Objects.equals(this.idEtatLigneCommande, other.idEtatLigneCommande)) {
            return false;
        }
        if (!Objects.equals(this.idOeuvre, other.idOeuvre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.LigneCommande[ id=" + id + " ]";
    }
    
}
