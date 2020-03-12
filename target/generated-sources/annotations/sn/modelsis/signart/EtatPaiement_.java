package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatPaiement.class)
public abstract class EtatPaiement_ {

	public static volatile SingularAttribute<EtatPaiement, String> code;
	public static volatile SetAttribute<EtatPaiement, LignePaiement> lignePaiementSet;
	public static volatile SingularAttribute<EtatPaiement, String> libelle;
	public static volatile SetAttribute<EtatPaiement, Paiement> paiementSet;
	public static volatile SingularAttribute<EtatPaiement, Integer> id;

}

