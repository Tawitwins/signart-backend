package sn.modelsis.signart;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Commande.class)
public abstract class Commande_ {

	public static volatile SingularAttribute<Commande, String> numero;
	public static volatile SingularAttribute<Commande, Paiement> paiement;
	public static volatile SingularAttribute<Commande, Client> idClient;
	public static volatile SingularAttribute<Commande, BigDecimal> montant;
	public static volatile SingularAttribute<Commande, LocalDate> dateCommande;
	public static volatile SingularAttribute<Commande, String> etat;
	public static volatile SingularAttribute<Commande, Integer> delaiLivraison;
	public static volatile SingularAttribute<Commande, EtatCommande> idEtatCommande;
	public static volatile SetAttribute<Commande, LigneCommande> ligneCommandeSet;
	public static volatile SingularAttribute<Commande, Integer> id;
	public static volatile SingularAttribute<Commande, Livraison> livraison;
	public static volatile SingularAttribute<Commande, Devise> idDevise;
	public static volatile SingularAttribute<Commande, BigDecimal> fraisLivraison;
	public static volatile SingularAttribute<Commande, String> commentaire;

}

