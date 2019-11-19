package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatClient.class)
public abstract class EtatClient_ {

	public static volatile SingularAttribute<EtatClient, String> code;
	public static volatile SetAttribute<EtatClient, Client> clientSet;
	public static volatile SingularAttribute<EtatClient, String> libelle;
	public static volatile SingularAttribute<EtatClient, Integer> id;

}

