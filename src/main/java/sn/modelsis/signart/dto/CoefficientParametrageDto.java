package sn.modelsis.signart.dto;

public class CoefficientParametrageDto {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String codeParametre;
    private Integer valeurParametre;
    private String statut;
    private String enumTypeParam;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeParametre() {
        return codeParametre;
    }

    public void setCodeParametre(String codeParametre) {
        this.codeParametre = codeParametre;
    }

    public Integer getValeurParametre() {
        return valeurParametre;
    }

    public void setValeurParametre(Integer valeurParametre) {
        this.valeurParametre = valeurParametre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEnumTypeParam() {
        return enumTypeParam;
    }

    public void setEnumTypeParam(String enumTypeParam) {
        this.enumTypeParam = enumTypeParam;
    }
}
