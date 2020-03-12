package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Devise.class)
public abstract class Devise_ {

	public static volatile SingularAttribute<Devise, String> code;
	public static volatile SetAttribute<Devise, Commande> commandeSet;
	public static volatile SetAttribute<Devise, Client> clientSet;
	public static volatile SingularAttribute<Devise, String> libelle;
	public static volatile SetAttribute<Devise, Panier> panierSet;
	public static volatile SingularAttribute<Devise, Integer> id;

}

