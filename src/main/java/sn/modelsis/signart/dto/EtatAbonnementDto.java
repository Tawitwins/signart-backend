/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author Pendaaa
 */
public class EtatAbonnementDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String libelle;
    private String code;
    private String description;
    
     public EtatAbonnementDto() {
    }

    public EtatAbonnementDto(Integer id, String libelle, String description, String code) {
        this.id = id;
        this.libelle = libelle;
        this.code = code;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
    
    
}
