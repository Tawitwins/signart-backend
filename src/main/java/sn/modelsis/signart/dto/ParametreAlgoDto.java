package sn.modelsis.signart.dto;

/**
 *
 * @author SNMBENGUO
 */
public class ParametreAlgoDto {
    private Integer id;
    private String niveau;
    private String libelle;
    private String note;
    private Integer baseNote;
    private Integer pourcentReduction;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
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


    public ParametreAlgoDto() {
    }

    public ParametreAlgoDto(Integer id, String niveau, String libelle, String note, Integer baseNote, Integer pourcentReduction) {
        this.id = id;
        this.niveau = niveau;
        this.libelle = libelle;
        this.note = note;
        this.baseNote = baseNote;
        this.pourcentReduction = pourcentReduction;
    }



}
