package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TypeMarquage.class)
public abstract class TypeMarquage_ {

	public static volatile SingularAttribute<TypeMarquage, String> code;
	public static volatile SingularAttribute<TypeMarquage, String> libelle;
	public static volatile SetAttribute<TypeMarquage, MarquageOeuvre> marquageOeuvreSet;
	public static volatile SingularAttribute<TypeMarquage, Integer> id;

}

