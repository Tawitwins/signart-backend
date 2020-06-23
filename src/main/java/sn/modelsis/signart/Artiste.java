package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SNLOM
 */
@Entity
@Table(name = "Artiste", catalog = "signart", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Artiste.findAll", query = "SELECT a FROM Artiste a")
    , @NamedQuery(name = "Artiste.findById", query = "SELECT a FROM Artiste a WHERE a.id = :id")
    , @NamedQuery(name = "Artiste.findByNom", query = "SELECT a FROM Artiste a WHERE a.nom = :nom")
    , @NamedQuery(name = "Artiste.findByPrenom", query = "SELECT a FROM Artiste a WHERE a.prenom = :prenom")
    , @NamedQuery(name = "Artiste.findBySurnom", query = "SELECT a FROM Artiste a WHERE a.surnom = :surnom")
    , @NamedQuery(name = "Artiste.findByTelephone", query = "SELECT a FROM Artiste a WHERE a.telephone = :telephone")
    , @NamedQuery(name = "Artiste.findByAdresse", query = "SELECT a FROM Artiste a WHERE a.adresse = :adresse")
    , @NamedQuery(name = "Artiste.findByVille", query = "SELECT a FROM Artiste a WHERE a.ville = :ville")
    , @NamedQuery(name = "Artiste.findByIdBiographie", query = "SELECT a FROM Artiste a WHERE a.idBiographie.id = :idBiographie")
    , @NamedQuery(name = "Artiste.findByPhoto", query = "SELECT a FROM Artiste a WHERE a.photo = :photo")
    , @NamedQuery(name = "Artiste.findByIdentite", query = "SELECT a FROM Artiste a WHERE a.identite = :identite")
   // , @NamedQuery(name = "Artiste.updateProfil", query = "UPDATE Artiste a SET a.nom = :nom, a.prenom = :prenom, a.surnom = :surnom, a.telephone = :telephone, a.email = :email, a.adresse = :adresse, a.ville = :ville, a.idPays = :pays WHERE a.id = :id ")
    , @NamedQuery(name = "Artiste.findByProfession", query = "SELECT a FROM Artiste a WHERE a.profession = :profession")
    , @NamedQuery(name = "Artiste.findByIdUser", query = "SELECT a FROM Artiste a WHERE a.idUser.id = :idUser")})

public class Artiste implements Serializable {
   
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artiste")
    private Set<ArtisteFonction> artisteFonctionSet;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String nom;
    @Column(name = "prenom", length = 50)
    private String prenom;
    @Column(name = "surnom", length = 50)
    private String surnom;
    @Column(name = "telephone", length = 20)
    private String telephone;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "adresse", length = 200)
    private String adresse;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "biographie", length = 2000)
    private String biographie;
    @Column(name = "identite", length = 160)
    private String identite;

    @Column(name = "genre", length = 10)
    private String genre;
    @Column(name = "nomGalerie", length = 100)
    private String nomGalerie;
    @Column(name = "adrGalerie", length = 200)
    private String adrGalerie;
    @Column(name = "villeGalerie", length = 50)
    private String villeGalerie;
    @Column(name = "spécialités", length = 200)
    private String specialites;
    @Column(name = "formations", length = 2000)
    private String formation;
    @Column(name = "expositions", length = 2000)
    private String expositions;
    
    
    @Column(name = "profession", length = 200)
    private String profession;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtiste")
    private Set<Compte> compteSet;
    @JoinColumn(name = "idEtatArtiste", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EtatArtiste idEtatArtiste;
    @JoinColumn(name = "idPays", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Pays idPays;
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Utilisateur idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtiste")
    private Set<Oeuvre> oeuvreSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtiste")
    private Set<Annonce> annonceSet;
    @JoinTable(name = "Artiste_Exposition", joinColumns = {
        @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idExposition", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Exposition> expositionSet;
    @JoinTable(name = "Artiste_Formation", joinColumns = {
        @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idFormation", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Formation> formationSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtiste")
    private Set<Filmographie> filmographieSet;
    @JoinColumn(name = "idBiographie", referencedColumnName = "id")
    @ManyToOne
    private Biographie idBiographie;
    @JoinTable(name = "Artiste_Fonction", joinColumns = {
        @JoinColumn(name = "idArtiste", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idFonction", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Fonction> fonctionSet;
    
    public Artiste() {
    }

    public Artiste(String nom, String prenom, String surnom, String telephone, String email, String adresse, String ville, Pays idPays) {
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.idPays = idPays;
    }

    public Artiste(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }
    
    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
  
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getNomGalerie() {
        return nomGalerie;
    }

    public void setNomGalerie(String nomGalerie) {
        this.nomGalerie = nomGalerie;
    }

    public String getAdrGalerie() {
        return adrGalerie;
    }

    public void setAdrGalerie(String adrGalerie ) {
        this.adrGalerie = adrGalerie;
    }

    public String getVilleGalerie() {
        return villeGalerie;
    }

    public void setVilleGalerie(String villeGalerie) {
        this.villeGalerie = villeGalerie;
    }

    public String getSpecialites() {
        return specialites;
    }

    public void setSpecialites(String specialites) {
        this.specialites = specialites;
    }
    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    public String getExpositions() {
        return expositions;
    }

    public void setExpositions(String expositions) {
        this.expositions = expositions;
    }




    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @XmlTransient
    public Set<Compte> getCompteSet() {
        return compteSet;
    }

    public void setCompteSet(Set<Compte> compteSet) {
        this.compteSet = compteSet;
    }

    public EtatArtiste getIdEtatArtiste() {
        return idEtatArtiste;
    }

    public void setIdEtatArtiste(EtatArtiste idEtatArtiste) {
        this.idEtatArtiste = idEtatArtiste;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Set<Oeuvre> getOeuvreSet() {
        return oeuvreSet;
    }

    public void setOeuvreSet(Set<Oeuvre> oeuvreSet) {
        this.oeuvreSet = oeuvreSet;
    }

    @XmlTransient
    public Set<Annonce> getAnnonceSet() {
        return annonceSet;
    }

    public void setAnnonceSet(Set<Annonce> annonceSet) {
        this.annonceSet = annonceSet;
    }

    @XmlTransient
    public Set<Exposition> getExpositionSet() {
        return expositionSet;
    }

    public void setExpositionSet(Set<Exposition> expositionSet) {
        this.expositionSet = expositionSet;
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
        if (!(object instanceof Artiste)) {
            return false;
        }
        Artiste other = (Artiste) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.modelsis.signart.Artiste[ id=" + id + " ]";
    }

    /**
     * @return the formationSet
     */
    @XmlTransient
    public Set<Formation> getFormationSet() {
        return formationSet;
    }

    /**
     * @param formationSet the formationSet to set
     */
    public void setFormationSet(Set<Formation> formationSet) {
        this.formationSet = formationSet;
    }


    @XmlTransient
    public Set<Filmographie> getFilmographieSet() {
        return filmographieSet;
    }

    public void setFilmographieSet(Set<Filmographie> filmographieSet) {
        this.filmographieSet = filmographieSet;
    }

    public Biographie getIdBiographie() {
        return idBiographie;
    }

    public void setIdBiographie(Biographie idBiographie) {
        this.idBiographie = idBiographie;
    }

    /**
     * @return the fonctionSet
     */
    public Set<Fonction> getFonctionSet() {
        return fonctionSet;
    }

    /**
     * @param fonctionSet the fonctionSet to set
     */
    public void setFonctionSet(Set<Fonction> fonctionSet) {
        this.fonctionSet = fonctionSet;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * @return the artisteFonctionSet
     */
    @XmlTransient
    public Set<ArtisteFonction> getArtisteFonctionSet() {
        return artisteFonctionSet;
    }

    /**
     * @param artisteFonctionSet the artisteFonctionSet to set
     */
    public void setArtisteFonctionSet(Set<ArtisteFonction> artisteFonctionSet) {
        this.artisteFonctionSet = artisteFonctionSet;
    }

}
