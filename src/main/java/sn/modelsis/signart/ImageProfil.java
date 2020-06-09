/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author snfayemp
 */
@Entity
@Table(name = "ImageProfil", catalog = "signart", schema = "dbo")
@NamedQueries({
               @NamedQuery(name = "ImageProfil.findByName", query = "SELECT i FROM ImageProfil i WHERE i.nom = :nom")
              ,@NamedQuery(name = "ImageProfil.findById", query = "SELECT i FROM ImageProfil i WHERE i.id = :id")})
public class ImageProfil implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "type")
    private String type;
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;
    

    public ImageProfil() {
        super();
    }

    public ImageProfil(String nom, String type, byte[] picByte) {
        this.nom = nom;
        this.type = type;
        this.picByte = picByte;
    }
    
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getType() {
        return type;
    }
   public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
     this.picByte = picByte;
    }
    
}
