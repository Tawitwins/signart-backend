package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Client", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Client.findClientArtiste", query = "SELECT cl FROM Client cl Join cl.commandeSet cm Join cm.ligneCommandeSet lc WHERE lc.idOeuvre.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id")
    , @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nom = :nom")
    , @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Client.findBySexe", query = "SELECT c FROM Client c WHERE c.sexe = :sexe")
    , @NamedQuery(name = "Client.findByVille", query = "SELECT c FROM Client c WHERE c.ville = :ville")
    , @NamedQuery(name = "Client.findByDateNaissance", query = "SELECT c FROM Client c WHERE c.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Client.findByIdUser", query = "SELECT c FROM Client c WHERE c.idUser.id = :idUser")})
public class Client implements Serializable {

    public static final String USER_TYPE_CLIENT = "CLIENT";
    public static final String USER_TYPE_ARTISTE = "ARTISTE";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "prenom", length = 50)
    private String prenom;
    @Column(name = "sexe")
    private Character sexe;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "region", length = 50)
    private String region;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @JoinTable(name = "TypeNewsletter_Client", joinColumns = {
        @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idTypeNewsletter", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<TypeNewsletter> typeNewsletterSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient",fetch = FetchType.LAZY)
    private Set<Adresse> adresseSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Set<Commentaire> commentaireSet;
    @JoinColumn(name = "idEtatClient", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatClient idEtatClient;
    @JoinColumn(name = "idPays", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Pays idPays;
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Utilisateur idUser;
    @JoinColumn(name = "idDevise", referencedColumnName = "id")
    @ManyToOne//@ManyToOne(optional = false)
    private Devise idDevise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Set<Commande> commandeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Set<Panier> panierSet;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Devise getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Devise idDevise) {
        this.idDevise = idDevise;
    }

    @XmlTransient
    public Set<Commentaire> getCommentaireSet() {
        return commentaireSet;
    }

    public void setCommentaireSet(Set<Commentaire> commentaireSet) {
        this.commentaireSet = commentaireSet;
    }

    public EtatClient getIdEtatClient() {
        return idEtatClient;
    }

    public void setIdEtatClient(EtatClient idEtatClient) {
        this.idEtatClient = idEtatClient;
    }

    @XmlTransient
    public Set<TypeNewsletter> getTypeNewsletterSet() {
        return typeNewsletterSet;
    }

    public void setTypeNewsletterSet(Set<TypeNewsletter> typeNewsletterSet) {
        this.typeNewsletterSet = typeNewsletterSet;
    }

    @XmlTransient
    public Set<Adresse> getAdresseSet() {
        return adresseSet;
    }

    public void setAdresseSet(Set<Adresse> adresseSet) {
        this.adresseSet = adresseSet;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Set<Commande> getCommandeSet() {
        return commandeSet;
    }

    public void setCommandeSet(Set<Commande> commandeSet) {
        this.commandeSet = commandeSet;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Client[ id=" + id + " ]";
    }

}
