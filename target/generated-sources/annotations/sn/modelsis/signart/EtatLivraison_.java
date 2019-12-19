package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatLivraison.class)
public abstract class EtatLivraison_ {

	public static volatile SingularAttribute<EtatLivraison, String> code;
	public static volatile SingularAttribute<EtatLivraison, String> libelle;
	public static volatile SingularAttribute<EtatLivraison, Integer> id;
	public static volatile SetAttribute<EtatLivraison, LigneLivraison> ligneLivraisonSet;
	public static volatile SetAttribute<EtatLivraison, Livraison> livraisonSet;

}

