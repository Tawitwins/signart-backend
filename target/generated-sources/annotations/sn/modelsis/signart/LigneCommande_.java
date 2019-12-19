package sn.modelsis.signart;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LigneCommande.class)
public abstract class LigneCommande_ {

	public static volatile SingularAttribute<LigneCommande, BigDecimal> prix;
	public static volatile SingularAttribute<LigneCommande, EtatLigneCommande> idEtatLigneCommande;
	public static volatile SingularAttribute<LigneCommande, Oeuvre> idOeuvre;
	public static volatile SingularAttribute<LigneCommande, Integer> id;
	public static volatile SetAttribute<LigneCommande, LigneLivraison> ligneLivraisonSet;
	public static volatile SingularAttribute<LigneCommande, Commande> idCommande;
	public static volatile SingularAttribute<LigneCommande, Integer> quantite;

}

