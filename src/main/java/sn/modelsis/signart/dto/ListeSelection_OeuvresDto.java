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
public class ListeSelection_OeuvresDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer idListe;
    private String nomOeuvre;

    public ListeSelection_OeuvresDto() {
    }

    public ListeSelection_OeuvresDto(Integer id, Integer idListe, String nomOeuvre) {
        this.id = id;
        this.idListe = idListe;
        this.nomOeuvre = nomOeuvre;
    }
    
    public ListeSelection_OeuvresDto(Integer idListe, String nomOeuvre) {
        this.idListe = idListe;
        this.nomOeuvre = nomOeuvre;
    }


    public Integer getIdListe() {
        return idListe;
    }

    public void setIdListe(Integer idListe) {
        this.idListe = idListe;
    }

    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }

   
    
    

   
    
    
}
