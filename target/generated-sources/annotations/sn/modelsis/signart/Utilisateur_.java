package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, String> mail;
	public static volatile SetAttribute<Utilisateur, Client> clientSet;
	public static volatile SetAttribute<Utilisateur, Artiste> artisteSet;
	public static volatile SingularAttribute<Utilisateur, Boolean> actif;
	public static volatile SingularAttribute<Utilisateur, Integer> id;
	public static volatile SingularAttribute<Utilisateur, String> userType;
	public static volatile SingularAttribute<Utilisateur, Profil> idProfil;

}

