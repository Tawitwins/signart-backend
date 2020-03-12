package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatArtiste.class)
public abstract class EtatArtiste_ {

	public static volatile SingularAttribute<EtatArtiste, String> code;
	public static volatile SingularAttribute<EtatArtiste, String> libelle;
	public static volatile SetAttribute<EtatArtiste, Artiste> artisteSet;
	public static volatile SingularAttribute<EtatArtiste, Integer> id;

}

