package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Domaine.class)
public abstract class Domaine_ {

	public static volatile SetAttribute<Domaine, Oeuvre> oeuvreSet;
	public static volatile SingularAttribute<Domaine, String> libelle;
	public static volatile SingularAttribute<Domaine, String> description;
	public static volatile SingularAttribute<Domaine, Integer> id;

}

