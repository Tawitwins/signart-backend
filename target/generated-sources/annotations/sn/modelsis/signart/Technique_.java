package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Technique.class)
public abstract class Technique_ {

	public static volatile SetAttribute<Technique, SousTechnique> sousTechniqueSet;
	public static volatile SingularAttribute<Technique, String> libelle;
	public static volatile SingularAttribute<Technique, Integer> id;
	public static volatile SingularAttribute<Technique, Menu> menu;

}

