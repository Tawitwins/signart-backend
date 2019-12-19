package sn.modelsis.signart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paiement.class)
public abstract class Paiement_ {

	public static volatile SingularAttribute<Paiement, EtatPaiement> idEtatPaiement;
	public static volatile SingularAttribute<Paiement, ModePaiement> idModePaiement;
	public static volatile SingularAttribute<Paiement, Date> datePaiement;
	public static volatile SetAttribute<Paiement, LignePaiement> lignePaiementSet;
	public static volatile SingularAttribute<Paiement, Integer> id;
	public static volatile SingularAttribute<Paiement, Commande> commande;

}

