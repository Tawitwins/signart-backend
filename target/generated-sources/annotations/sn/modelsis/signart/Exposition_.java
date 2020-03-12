package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exposition.class)
public abstract class Exposition_ {

	public static volatile SingularAttribute<Exposition, Date> dateDebut;
	public static volatile SingularAttribute<Exposition, String> titre;
	public static volatile SetAttribute<Exposition, Artiste> artisteSet;
	public static volatile SingularAttribute<Exposition, String> adresse;
	public static volatile SingularAttribute<Exposition, String> description;
	public static volatile SingularAttribute<Exposition, Integer> id;
	public static volatile SingularAttribute<Exposition, Date> dateFin;
	public static volatile SingularAttribute<Exposition, Artiste> idArtiste;
	public static volatile SingularAttribute<Exposition, String> type;
	public static volatile SingularAttribute<Exposition, Boolean> etatExposition;

}

