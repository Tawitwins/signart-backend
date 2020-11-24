package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class BiographieDto {
    private Integer id;
    private Date dateNaissance;
    private String lieuNaissance;
    private String nationalite;
    private String libelle;
    private Boolean etatBiographie;
    private Integer idArtiste;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @return the dateNaissance
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


    /**
     * @return the lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * @param lieuNaissance the lieuNaissance to set
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }
    
    

    /**
     * @return the nationalite
     */
    public String getNationalite() {
        return nationalite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getEtatBiographie() {
        return etatBiographie;
    }

    public void setEtatBiographie(Boolean etatBiographie) {
        this.etatBiographie = etatBiographie;
    }
    

    /**
     * @param nationalite the nationalite to set
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

}
