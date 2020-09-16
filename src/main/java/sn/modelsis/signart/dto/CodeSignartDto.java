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
    private String code;
    private Integer idLicence;
    private Integer idAbonne;

    public CodeSignartDto() {
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

    public Integer getIdLicence() {
        return idLicence;
    }

    public void setIdLicence(Integer idLicence) {
        this.idLicence = idLicence;
    }

    public Integer getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(Integer idAbonne) {
        this.idAbonne = idAbonne;
    }


    
    
}
