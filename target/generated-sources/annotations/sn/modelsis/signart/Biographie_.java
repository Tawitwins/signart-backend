package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Biographie.class)
public abstract class Biographie_ {

	public static volatile SingularAttribute<Biographie, String> lieuNaissance;
	public static volatile SingularAttribute<Biographie, String> nationalite;
	public static volatile SingularAttribute<Biographie, Date> dateNaissance;
	public static volatile SetAttribute<Biographie, Artiste> artisteSet;
	public static volatile SingularAttribute<Biographie, Integer> id;

}

