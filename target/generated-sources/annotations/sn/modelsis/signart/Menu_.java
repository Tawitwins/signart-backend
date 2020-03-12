package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

	public static volatile SingularAttribute<Menu, String> path;
	public static volatile SingularAttribute<Menu, String> classe;
	public static volatile SetAttribute<Menu, Profil> profilSet;
	public static volatile SingularAttribute<Menu, String> icon;
	public static volatile SingularAttribute<Menu, Integer> id;
	public static volatile SingularAttribute<Menu, String> title;
	public static volatile SingularAttribute<Menu, Integer> idParent;

}

