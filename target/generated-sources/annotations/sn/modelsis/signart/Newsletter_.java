package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Newsletter.class)
public abstract class Newsletter_ {

	public static volatile SingularAttribute<Newsletter, Boolean> envoye;
	public static volatile SingularAttribute<Newsletter, Date> dateEnvoi;
	public static volatile SingularAttribute<Newsletter, TypeNewsletter> idTypeNewsletter;
	public static volatile SingularAttribute<Newsletter, Integer> id;
	public static volatile SingularAttribute<Newsletter, String> contenu;
	public static volatile SetAttribute<Newsletter, NewsletterTrace> newsletterTraceSet;

}

