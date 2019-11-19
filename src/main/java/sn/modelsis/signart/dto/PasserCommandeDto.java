package sn.modelsis.signart.dto;

import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class PasserCommandeDto {
    private Integer id;
    private String numero;
    private Integer idDevise;
    private Integer idClient;
    private String token;
    private Set<LigneCommandeDto> lignesCommande;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(Integer idDevise) {
        this.idDevise = idDevise;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<LigneCommandeDto> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(Set<LigneCommandeDto> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

}
