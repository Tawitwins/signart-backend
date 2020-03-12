package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatCommande.class)
public abstract class EtatCommande_ {

	public static volatile SingularAttribute<EtatCommande, String> code;
	public static volatile SetAttribute<EtatCommande, Commande> commandeSet;
	public static volatile SingularAttribute<EtatCommande, String> libelle;
	public static volatile SingularAttribute<EtatCommande, Integer> id;

}

