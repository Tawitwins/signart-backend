package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class FormationDto {
     private Integer id;
    private String sigle;
    private String libelle;
    private String lieu;
    private Integer idArtiste;
    private Boolean etatPublication;
    private String specialisation;
    private Integer anneeDebut;
    private Integer anneeFin;

    public FormationDto(Integer id, String sigle, String libelle, String lieu, Boolean etatPublication, Integer idArtiste) {
        this.id = id;
        this.sigle = sigle;
        this.libelle = libelle;
        this.lieu = lieu;
        this.idArtiste = idArtiste;
        this.etatPublication = etatPublication;
    }

    public FormationDto(Integer id, String sigle, String libelle, String lieu, Integer idArtiste, Boolean etatPublication, String specialisation, Integer anneeDebut, Integer anneeFin) {
        this.id = id;
        this.sigle = sigle;
        this.libelle = libelle;
        this.lieu = lieu;
        this.idArtiste = idArtiste;
        this.etatPublication = etatPublication;
        this.specialisation = specialisation;
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeFin;
    }

    public FormationDto(String sigle, String libelle, String lieu, Integer idArtiste, Boolean etatPublication, String specialisation, Integer anneeDebut, Integer anneeFin) {
        this.sigle = sigle;
        this.libelle = libelle;
        this.lieu = lieu;
        this.idArtiste = idArtiste;
        this.etatPublication = etatPublication;
        this.specialisation = specialisation;
        this.anneeDebut = anneeDebut;
        this.anneeFin = anneeFin;
    }
    
    

    public FormationDto() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the sigle
     */
    public String getSigle() {
        return sigle;
    }

    public Boolean getEtatPublication() {
        return etatPublication;
    }

    public void setEtatPublication(Boolean etatPublication) {
        this.etatPublication = etatPublication;
    }

   

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public Integer getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Integer anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Integer getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Integer anneeFin) {
        this.anneeFin = anneeFin;
    }
    
    
    

    /**
     * @param sigle the sigle to set
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }
    
    /**
     * @return the lieu
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * @param lieu the lieu to set
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

}
