package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pays.class)
public abstract class Pays_ {

	public static volatile SingularAttribute<Pays, String> code;
	public static volatile SetAttribute<Pays, Client> clientSet;
	public static volatile SingularAttribute<Pays, String> libelle;
	public static volatile SetAttribute<Pays, Artiste> artisteSet;
	public static volatile SingularAttribute<Pays, Integer> id;

}

