/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */

@Entity
@Table(name = "ImageNumerique", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "ImageNumerique.findAll", query = "SELECT i FROM ImageNumerique i")
    , @NamedQuery(name = "ImageNumerique.findById", query = "SELECT i FROM ImageNumerique i WHERE i.id = :id")
    , @NamedQuery(name = "ImageNumerique.findByValue", query = "SELECT i FROM ImageNumerique i WHERE i.value = :value")})
public class ImageNumerique implements Serializable{
        private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "filename", nullable = false)
    private String filename;
    @Column(name = "filetype", nullable = false)
    private String filetype;
    @Column(name = "value", nullable = false)
    private String value;

    public ImageNumerique() {
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
