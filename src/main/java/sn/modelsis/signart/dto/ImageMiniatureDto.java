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
public class ImageMiniatureDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nomImage;
    private String valeurImage;

    public ImageMiniatureDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getValeurImage() {
        return valeurImage;
    }

    public void setValeurImage(String valeurImage) {
        this.valeurImage = valeurImage;
    }
    
    
    
}
