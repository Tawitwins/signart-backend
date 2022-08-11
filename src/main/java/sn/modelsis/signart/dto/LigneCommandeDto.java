package sn.modelsis.signart.dto;

import java.math.BigDecimal;

/**
 *
 * @author SNLOM
 */
public class LigneCommandeDto {

    private Integer id;
    private int quantite;
    private BigDecimal prix;
    private OeuvreDto oeuvre;
    private BigDecimal total;
    private Integer idClient;

    public String getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(String numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    private String numeroCommande;

    private Integer idEtatLigneCommande;

    private String etatLigneCommande;
    public String getEtatLigneCommande() {
        return etatLigneCommande;
    }

    public void setEtatLigneCommande(String etatLigneCommande) {
        this.etatLigneCommande = etatLigneCommande;
    }




    public Integer getIdEtatLigneCommande() {
        return idEtatLigneCommande;
    }

    public void setIdEtatLigneCommande(Integer idEtatLigneCommande) {
        this.idEtatLigneCommande = idEtatLigneCommande;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    private Integer idCommande;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public OeuvreDto getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(OeuvreDto oeuvre) {
        this.oeuvre = oeuvre;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    
}
