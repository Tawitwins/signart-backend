package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compte.class)
public abstract class Compte_ {

	public static volatile SingularAttribute<Compte, TypeCompte> idTypeCompte;
	public static volatile SingularAttribute<Compte, Integer> id;
	public static volatile SingularAttribute<Compte, Artiste> idArtiste;
	public static volatile SingularAttribute<Compte, String> url;

}

