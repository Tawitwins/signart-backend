package sn.modelsis.signart;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Menu", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.id = :id")
    , @NamedQuery(name = "Menu.findByPath", query = "SELECT m FROM Menu m WHERE m.path = :path")
    , @NamedQuery(name = "Menu.findByTitle", query = "SELECT m FROM Menu m WHERE m.title = :title")
    , @NamedQuery(name = "Menu.findByIcon", query = "SELECT m FROM Menu m WHERE m.icon = :icon")
    , @NamedQuery(name = "Menu.findByClasse", query = "SELECT m FROM Menu m WHERE m.classe = :classe")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "path", length = 50)
    private String path;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "icon", length = 50)
    private String icon;
    @Column(name = "classe", length = 50)
    private String classe;
@Type(type = "org.hibernate.type.TextType")
    private byte[] image;
    // @OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
    // private Technique technique;
    @Column(name = "idParent", nullable = true)
    private Integer idParent;
    @JoinTable(name = "Menu_Profil", joinColumns = {
        @JoinColumn(name = "idMenu", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idProfil", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Profil> profilSet;

    public Menu() {
    }

    public Menu(Integer id) {
        this.id = id;
    }

    public Menu(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    @XmlTransient
    public Set<Profil> getProfilSet() {
        return profilSet;
    }

    public void setProfilSet(Set<Profil> profilSet) {
        this.profilSet = profilSet;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Menu[ id=" + id + " ]";
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }
    
}
