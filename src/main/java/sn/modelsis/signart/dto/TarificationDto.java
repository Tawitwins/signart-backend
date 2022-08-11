package sn.modelsis.signart.dto;

import java.io.Serializable;

/**
 *
 * @author SNMBENGUEO
 */
public class TarificationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer delaiLivraison;
    private Integer fraisLivraison;
    private Integer fraisAssurance;
    private String zone;
    private Integer idServiceLivraison;


    public TarificationDto(Integer id, Integer delaiLivraison, Integer fraisAssurance, Integer fraisLivraison, String zone) {
        this.id = id;
        this.delaiLivraison = delaiLivraison;
        this.fraisAssurance = fraisAssurance;
        this.fraisLivraison = fraisAssurance;
        this.zone = zone;
    }



    public TarificationDto() {
    }

    public TarificationDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(Integer delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public Integer getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(Integer fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public Integer getFraisAssurance() {
        return fraisAssurance;
    }

    public void setFraisAssurance(Integer fraisAssurance) {
        this.fraisAssurance = fraisAssurance;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getIdServiceLivraison() {
        return idServiceLivraison;
    }

    public void setIdServiceLivraison(Integer idServiceLivraison) {
        this.idServiceLivraison = idServiceLivraison;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarificationDto)) {
            return false;
        }
        TarificationDto other = (TarificationDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.TarificationDto[ id=" + id + " ]";
    }
    
}


