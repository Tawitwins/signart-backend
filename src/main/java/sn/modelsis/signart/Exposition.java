package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Exposition", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
    //@NamedQuery(name = "Exposition.findByArtiste", query = "SELECT e FROM Exposition e Join e.artisteSet a where a.id = :idArtiste")
      @NamedQuery(name = "Exposition.findByArtiste", query = "SELECT e FROM Exposition e WHERE e.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "ExpExposition.findByArtisteosition.findAll", query = "SELECT e FROM Exposition e")
    , @NamedQuery(name = "Exposition.findById", query = "SELECT e FROM Exposition e WHERE e.id = :id")
    , @NamedQuery(name = "Exposition.findByDateDebut", query = "SELECT e FROM Exposition e WHERE e.dateDebut = :dateDebut")
    , @NamedQuery(name = "Exposition.findByDateFin", query = "SELECT e FROM Exposition e WHERE e.dateFin = :dateFin")
    , @NamedQuery(name = "Exposition.findByAdresse", query = "SELECT e FROM Exposition e WHERE e.adresse = :adresse")})
public class Exposition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "titre", nullable = false, length = 100)
    private String titre;
    @Basic(optional = false)
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Basic(optional = true)
    @Column(name = "adresse", nullable = false, length = 100)
    private String adresse;
    @Column(name = "latitude", nullable = false, length = 100)
    private String latitude;
    @Column(name = "longitude", nullable = false, length = 100)
    private String longitude;
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    @Lob
    @Column(name = "description", length = 2147483647)
    private String description;
   // @Lob
   // @Column(name = "photo")
  //  private byte[] photo;
    @ManyToMany(mappedBy = "expositionSet")
    private Set<Artiste> artisteSet;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    @Column(name = "etatExposition")
    private Boolean etatExposition;

    public Exposition() {
    }

    public Exposition(Integer id) {
        this.id = id;
    }

    public Exposition(Integer id, Date dateDebut, Date dateFin, String adresse, String type) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEtatExposition() {
        return etatExposition;
    }

    public void setEtatExposition(Boolean etatExposition) {
        this.etatExposition = etatExposition;
    }
    
    
    
    

    public Artiste getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Artiste idArtiste) {
        this.idArtiste = idArtiste;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    /*public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }*/

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @XmlTransient
    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
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
        if (!(object instanceof Exposition)) {
            return false;
        }
        Exposition other = (Exposition) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Exposition[ id=" + id + " ]";
    }
    
}
