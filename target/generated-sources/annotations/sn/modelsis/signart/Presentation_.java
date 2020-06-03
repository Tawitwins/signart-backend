package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Presentation.class)
public abstract class Presentation_ {

	public static volatile SingularAttribute<Presentation, Boolean> etatPubPresentation;
	public static volatile SingularAttribute<Presentation, String> libelle;
	public static volatile SingularAttribute<Presentation, String> videoId;
	public static volatile SingularAttribute<Presentation, Integer> id;
	public static volatile SingularAttribute<Presentation, Artiste> idArtiste;

}

