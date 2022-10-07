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
    @Column(name = "valeurParametre", length = 1000)
    private String valeurParametre;
    @Column(name = "statut", length = 1000)
    private String statut;
    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private EnumTypeParam enumTypeParam;

    public CoefficientParametrage() {
    }

    public CoefficientParametrage(EnumTypeParam enumTypeParam) {
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

    public String getValeurParametre() {
        return valeurParametre;
    }

    public void setValeurParametre(String valeurParametre) {
        this.valeurParametre = valeurParametre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public EnumTypeParam getEnumTypeParam() {
        return enumTypeParam;
    }

    public void setEnumTypeParam(EnumTypeParam enumTypeParam) {
        this.enumTypeParam = enumTypeParam;
    }
}
