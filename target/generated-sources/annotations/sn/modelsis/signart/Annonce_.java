package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Annonce.class)
public abstract class Annonce_ {

	public static volatile SingularAttribute<Annonce, Date> dateDebut;
	public static volatile SingularAttribute<Annonce, String> titre;
	public static volatile SingularAttribute<Annonce, String> description;
	public static volatile SingularAttribute<Annonce, byte[]> photo;
	public static volatile SingularAttribute<Annonce, Integer> id;
	public static volatile SingularAttribute<Annonce, Date> dateFin;
	public static volatile SingularAttribute<Annonce, Artiste> idArtiste;
	public static volatile SingularAttribute<Annonce, String> lieu;

}

