package sn.modelsis.signart.dto;

/**
 *
 * @author SNLOM
 */
public class VisiteurDto {
    private Integer id;
    private String nom;
    private String prenom;
    private PaysDto Pays;
    private Integer idPays;
    private String raisonsociale;
    private String typevisiteur;

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

    public String getPrenom() {
        return prenom;
    }
    
    public void setRaisonSociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public String getRaisonSociale() {
        return raisonsociale;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public PaysDto getPays() {
        return Pays;
    }

    public void setPays(PaysDto Pays) {
        this.Pays = Pays;
    }
    
    public void setTypeVisiteur(String typevisiteur) {
        this.typevisiteur = typevisiteur;
    }

    public String getTypeVisiteur() {
        return typevisiteur;
    }

    public int getIdPays() {
        return this.idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }
}
