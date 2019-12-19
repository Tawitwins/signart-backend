package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class ArtisteFormationDto {
    private Integer id;
    private String sigle;
    private String libelle;
    private String lieu;
    private Integer anneeDebut;
    private Integer anneeFin;
    private String specialisation;

    public Integer getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Integer anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Integer getAnneeFin() {
        return anneeFin;
    }
    //
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAnneeFin(Integer anneeFin) {
        this.anneeFin = anneeFin;
    } 

    /**
     * @return the sigle
     */
    public String getSigle() {
        return sigle;
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

    /**
     * @return the specialisation
     */
    public String getSpecialisation() {
        return specialisation;
    }

    /**
     * @param specialisation the specialisation to set
     */
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
