package sn.modelsis.signart;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livraison.class)
public abstract class Livraison_ {

	public static volatile SingularAttribute<Livraison, Adresse> idAdresseFacturation;
	public static volatile SingularAttribute<Livraison, LocalDate> dateLivraisonPrevue;
	public static volatile SingularAttribute<Livraison, LocalDate> dateLivraisonEffective;
	public static volatile SingularAttribute<Livraison, EtatLivraison> idEtatLivraison;
	public static volatile SingularAttribute<Livraison, Adresse> idAdresseLivraison;
	public static volatile SingularAttribute<Livraison, Commande> commande;
	public static volatile SetAttribute<Livraison, LigneLivraison> ligneLivraisonSet;
	public static volatile SingularAttribute<Livraison, Integer> idCommande;
	public static volatile SingularAttribute<Livraison, ModeLivraison> idModeLivraison;

}

