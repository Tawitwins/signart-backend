package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SNMBENGUEO
 */
public class EvenementSignartDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String titre;
    private String description;
    private Date dateCreation;
    private Date dateOfficielle;
    private Integer idArtiste;
    private Boolean status;
    private String lienVideo;
    private String codeEvenement;
    private Integer prixCodeEvent;
    private Integer nbrCodeAchete;
    private String lieu;
    private String contact;
    private String  responsable;
    private Integer idUtilisateur;

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


    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateOfficielle() {
        return dateOfficielle;
    }

    public void setDateOfficielle(Date dateOfficielle) {
        this.dateOfficielle = dateOfficielle;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLienVideo() {
        return lienVideo;
    }

    public void setLienVideo(String lienVideo) {
        this.lienVideo = lienVideo;
    }

    public String getCodeEvenement() {
        return codeEvenement;
    }

    public void setCodeEvenement(String codeEvenement) {
        this.codeEvenement = codeEvenement;
    }

    public Integer getPrixCodeEvent() {
        return prixCodeEvent;
    }

    public void setPrixCodeEvent(Integer prixCodeEvent) {
        this.prixCodeEvent = prixCodeEvent;
    }

    public Integer getNbrCodeAchete() {
        return nbrCodeAchete;
    }

    public void setNbrCodeAchete(Integer nbrCodeAchete) {
        this.nbrCodeAchete = nbrCodeAchete;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

   }
