package sn.modelsis.signart.dto;

public class ModePaiementDto {
    private Integer id;
    private String libelle;
    private String code;

    private boolean isAdminMode;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public boolean isAdminMode() {
        return isAdminMode;
    }

    public void setAdminMode(boolean adminMode) {
        isAdminMode = adminMode;
    }

}