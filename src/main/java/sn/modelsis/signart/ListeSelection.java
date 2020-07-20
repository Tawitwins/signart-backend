/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pendaaa
 */

@Entity
@Table(name = "ListeSelection", catalog = "signart", schema = "dbo")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "ListeSelection.findAll", query = "SELECT l FROM ListeSelection l")
    , @NamedQuery(name = "ListeSelection.findById", query = "SELECT l FROM ListeSelection l WHERE l.id = :id")
    , @NamedQuery(name = "ListeSelection.findName", query = "SELECT l FROM ListeSelection l WHERE l.nomListe = :nomListe")
    , @NamedQuery(name = "ListeSelection.findByIdUtilisateur", query = "SELECT l FROM ListeSelection l WHERE l.idUtilisateur.id = :idUtilisateur")})
public class ListeSelection implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @Column(name = "nomListe", length = 50)
    private String nomListe;

    public ListeSelection(Integer id, Utilisateur idUtilisateur) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
    }

    public ListeSelection(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }



    public ListeSelection() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }   

    public String getNomListe() {
        return nomListe;
    }

    public void setNomListe(String nomListe) {
        this.nomListe = nomListe;
    }
    
}
