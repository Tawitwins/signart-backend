/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author snfayemp
 */

@Entity
@Table(name = "ImageMiniature", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ImageMiniature.findAll", query = "SELECT i FROM ImageMiniature i")
   ,@NamedQuery(name = "ImageMiniature.findByName", query = "SELECT i FROM ImageMiniature i WHERE i.nomImage = :nomImage")})
public class ImageMiniature implements Serializable {
    
     private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nomImage", length = 500)
    private String nomImage ;
@Type(type = "org.hibernate.type.TextType")
    private byte[] valeurImage;

    public ImageMiniature() {
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

    public byte[] getValeurImage() {
        return valeurImage;
    }

    public void setValeurImage(byte[] valeurImage) {
        this.valeurImage = valeurImage;
    }
    
    
}
