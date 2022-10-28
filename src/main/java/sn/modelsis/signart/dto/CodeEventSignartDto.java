/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import sn.modelsis.signart.EvenementSignart;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SNMBENGUEO
 */
public class CodeEventSignartDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    private Date dateCreation;
    private Date dateOfficielle;
    private Integer idEvenementSignart;
    private Boolean status;
    private String code;

    private Integer prixCodeEvent;
    private String proprietaire;


    public CodeEventSignartDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateOfficielle() {
        return dateOfficielle;
    }

    public void setDateOfficielle(Date dateOfficielle) {
        this.dateOfficielle = dateOfficielle;
    }

    public Integer getIdEvenementSignart() {
        return idEvenementSignart;
    }

    public void setIdEvenementSignart(Integer idEvenementSignart) {
        this.idEvenementSignart = idEvenementSignart;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPrixCodeEvent() {
        return prixCodeEvent;
    }

    public void setPrixCodeEvent(Integer prixCodeEvent) {
        this.prixCodeEvent = prixCodeEvent;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }





}
