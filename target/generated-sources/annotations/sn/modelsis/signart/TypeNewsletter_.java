package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TypeNewsletter.class)
public abstract class TypeNewsletter_ {

	public static volatile SingularAttribute<TypeNewsletter, Integer> periodicite;
	public static volatile SetAttribute<TypeNewsletter, Client> clientSet;
	public static volatile SetAttribute<TypeNewsletter, AbonnementNewsletter> abonnementNewsletterSet;
	public static volatile SingularAttribute<TypeNewsletter, String> libelle;
	public static volatile SetAttribute<TypeNewsletter, Newsletter> newsletterSet;
	public static volatile SingularAttribute<TypeNewsletter, Integer> id;

}

