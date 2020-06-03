/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

/**
 *
 * @author snfayemp
 */
public class ImageProfilDto {
    
    private String filename;
    private String filetype;
    private String value;

    public ImageProfilDto() {
    }

    public ImageProfilDto(String filename, String filetype, String value) {
        this.filename = filename;
        this.filetype = filetype;
        this.value = value;
    }
    
    

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    
    
    
}
