package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MarquageOeuvre.class)
public abstract class MarquageOeuvre_ {

	public static volatile SingularAttribute<MarquageOeuvre, Client> idClient;
	public static volatile SingularAttribute<MarquageOeuvre, Date> dateMarquage;
	public static volatile SingularAttribute<MarquageOeuvre, Oeuvre> idOeuvre;
	public static volatile SingularAttribute<MarquageOeuvre, TypeMarquage> idTypeMarquage;
	public static volatile SingularAttribute<MarquageOeuvre, Integer> id;

}

