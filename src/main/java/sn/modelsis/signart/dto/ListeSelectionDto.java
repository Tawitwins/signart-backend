/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import sn.modelsis.signart.OeuvreNumerique;

/**
 *
 * @author Pendaaa
 */
public class ListeSelectionDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
     private Integer id;
    private Integer idUtilisateur;

    public ListeSelectionDto() {
    }

    public ListeSelectionDto(Integer id, Integer idUtilisateur) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
    }

    public ListeSelectionDto(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    
}
