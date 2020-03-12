package sn.modelsis.signart;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Oeuvre.class)
public abstract class Oeuvre_ {

	public static volatile SetAttribute<Oeuvre, Theme> themeSet;
	public static volatile SetAttribute<Oeuvre, Image> imageSet;
	public static volatile SingularAttribute<Oeuvre, BigDecimal> prix;
	public static volatile SingularAttribute<Oeuvre, Boolean> nouveau;
	public static volatile SingularAttribute<Oeuvre, Date> dateAjout;
	public static volatile SingularAttribute<Oeuvre, BigDecimal> taxes;
	public static volatile SingularAttribute<Oeuvre, String> description;
	public static volatile SingularAttribute<Oeuvre, Integer> annee;
	public static volatile SingularAttribute<Oeuvre, String> nom;
	public static volatile SingularAttribute<Oeuvre, Couleur> idCouleur;
	public static volatile SetAttribute<Oeuvre, LignePanier> lignePanierSet;
	public static volatile SingularAttribute<Oeuvre, StatutOeuvre> idStatut;
	public static volatile SetAttribute<Oeuvre, MotCle> motCleSet;
	public static volatile SingularAttribute<Oeuvre, Integer> id;
	public static volatile SingularAttribute<Oeuvre, byte[]> image;
	public static volatile SetAttribute<Oeuvre, Domaine> domaineSet;
	public static volatile SingularAttribute<Oeuvre, Integer> tauxremise;
	public static volatile SingularAttribute<Oeuvre, Boolean> lithographie;
	public static volatile SingularAttribute<Oeuvre, String> auteur;
	public static volatile SetAttribute<Oeuvre, PromotionOeuvre> promotionOeuvreSet;
	public static volatile SingularAttribute<Oeuvre, byte[]> miniature;
	public static volatile SetAttribute<Oeuvre, LigneCommande> ligneCommandeSet;
	public static volatile SetAttribute<Oeuvre, MarquageOeuvre> marquageOeuvreSet;
	public static volatile SingularAttribute<Oeuvre, Artiste> idArtiste;
	public static volatile SingularAttribute<Oeuvre, Technique> idTechnique;
	public static volatile SingularAttribute<Oeuvre, BigDecimal> fraisLivraison;
	public static volatile SingularAttribute<Oeuvre, String> dimensions;

}

