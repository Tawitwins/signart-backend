/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.dto;

/**
 *
 * @author Pendaaa
 */
public class PresentationDto {
    
    private Integer id;
    private String libelle;
    private String videoId;
    private Integer idArtiste;
    private Boolean etatPubPresentation;

    public PresentationDto() {
    }

    public PresentationDto(Integer id, String libelle, String videoId, Integer idArtiste) {
        this.id = id;
        this.libelle = libelle;
        this.videoId = videoId;
        this.idArtiste = idArtiste;
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Boolean getEtatPubPresentation() {
        return etatPubPresentation;
    }

    public void setEtatPubPresentation(Boolean etatPubPresentation) {
        this.etatPubPresentation = etatPubPresentation;
    }
    
    
    
    
    
}
