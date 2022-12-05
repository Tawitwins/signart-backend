package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNMBENGUEO
 */
@Entity
@Table(name = "Tarification", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tarification.findAll", query = "SELECT t FROM Tarification t")
    , @NamedQuery(name = "Tarification.findById", query = "SELECT t FROM Tarification t WHERE t.id = :id")
    , @NamedQuery(name = "Tarification.findByZone", query = "SELECT t FROM Tarification t WHERE t.zone = :zone")
        , @NamedQuery(name = "Tarification.findByDistance", query = "SELECT t FROM Tarification t WHERE t.distance = :distance")

})

public class Tarification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "delaiLivraison", length = 50)
    private Integer delaiLivraison;
    @Column(name = "fraisLivraison", length = 20)
    private Integer fraisLivraison;
    @Column(name = "fraisAssurance", length = 50)
    private Integer fraisAssurance;
    @Column(name = "zone", length = 200)
    private String zone;

    @Column(name = "accessibiliteZone", length = 200)
    private String accessibiliteZone;
    @Column(name = "distance", length = 50)
    private Integer distance;
    @Column(name = "categorieDistance", length = 200)
    private String categorieDistance;
    @JoinColumn(name = "idServiceLivraison", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private ServiceLivraison idServiceLivraison;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarification")
    private Set<Commande> CommandeSet;

    public Tarification() {
    }

    public Tarification(Integer delaiLivraison, Integer fraisAssurance, Integer fraisLivraison, String zone) {
        this.delaiLivraison = delaiLivraison;
        this.fraisAssurance = fraisAssurance;
        this.fraisLivraison = fraisLivraison;
        this.zone = zone;
    }

    public Tarification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Commande> getCommandeSet() {
        return CommandeSet;
    }

    public void setCommandeSet(Set<Commande> commandeSet) {
        CommandeSet = commandeSet;
    }
    public Integer getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(Integer delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public Integer getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(Integer fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public Integer getFraisAssurance() {
        return fraisAssurance;
    }

    public void setFraisAssurance(Integer fraisAssurance) {
        this.fraisAssurance = fraisAssurance;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public ServiceLivraison getIdServiceLivraison() {
        return idServiceLivraison;
    }

    public void setIdServiceLivraison(ServiceLivraison idServiceLivraison) {
        this.idServiceLivraison = idServiceLivraison;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getAccessibiliteZone() {
        return accessibiliteZone;
    }

    public void setAccessibiliteZone(String accessibiliteZone) {
        this.accessibiliteZone = accessibiliteZone;
    }

    public String getCategorieDistance() {
        return categorieDistance;
    }

    public void setCategorieDistance(String categorieDistance) {
        this.categorieDistance = categorieDistance;
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
        if (!(object instanceof Tarification)) {
            return false;
        }
        Tarification other = (Tarification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Tarification[ id=" + id + " ]";
    }


}
