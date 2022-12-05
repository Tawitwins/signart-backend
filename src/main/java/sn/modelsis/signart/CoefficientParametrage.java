package sn.modelsis.signart;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CoefficientParametrage", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CoefficientParametrage.findById", query = "SELECT cp FROM CoefficientParametrage cp WHERE cp.id = :id"),
        @NamedQuery(name = "CoefficientParametrage.findByCodeParametre", query = "SELECT cp FROM CoefficientParametrage cp WHERE cp.codeParametre = :codeParametre"),
        @NamedQuery(name = "CoefficientParametrage.findByValeurParametre", query = "SELECT cp FROM CoefficientParametrage cp WHERE cp.valeurParametre = :valeurParametre"),
        @NamedQuery(name = "CoefficientParametrage.findByStatut", query = "SELECT cp FROM CoefficientParametrage cp WHERE cp.statut = :statut"),
        @NamedQuery(name = "CoefficientParametrage.findByEnumTypeParam", query = "SELECT cp FROM CoefficientParametrage cp WHERE cp.enumTypeParam = :enumTypeParam"),
})
public class CoefficientParametrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "codeParametre", length = 1000)
    private String codeParametre;
    @Column(name = "valeurParametre")
    private Integer valeurParametre;
    @Column(name = "statut", length = 1000)
    private String statut;
    @Column(name = "enumTypeParam", length = 1000)
    private String enumTypeParam;

    public CoefficientParametrage() {
    }

    public CoefficientParametrage(String enumTypeParam) {
        this.enumTypeParam = enumTypeParam;
    }

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
