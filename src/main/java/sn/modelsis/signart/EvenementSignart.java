package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNMBENGUEO
 */
@Entity
@Table(name = "Evenement", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "EvenementSignart.findByArtiste", query = "SELECT e FROM EvenementSignart e WHERE e.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "EvenementSignart.findByAdmin", query = "SELECT e FROM EvenementSignart e WHERE e.idAdminUser.id = :idAdminUser")
    , @NamedQuery(name = "EvenementSignart.findById", query = "SELECT e FROM EvenementSignart e WHERE e.id = :id")
    , @NamedQuery(name = "EvenementSignart.findByStatus", query = "SELECT e FROM EvenementSignart e WHERE e.status = :status")
    , @NamedQuery(name = "EvenementSignart.findByCodeEvenement", query = "SELECT e FROM EvenementSignart e WHERE e.codeEvenement = :codeEvenement")})
public class EvenementSignart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "titre", nullable = false, length = 100)
    private String titre;
    @Column(name = "description")
    private String description;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "dateOfficielle")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfficielle;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Artiste idArtiste;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "lienVideo")
    private String lienVideo;
    @Column(name = "codeEvenement")
    private String codeEvenement;
    @Column(name = "prixCodeEvent")
    private Integer prixCodeEvent;
    @Column(name = "nbrCodeAchete")
    private Integer nbrCodeAchete;
    @Column(name = "lieu", length = 100)
    private String lieu;
    @Column(name = "contact")
    private String contact;
    @Column(name = "responsable")
    private String  responsable;
    @ManyToOne
    @JoinColumn(name = "idAdminUser", referencedColumnName = "id")
    private Utilisateur idAdminUser;

    public EvenementSignart() {
    }

    public EvenementSignart(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateOfficielle() {
        return dateOfficielle;
    }

    public void setDateOfficielle(Date dateOfficielle) {
        this.dateOfficielle = dateOfficielle;
    }

    @XmlTransient
    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLienVideo() {
        return lienVideo;
    }

    public void setLienVideo(String lienVideo) {
        this.lienVideo = lienVideo;
    }

    public String getCodeEvenement() {
        return codeEvenement;
    }

    public void setCodeEvenement(String codeEvenement) {
        this.codeEvenement = codeEvenement;
    }

    public Integer getPrixCodeEvent() {
        return prixCodeEvent;
    }

    public void setPrixCodeEvent(Integer prixCodeEvent) {
        this.prixCodeEvent = prixCodeEvent;
    }

    public Integer getNbrCodeAchete() {
        return nbrCodeAchete;
    }

    public void setNbrCodeAchete(Integer nbrCodeAchete) {
        this.nbrCodeAchete = nbrCodeAchete;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Utilisateur getIdUtilisateur() {
        return idAdminUser;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idAdminUser = idUtilisateur;
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
        if (!(object instanceof EvenementSignart)) {
            return false;
        }
        EvenementSignart other = (EvenementSignart) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.EvenementSignart[ id=" + id + " ]";
    }
    
}
