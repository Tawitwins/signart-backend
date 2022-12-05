package sn.modelsis.signart.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author SNLOM
 */
public class ParametrageDto {
    private String paramName;
    private String value;

    public ParametrageDto() {
    }

    public ParametrageDto(String paramName, String value, Boolean statut) {
        this.paramName = paramName;
        this.value = value;
        this.statut = statut;
    }

    private Boolean statut;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }
}
