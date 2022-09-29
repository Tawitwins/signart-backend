/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import org.apache.james.mime4j.field.datetime.DateTime;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */
@Entity
@Table(name = "Abonnement", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Abonnement.findAll", query = "SELECT a FROM Abonnement a")
    , @NamedQuery(name = "Abonnement.findById", query = "SELECT a FROM Abonnement a WHERE a.id = :id")
    , @NamedQuery(name = "Abonnement.findByIdAbonne", query = "SELECT a FROM Abonnement a WHERE a.idAbonne.id = :idAbonne")
    , @NamedQuery(name = "Abonnement.findAllByIdAbonne", query = "SELECT a FROM Abonnement a WHERE a.idAbonne.id = :idAbonne")
    , @NamedQuery(name = "Abonnement.findByTokenPaiement", query = "SELECT a FROM Abonnement a WHERE a.tokenPaiement = :tokenPaiement")
        , @NamedQuery(name = "Abonnement.findByReabonne", query = "SELECT a FROM Abonnement a WHERE a.reabonne = :reabonne")
})

public class Abonnement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "idAbonne", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Abonne idAbonne;
    @JoinColumn(name = "idTerminal", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal idTerminal;
    @JoinColumn(name = "idDelai", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Delai idDelai;
    @JoinColumn(name = "idListeSelection", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ListeSelection idListeSelection;
    @Column(name = "montantPaiement", nullable = false)
    private Integer montantPaiement;
    @Column(name = "precisions", nullable = true, length = 2000)
    private String precisions;
    @JoinColumn(name = "etatAbonnement", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatAbonnement etatAbonnement;
    @JoinColumn(name = "idModePaiement", referencedColumnName = "id")
    @ManyToOne
    private ModePaiement idModePaiement;
    @Column(name = "tokenPaiement", length = 200)
    private String tokenPaiement;


    @Basic(optional = false)
    @Column(name = "dateCreation", nullable = false)
    private Date dateCreation;
    @Column(name = "dateDebut", nullable = false)
    private Date dateDebut;

    @JoinColumn(name = "idDetailPayment", referencedColumnName = "id")
    @ManyToOne
    private PaymentDetails idDetailPayment;

   
    @Column(name = "reabonne")
    private Boolean reabonne;

    public Abonnement() {
    }

    public Abonnement(Integer id, Abonne idAbonne, Terminal idTerminal, Delai idDelai, ListeSelection idListeSelection, Integer montantPaiement, EtatAbonnement etatAbonnement) {
        this.id = id;
        this.idAbonne = idAbonne;
        this.idTerminal = idTerminal;
        this.idDelai = idDelai;
        this.idListeSelection = idListeSelection;
        this.montantPaiement = montantPaiement;
        this.etatAbonnement = etatAbonnement;
    }

    public Abonnement(Abonne idAbonne, Terminal idTerminal, Delai idDelai, ListeSelection idListeSelection, Integer montantPaiement, EtatAbonnement etatAbonnement) {
        this.idAbonne = idAbonne;
        this.idTerminal = idTerminal;
        this.idDelai = idDelai;
        this.idListeSelection = idListeSelection;
        this.montantPaiement = montantPaiement;
        this.etatAbonnement = etatAbonnement;
    }

    public Abonne getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(Abonne idAbonne) {
        this.idAbonne = idAbonne;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Terminal getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(Terminal idTerminal) {
        this.idTerminal = idTerminal;
    }

    public Delai getIdDelai() {
        return idDelai;
    }

    public void setIdDelai(Delai idDelai) {
        this.idDelai = idDelai;
    }

    public ListeSelection getIdListeSelection() {
        return idListeSelection;
    }

    public void setIdListeSelection(ListeSelection idListeSelection) {
        this.idListeSelection = idListeSelection;
    }

    public Integer getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(Integer montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public EtatAbonnement getEtatAbonnement() {
        return etatAbonnement;
    }

    public void setEtatAbonnement(EtatAbonnement etatAbonnement) {
        this.etatAbonnement = etatAbonnement;
    }
    public ModePaiement getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(ModePaiement idModePaiement) {
        this.idModePaiement = idModePaiement;
    }
    public String getTokenPaiement() {
        return tokenPaiement;
    }

    public void setTokenPaiement(String tokenPaiement) {
        this.tokenPaiement = tokenPaiement;
    }

    public Date getDateCréation() {
        return dateCreation;
    }

    public void setDateCréation(Date dateCréation) {
        this.dateCreation = dateCréation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public PaymentDetails getIdDetailPayment() {
        return idDetailPayment;
    }

    public void setIdDetailPayment(PaymentDetails idDetailPayment) {
        this.idDetailPayment = idDetailPayment;
    public Boolean getReabonne() {
        return reabonne;
    }

    public void setReabonne(Boolean reabonne) {
        this.reabonne = reabonne;
    }
}
