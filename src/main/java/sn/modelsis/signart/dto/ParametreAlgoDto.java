package sn.modelsis.signart.dto;

/**
 *
 * @author SNMBENGUO
 */
public class ParametreAlgoDto {
    private Integer id;
    private String niveau;
    private String libelle;
    private Integer note;
    private Integer baseNote;
    private Integer pourcentReduction;
    private float borneSup;
    private float borneInf;

    private Integer idCoefficientParametrage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getBaseNote() {
        return baseNote;
    }

    public void setBaseNote(Integer baseNote) {
        this.baseNote = baseNote;
    }

    public Integer getPourcentReduction() {
        return pourcentReduction;
    }

    public void setPourcentReduction(Integer pourcentReduction) {
        this.pourcentReduction = pourcentReduction;
    }
    public float getBorneSup() {
        return borneSup;
    }

    public void setBorneSup(float borneSup) {
        this.borneSup = borneSup;
    }

    public float getBorneInf() {
        return borneInf;
    }

    public void setBorneInf(float borneInf) {
        this.borneInf = borneInf;
    public Integer getIdCoefficientParametrage() {
        return idCoefficientParametrage;
    }

    public void setIdCoefficientParametrage(Integer idCoefficientParametrage) {
        this.idCoefficientParametrage = idCoefficientParametrage;
    }

    public ParametreAlgoDto() {
    }

    public ParametreAlgoDto(Integer id, String niveau, String libelle, Integer note, Integer baseNote, Integer pourcentReduction) {
        this.id = id;
        this.niveau = niveau;
        this.libelle = libelle;
        this.note = note;
        this.baseNote = baseNote;
        this.pourcentReduction = pourcentReduction;
    }



}
