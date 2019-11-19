package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "LignePanier", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "LignePanier.findAll", query = "SELECT l FROM LignePanier l")
    , @NamedQuery(name = "LignePanier.findByIdClient", query = "SELECT l FROM LignePanier l where l.idPanier.idClient.id = :idClient")
    , @NamedQuery(name = "LignePanier.findById", query = "SELECT l FROM LignePanier l WHERE l.id = :id")
    , @NamedQuery(name = "LignePanier.findByQuantite", query = "SELECT l FROM LignePanier l WHERE l.quantite = :quantite")
    , @NamedQuery(name = "LignePanier.findByPrix", query = "SELECT l FROM LignePanier l WHERE l.prix = :prix")})
public class LignePanier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantite", nullable = false)
    private int quantite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "prix", nullable = false, precision = 19, scale = 4)
    private BigDecimal prix;
    @JoinColumn(name = "idOeuvre", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Oeuvre idOeuvre;
    @JoinColumn(name = "idEtatLignePanier", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatLignePanier idEtatLignePanier;
    @JoinColumn(name = "idPanier", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Panier idPanier;

    public LignePanier() {
    }

    public LignePanier(Integer id) {
        this.id = id;
    }

    public LignePanier(Integer id, int quantite, BigDecimal prix) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Oeuvre getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Oeuvre idOeuvre) {
        this.idOeuvre = idOeuvre;
    }
    
    public EtatLignePanier getIdEtatLignePanier() {
        return idEtatLignePanier;
    }

    public void setIdEtatLignePanier(EtatLignePanier idEtatLignePanier) {
        this.idEtatLignePanier = idEtatLignePanier;
    }

    public Panier getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Panier idPanier) {
        this.idPanier = idPanier;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.idOeuvre);
        hash = 37 * hash + Objects.hashCode(this.idEtatLignePanier);
        hash = 37 * hash + Objects.hashCode(this.idPanier);
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
        final LignePanier other = (LignePanier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idOeuvre, other.idOeuvre)) {
            return false;
        }
        if (!Objects.equals(this.idEtatLignePanier, other.idEtatLignePanier)) {
            return false;
        }
        return Objects.equals(this.idPanier, other.idPanier);
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.LignePanier[ id=" + id + " ]";
    }
    
}
