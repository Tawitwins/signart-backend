package sn.modelsis.signart;

import java.io.Serializable;
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
@Table(name = "Visiteur", catalog = "signart", schema = "dbo")
@NamedQueries({
    //@NamedQuery(name = "Visiteur.findClientArtiste", query = "SELECT cl FROM visisteur cl Join cl.commandeSet cm Join cm.ligneCommandeSet lc WHERE lc.idOeuvre.idArtiste.id = :idArtiste")
      @NamedQuery(name = "Visiteur.findAll", query = "SELECT c FROM Visiteur c")
    , @NamedQuery(name = "Visiteur.findById", query = "SELECT c FROM Visiteur c WHERE c.id = :id")
    , @NamedQuery(name = "Visiteur.findByNom", query = "SELECT c FROM Visiteur c WHERE c.nom = :nom")
    , @NamedQuery(name = "Visiteur.findByPrenom", query = "SELECT c FROM Visiteur c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Visiteur.findByRaisonSociale", query = "SELECT c FROM Visiteur c WHERE c.raisonsociale = :raisonsociale")
    , /*@NamedQuery(name = "Visiteur.findByPays", query = "SELECT c FROM Visiteur c WHERE c.pays = :pays")*/})

/**
 *
 * @author SNMBENGUEO
 */
public class Visiteur implements Serializable {
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
    @Column(name = "raisonsociale", length = 50)
    private String raisonsociale;
    @Column(name = "typevisiteur", length = 50)
    private String typevisiteur;
    
    //@Column(name = "pays", length = 50)
    //private Integer idPays;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Pays", referencedColumnName = "Id", nullable = false)
    private Pays Pays;


    public Visiteur() {
    }

    public Visiteur(Integer id) {
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
    
    public String getRaisonSociale() {
        return raisonsociale;
    }

    public void setRaisonSociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }
    
    public String getTypeVisiteur() {
        return typevisiteur;
    }

    public void setTypeVisiteur(String typevisiteur) {
        this.typevisiteur = typevisiteur;
    }

    public Pays getPays() {
        return Pays;
    }

    public void setPays(Pays Pays) {
        this.Pays = Pays;
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
        if (!(object instanceof Visiteur)) {
            return false;
        }
        Visiteur other = (Visiteur) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Visiteur[ id=" + id + " ]";
    }
}
