package sn.modelsis.signart.dto;
/**
 * @author SNNGOMN
 */
public class SousTechniqueDto {

    private Integer id;
    private String libelle;
    private String icone;
    private Integer idTechnique;

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

    public Integer getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(Integer idTechnique) {
        this.idTechnique = idTechnique;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}