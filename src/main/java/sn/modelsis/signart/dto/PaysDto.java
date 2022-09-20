package sn.modelsis.signart.dto;

public class PaysDto {

    private Integer id;
    private String code;
    private String libelle;
    private String indicatif;

    public Integer getId() {
        return id;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }
    
    

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}