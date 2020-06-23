/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.penda.signart.dto;

import java.io.Serializable;

/**
 *
 * @author Pendaaa
 */
public class LicenceDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String valeur;
    private Integer idAbonnement;

    public LicenceDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Integer getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.idAbonnement = idAbonnement;
    }
    
    
    
}
