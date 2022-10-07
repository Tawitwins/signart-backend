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
@Table(name = "ServiceLivraison", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ServiceLivraison.findAll", query = "SELECT s FROM ServiceLivraison s")
    , @NamedQuery(name = "ServiceLivraison.findById", query = "SELECT s FROM ServiceLivraison s WHERE s.id = :id")
    , @NamedQuery(name = "ServiceLivraison.findByNom", query = "SELECT s FROM ServiceLivraison s WHERE s.nom = :nom")
})

public class ServiceLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "adresse", length = 200)
    private String adresse;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceLivraison")
    private Set<Agent> AgentSet;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceLivraison")
    private Set<Tarification> TarificationSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceLivraison")
    private Set<Commande> CommandeSet;




    public ServiceLivraison() {
    }

    public ServiceLivraison(String nom, String telephone, String email, String adresse) {
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public ServiceLivraison(Integer id) {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Tarification> getTarificationSet() {
        return TarificationSet;
    }

    public void setTarificationSet(Set<Tarification> tarificationSet) {
        TarificationSet = tarificationSet;
    }

    public Set<Agent> getAgentSet() {
        return AgentSet;
    }

    public void setAgentSet(Set<Agent> agentSet) {
        AgentSet = agentSet;
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
        if (!(object instanceof ServiceLivraison)) {
            return false;
        }
        ServiceLivraison other = (ServiceLivraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.ServiceLivraison[ id=" + id + " ]";
    }


}
