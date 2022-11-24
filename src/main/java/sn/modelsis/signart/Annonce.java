package sn.modelsis.signart;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Annonce", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annonce.findByArtiste", query = "SELECT a FROM Annonce a WHERE a.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "Annonce.findAll", query = "SELECT a FROM Annonce a")
    , @NamedQuery(name = "Annonce.findById", query = "SELECT a FROM Annonce a WHERE a.id = :id")
    , @NamedQuery(name = "Annonce.findByDateDebut", query = "SELECT a FROM Annonce a WHERE a.dateDebut = :dateDebut")
    , @NamedQuery(name = "Annonce.findByDateFin", query = "SELECT a FROM Annonce a WHERE a.dateFin = :dateFin")})
public class Annonce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "titre", nullable = false, length = 100)
    private String titre;
    @Basic(optional = false)
    @Column(name = "description",columnDefinition = "TEXT",nullable = false, length = 2048)
    private String description;
//@Type(type = "org.hibernate.type.TextType")
    private byte[] photo;
    @Column(name = "lieu", nullable = false, length = 500)
    private String lieu;
    @Basic(optional = false)
    @Column(name = "dateDebut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    @Column(name = "etatPublication")
    private Boolean etatPublication;

    public Annonce() {
    }

    public Annonce(Integer id) {
        this.id = id;
    }

    public Annonce(Integer id, String texte, Date dateDebut, Date dateFin) {
        this.id = id;
        this.description = texte;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEtatPublication() {
        return etatPublication;
    }

    public void setEtatPublication(Boolean etatPublication) {
        this.etatPublication = etatPublication;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
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
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Annonce[ id=" + id + " ]";
    }
    
}
