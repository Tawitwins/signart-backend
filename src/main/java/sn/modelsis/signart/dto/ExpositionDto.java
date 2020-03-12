package sn.modelsis.signart.dto;

import java.util.Date;

/**
 *
 * @author SNLOM
 */
public class ExpositionDto {
    private Integer id;
    private String adresse;
    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String type;
    private Integer idArtiste;
    private Boolean etatExposition;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Boolean getEtatExposition() {
        return etatExposition;
    }

    public void setEtatExposition(Boolean etatExposition) {
        this.etatExposition = etatExposition;
    }
    
    
    

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
