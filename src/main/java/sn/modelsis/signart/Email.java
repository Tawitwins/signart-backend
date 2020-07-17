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

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Email", catalog = "signart", schema = "dbo")
@NamedQueries({
    //@NamedQuery(name = "Visiteur.findClientArtiste", query = "SELECT cl FROM visisteur cl Join cl.commandeSet cm Join cm.ligneCommandeSet lc WHERE lc.idOeuvre.idArtiste.id = :idArtiste")
      @NamedQuery(name = "Email.findAll", query = "SELECT c FROM Email c")
    , @NamedQuery(name = "Email.findById", query = "SELECT c FROM Email c WHERE c.id = :id")
    , @NamedQuery(name = "Email.findByTo", query = "SELECT c FROM Email c WHERE c.to = :to")
//    , @NamedQuery(name = "Email.findByPrenom", query = "SELECT c FROM Email c WHERE c.prenom = :prenom")
//    , @NamedQuery(name = "Email.findByRaisonSociale", query = "SELECT c FROM Email c WHERE c.raisonsociale = :raisonsociale")
    , /*@NamedQuery(name = "Visiteur.findByPays", query = "SELECT c FROM Visiteur c WHERE c.pays = :pays")*/})

/**
 *
 * @author SNMBENGUEO
 */
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "objet", length = 200)
    private String objet;
    @Column(name = "receiver", length = 50)
    private String to;
    @Column(name = "content", length = 5000)
    private String content;
    @Column(name = "pj", length = 100)
    private byte[] pj;
    @Column(name = "dateEnvoi", nullable = true)
    private Date dateEnvoi;
    


    public Email() {
    }

    public Email(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public byte[] getPj() {
        return pj;
    }

    public void setPj(byte[] pj) {
        this.pj = pj;
    }

    public Date getDateEnvoi() {
        return this.dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
    /*public Integer getIdPays() {
        return this.Pays.getId();
    }

    public void setIdPays() {
        this.idPays = this.Pays.getId();
    }*/



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Email[ id=" + id + " ]";
    }
}
