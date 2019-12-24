package sn.modelsis.signart;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LigneLivraison.class)
public abstract class LigneLivraison_ {

	public static volatile SingularAttribute<LigneLivraison, LocalDate> dateLivraison;
	public static volatile SingularAttribute<LigneLivraison, LigneCommande> idLigneCommande;
	public static volatile SingularAttribute<LigneLivraison, EtatLivraison> idEtatLivraison;
	public static volatile SingularAttribute<LigneLivraison, Integer> id;
	public static volatile SingularAttribute<LigneLivraison, Livraison> idLivraison;

}

