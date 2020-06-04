package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Formation", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Formation.findByArtiste", query = "SELECT e FROM Formation e WHERE e.idArtiste.id = :idArtiste")
    , @NamedQuery(name = "Formation.findAll", query = "SELECT e FROM Formation e")
    , @NamedQuery(name = "Formation.findById", query = "SELECT e FROM Formation e WHERE e.id = :id")
    , @NamedQuery(name = "Formation.findByLieu", query = "SELECT e FROM Formation e WHERE e.lieu = :lieu")})
public class Formation implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "sigle", nullable = false, length = 10)
    private String sigle;
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    @Column(name = "lieu", nullable = false, length = 20)
    private String lieu;
    @Column(name = "etatPublication", nullable = false, length = 50)
    private Boolean etatPublication;
    @Column(name = "specialisation")
    private String specialisation;
    @Column(name = "anneeDebut")
    private Integer anneeDebut;
    @Column(name = "anneeFin")
    private Integer anneeFin;
    @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Artiste idArtiste;
    
    
    public Formation() {
    }

    public Formation(Integer id) {
        this.id = id;
    }

    public Formation(Integer id, String adresse) {
        this.id = id;
        this.lieu = adresse;
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


    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public Integer getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Integer anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Integer getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Integer anneeFin) {
        this.anneeFin = anneeFin;
    }
    
    
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Formation[ id=" + id + " ]";
    }

    /**
     * @return the sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * @param sigle the sigle to set
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }
    
}
