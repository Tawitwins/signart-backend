package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Fonction.class)
public abstract class Fonction_ {

	public static volatile CollectionAttribute<Fonction, ArtisteFonction> artisteFonctionCollection;
	public static volatile SingularAttribute<Fonction, String> libelle;
	public static volatile SetAttribute<Fonction, Artiste> artisteSet;
	public static volatile SingularAttribute<Fonction, Integer> id;

}

