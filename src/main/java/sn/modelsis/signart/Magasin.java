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
@Table(name = "Magasin", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m")
    , @NamedQuery(name = "Magasin.findById", query = "SELECT m FROM Magasin m WHERE m.id = :id")
    , @NamedQuery(name = "Magasin.findByNom", query = "SELECT m FROM Magasin m WHERE m.nom = :nom")
})

public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "nomResp", length = 50)
    private String nomResp;
    @Column(name = "telephoneResp", length = 20)
    private String telephoneResp;
    @Column(name = "emailResp", length = 50)
    private String emailResp;
    @Column(name = "adresse", length = 200)
    private String adresse;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMagasin")
    private Set<Agent> AgentSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMagasin")
    private Set<Oeuvre> OeuvreSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMagasin")
    private Set<Commande> CommandeSet;




    public Magasin() {
    }

    public Magasin(String nom, String nomResp, String telephoneResp, String emailResp, String adresse) {
        this.nom = nom;
        this.nomResp = nomResp;
        this.telephoneResp = telephoneResp;
        this.emailResp = emailResp;
        this.adresse = adresse;
    }

    public Magasin(Integer id) {
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

    public String getNomResp() {
        return nomResp;
    }

    public void setNomResp(String nomResp) {
        this.nomResp = nomResp;
    }

    public String getTelephoneResp() {
        return telephoneResp;
    }

    public void setTelephoneResp(String telephoneResp) {
        this.telephoneResp = telephoneResp;
    }

    public String getEmailResp() {
        return emailResp;
    }

    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<Agent> getAgentSet() {
        return AgentSet;
    }

    public void setAgentSet(Set<Agent> agentSet) {
        AgentSet = agentSet;
    }

    public Set<Oeuvre> getOeuvreSet() {
        return OeuvreSet;
    }

    public void setOeuvreSet(Set<Oeuvre> oeuvreSet) {
        OeuvreSet = oeuvreSet;
    }

    public Set<Commande> getCommandeSet() {
        return CommandeSet;
    }

    public void setCommandeSet(Set<Commande> commandeSet) {
        CommandeSet = commandeSet;
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
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Magasin[ id=" + id + " ]";
    }


}
