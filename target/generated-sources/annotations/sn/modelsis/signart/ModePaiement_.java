package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModePaiement.class)
public abstract class ModePaiement_ {

	public static volatile SingularAttribute<ModePaiement, String> code;
	public static volatile SetAttribute<ModePaiement, LignePaiement> lignePaiementSet;
	public static volatile SingularAttribute<ModePaiement, String> libelle;
	public static volatile SetAttribute<ModePaiement, Paiement> paiementSet;
	public static volatile SingularAttribute<ModePaiement, Integer> id;

}

