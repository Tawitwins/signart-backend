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
public class TerminalDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String libelle;
    private String description;
    private Integer prix;

    private String code;
    public TerminalDto() {
    }

    
    public TerminalDto(Integer id, String libelle, String description, Integer prix) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
    }

    public TerminalDto(String libelle, String description, Integer prix) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
