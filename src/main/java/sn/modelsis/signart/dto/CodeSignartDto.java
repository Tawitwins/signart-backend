/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author snfayemp
 */
public class CodeSignartDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String keyValue;
    private String keyAlias;
    private Integer idAbonnement;

    public CodeSignartDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public Integer getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.idAbonnement = idAbonnement;
    }
    
    
    
}
