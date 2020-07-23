/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author snfayemp
 */
@Entity
@Table(name = "HistoriqueAbonnement", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "HistoriqueAbonnement.findAllByIdUtilisateur", query = "SELECT h FROM HistoriqueAbonnement h WHERE h.idUtilisateur.id = :idUtilisateur")
    , @NamedQuery(name = "HistoriqueAbonnement.findById", query = "SELECT h FROM HistoriqueAbonnement h WHERE h.id = :id")
    , @NamedQuery(name = "HistoriqueAbonnement.findByIdAbonnement", query = "SELECT h FROM HistoriqueAbonnement h WHERE h.idAbonnement.id = :idAbonnement")})
public class HistoriqueAbonnement implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Column(name = "libelle",length = 1000)
    private String libelle;
    @JoinColumn(name = "idAbonnement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Abonnement idAbonnement;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public HistoriqueAbonnement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Abonnement getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Abonnement idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
    
}
