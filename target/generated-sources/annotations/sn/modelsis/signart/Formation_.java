package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Formation.class)
public abstract class Formation_ {

	public static volatile SingularAttribute<Formation, Boolean> etatPublication;
	public static volatile SingularAttribute<Formation, String> specialisation;
	public static volatile SingularAttribute<Formation, Integer> anneeDebut;
	public static volatile SingularAttribute<Formation, String> libelle;
	public static volatile SingularAttribute<Formation, Integer> anneeFin;
	public static volatile SingularAttribute<Formation, Integer> id;
	public static volatile SingularAttribute<Formation, Artiste> idArtiste;
	public static volatile SingularAttribute<Formation, String> sigle;
	public static volatile SingularAttribute<Formation, String> lieu;

}

