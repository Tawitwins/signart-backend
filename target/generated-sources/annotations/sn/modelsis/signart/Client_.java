package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, String> ville;
	public static volatile SingularAttribute<Client, Date> dateNaissance;
	public static volatile SetAttribute<Client, Commande> commandeSet;
	public static volatile SetAttribute<Client, Commentaire> commentaireSet;
	public static volatile SetAttribute<Client, Panier> panierSet;
	public static volatile SingularAttribute<Client, Character> sexe;
	public static volatile SingularAttribute<Client, String> telephone;
	public static volatile SingularAttribute<Client, String> nom;
	public static volatile SingularAttribute<Client, Utilisateur> idUser;
	public static volatile SingularAttribute<Client, EtatClient> idEtatClient;
	public static volatile SingularAttribute<Client, Pays> idPays;
	public static volatile SetAttribute<Client, TypeNewsletter> typeNewsletterSet;
	public static volatile SetAttribute<Client, Adresse> adresseSet;
	public static volatile SingularAttribute<Client, Integer> id;
	public static volatile SingularAttribute<Client, Devise> idDevise;
	public static volatile SingularAttribute<Client, String> region;
	public static volatile SingularAttribute<Client, String> prenom;

}

