package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MarquageArtiste.class)
public abstract class MarquageArtiste_ {

	public static volatile SingularAttribute<MarquageArtiste, Client> idClient;
	public static volatile SingularAttribute<MarquageArtiste, Date> dateMarquage;
	public static volatile SingularAttribute<MarquageArtiste, TypeMarquage> idTypeMarquage;
	public static volatile SingularAttribute<MarquageArtiste, Integer> id;
	public static volatile SingularAttribute<MarquageArtiste, Artiste> idArtiste;

}

