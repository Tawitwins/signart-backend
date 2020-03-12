package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adresse.class)
public abstract class Adresse_ {

	public static volatile SingularAttribute<Adresse, TypeAdresse> idTypeAdresse;
	public static volatile SingularAttribute<Adresse, String> ville;
	public static volatile SingularAttribute<Adresse, Client> idClient;
	public static volatile SingularAttribute<Adresse, String> indicatif;
	public static volatile SingularAttribute<Adresse, String> telephone;
	public static volatile SingularAttribute<Adresse, String> nom;
	public static volatile SetAttribute<Adresse, Livraison> livraisonSet;
	public static volatile SingularAttribute<Adresse, Pays> idPays;
	public static volatile SingularAttribute<Adresse, String> adresse;
	public static volatile SetAttribute<Adresse, Livraison> livraisonFacturationSet;
	public static volatile SingularAttribute<Adresse, Integer> id;
	public static volatile SingularAttribute<Adresse, String> region;
	public static volatile SingularAttribute<Adresse, String> prenom;

}

