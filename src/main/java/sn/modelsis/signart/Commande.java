package sn.modelsis.signart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Commande", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findById", query = "SELECT c FROM Commande c WHERE c.id = :id")
    , @NamedQuery(name = "Commande.findByNumero", query = "SELECT c FROM Commande c WHERE c.numero = :numero")
    , @NamedQuery(name = "Commande.findByDate", query = "SELECT c FROM Commande c WHERE c.dateCommande = :dateCommande")
    , @NamedQuery(name = "Commande.findByDelaiLivraison", query = "SELECT c FROM Commande c WHERE c.delaiLivraison = :delaiLivraison")
    , @NamedQuery(name = "Commande.findByMontant", query = "SELECT c FROM Commande c WHERE c.montant = :montant")
    , @NamedQuery(name = "Commande.findByFraisLivraison", query = "SELECT c FROM Commande c WHERE c.fraisLivraison = :fraisLivraison")
    , @NamedQuery(name = "Commande.findByCommentaire", query = "SELECT c FROM Commande c WHERE c.commentaire = :commentaire")
    , @NamedQuery(name = "Commande.findByToken", query = "SELECT c FROM Commande c WHERE c.tokenPaiement = :tokenPaiement")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateCommande", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDate dateCommande;
    @Column(name = "delaiLivraison", nullable = false)
    private Integer delaiLivraison;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "montant", nullable = false, precision = 19, scale = 4)
    private BigDecimal montant;
    @Basic(optional = false)
    @Column(name = "fraisLivraison", nullable = false, precision = 19, scale = 4)
    private BigDecimal fraisLivraison;
    @Column(name = "commentaire", length = 500)
    private String commentaire;
    @Column(name = "numero", nullable = false, length = 50)
    private String numero;
    @Column(name = "etat", length = 15)
    private String etat;
    @Column(name = "tokenPaiement", length = 200)
    private String tokenPaiement;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commande")
    private Livraison livraison;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commande")
    private Paiement paiement;
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Client idClient;
    @JoinColumn(name = "idDevise", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Devise idDevise;
    @JoinColumn(name = "idEtatCommande", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatCommande idEtatCommande;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCommande")
    private Set<LigneCommande> ligneCommandeSet;

    public Commande() {
    }

    public Commande(Integer id) {
        this.id = id;
    }

    public Commande(Integer id, LocalDate date, BigDecimal montant, BigDecimal fraisLivraison) {
        this.id = id;
        this.dateCommande = date;
        this.montant = montant;
        this.fraisLivraison = fraisLivraison;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Integer getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(Integer delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(BigDecimal fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat){
        this.etat = etat;
    }
    public String getTokenPaiement() {
        return tokenPaiement;
    }

    public void setTokenPaiement(String tokenPaiement) {
        this.tokenPaiement = tokenPaiement;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
    }

    public EtatCommande getIdEtatCommande() {
        return idEtatCommande;
    }

    public void setIdEtatCommande(EtatCommande idEtatCommande) {
        this.idEtatCommande = idEtatCommande;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    @XmlTransient
    public Set<LigneCommande> getLigneCommandeSet() {
        return ligneCommandeSet;
    }

    public void setLigneCommandeSet(Set<LigneCommande> ligneCommandeSet) {
        this.ligneCommandeSet = ligneCommandeSet;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Commande[ id=" + id + " ]";
    }

}
