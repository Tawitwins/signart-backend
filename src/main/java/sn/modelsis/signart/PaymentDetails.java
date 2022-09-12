package sn.modelsis.signart;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PaymentDetails", catalog = "signart", schema = "dbo")
@NamedQueries({
        @NamedQuery(name = "PaymentDetails.findAll", query = "SELECT p FROM PaymentDetails p"),
        @NamedQuery(name = "PaymentDetails.findById", query = "SELECT p FROM PaymentDetails p WHERE p.id = :id"),
        @NamedQuery(name = "PaymentDetails.findByReference", query = "SELECT p FROM PaymentDetails p WHERE p.reference = :reference"),
        @NamedQuery(name = "PaymentDetails.findByPreuve", query = "SELECT p FROM PaymentDetails p WHERE p.preuve = :preuve"),
        @NamedQuery(name = "PaymentDetails.findByDestinataire", query = "SELECT p FROM PaymentDetails p WHERE p.destinataire = :destinataire")})
public class PaymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "reference", nullable = false)
    private String reference;

    @Basic(optional = false)
    @Column(name = "destinataire", nullable = false)
    private String destinataire;

    @Basic(optional = false)
    @Column(name = "preuve", nullable = false)
    private String preuve;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaymentDetails")
    private Set<LignePaiement> lignePaiementSet;

    public PaymentDetails() {
    }

    public PaymentDetails(String reference, String destinataire, String preuve, Set<LignePaiement> lignePaiementSet) {
        this.reference = reference;
        this.destinataire = destinataire;
        this.preuve = preuve;
        this.lignePaiementSet = lignePaiementSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getPreuve() {
        return preuve;
    }

    public void setPreuve(String preuve) {
        this.preuve = preuve;
    }

    public Set<LignePaiement> getLignePaiementSet() {
        return lignePaiementSet;
    }

    public void setLignePaiementSet(Set<LignePaiement> lignePaiementSet) {
        this.lignePaiementSet = lignePaiementSet;
    }
}
