package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Theme.class)
public abstract class Theme_ {

	public static volatile SetAttribute<Theme, Oeuvre> oeuvreSet;
	public static volatile SingularAttribute<Theme, String> libelle;
	public static volatile SingularAttribute<Theme, String> description;
	public static volatile SingularAttribute<Theme, Integer> id;

}

