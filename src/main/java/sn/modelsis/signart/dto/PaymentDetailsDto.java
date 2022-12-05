package sn.modelsis.signart.dto;

public class PaymentDetailsDto {

    private Integer id;

    private String reference;

    private String destinataire;

    private String preuve;

    public PaymentDetailsDto() {
    }

    public PaymentDetailsDto(String reference, String destinataire, String preuve) {
        this.reference = reference;
        this.destinataire = destinataire;
        this.preuve = preuve;
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
}

