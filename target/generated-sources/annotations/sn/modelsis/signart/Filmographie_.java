package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Filmographie.class)
public abstract class Filmographie_ {

	public static volatile SingularAttribute<Filmographie, String> libelle;
	public static volatile SingularAttribute<Filmographie, Date> duree;
	public static volatile SingularAttribute<Filmographie, Integer> id;
	public static volatile SingularAttribute<Filmographie, Artiste> idArtiste;
	public static volatile SingularAttribute<Filmographie, String> type;
	public static volatile SingularAttribute<Filmographie, String> auteur;

}

