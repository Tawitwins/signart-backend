package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profil.class)
public abstract class Profil_ {

	public static volatile SingularAttribute<Profil, String> code;
	public static volatile SetAttribute<Profil, Menu> menuSet;
	public static volatile SingularAttribute<Profil, String> libelle;
	public static volatile SetAttribute<Profil, Utilisateur> utilisateurSet;
	public static volatile SingularAttribute<Profil, Integer> id;

}

