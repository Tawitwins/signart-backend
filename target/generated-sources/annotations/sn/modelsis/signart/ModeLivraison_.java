package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModeLivraison.class)
public abstract class ModeLivraison_ {

	public static volatile SingularAttribute<ModeLivraison, String> code;
	public static volatile SingularAttribute<ModeLivraison, String> libelle;
	public static volatile SingularAttribute<ModeLivraison, Integer> id;
	public static volatile SetAttribute<ModeLivraison, Livraison> livraisonSet;

}

