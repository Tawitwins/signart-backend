package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visiteur.class)
public abstract class Visiteur_ {

	public static volatile SingularAttribute<Visiteur, Integer> id;
	public static volatile SingularAttribute<Visiteur, String> raisonsociale;
	public static volatile SingularAttribute<Visiteur, String> nom;
	public static volatile SingularAttribute<Visiteur, String> prenom;
	public static volatile SingularAttribute<Visiteur, String> typevisiteur;
	public static volatile SingularAttribute<Visiteur, Pays> Pays;

}

