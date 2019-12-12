package sn.modelsis.signart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author SNNGOM
 */
public class OeuvreSouscriptionDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private byte[] image;
    private String description;
    private BigDecimal prix;
    

    public OeuvreSouscriptionDto() {
    }

    public OeuvreSouscriptionDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
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
        if (!(object instanceof OeuvreSouscriptionDto)) {
            return false;
        }
        OeuvreSouscriptionDto other = (OeuvreSouscriptionDto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.OeuvreSouscription[ id=" + id + " ]";
    }
    
}
