package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TypeAdresse.class)
public abstract class TypeAdresse_ {

	public static volatile SingularAttribute<TypeAdresse, String> code;
	public static volatile SingularAttribute<TypeAdresse, String> libelle;
	public static volatile SetAttribute<TypeAdresse, Adresse> adresseSet;
	public static volatile SingularAttribute<TypeAdresse, Integer> id;

}

