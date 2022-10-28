package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SNMBENGUEO
 */
@Entity
@Table(name = "CodeEventSignart", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "CodeEventSignart.findByCode", query = "SELECT e FROM CodeEventSignart e WHERE e.code = :code")
    , @NamedQuery(name = "CodeEventSignart.findByEvenement", query = "SELECT e FROM CodeEventSignart e WHERE e.idEvenementSignart.id = :idEvent")
    , @NamedQuery(name = "CodeEventSignart.findById", query = "SELECT e FROM CodeEventSignart e WHERE e.id = :id")
    , @NamedQuery(name = "CodeEventSignart.findByStatus", query = "SELECT e FROM CodeEventSignart e WHERE e.status = :status")})
public class CodeEventSignart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "dateOfficielle")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfficielle;
    @JoinColumn(name = "idEvenementSignart", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private EvenementSignart idEvenementSignart;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "code")
    private String code;
    @Column(name = "prixCodeEvent")
    private Integer prixCodeEvent;
    @Column(name = "proprietaire")
    private String proprietaire;

    public CodeEventSignart() {
    }

    public CodeEventSignart(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPrixCodeEvent() {
        return prixCodeEvent;
    }

    public void setPrixCodeEvent(Integer prixCodeEvent) {
        this.prixCodeEvent = prixCodeEvent;
    }
    public EvenementSignart getIdEvenementSignart() {
        return idEvenementSignart;
    }

    public void setIdEvenementSignart(EvenementSignart idEvenementSignart) {
        this.idEvenementSignart = idEvenementSignart;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
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
        if (!(object instanceof CodeEventSignart)) {
            return false;
        }
        CodeEventSignart other = (CodeEventSignart) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.CodeEventSignart[ id=" + id + " ]";
    }
    
}
