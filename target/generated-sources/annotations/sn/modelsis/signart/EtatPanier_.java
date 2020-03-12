package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatPanier.class)
public abstract class EtatPanier_ {

	public static volatile SingularAttribute<EtatPanier, String> code;
	public static volatile SingularAttribute<EtatPanier, String> libelle;
	public static volatile SetAttribute<EtatPanier, Panier> panierSet;
	public static volatile SingularAttribute<EtatPanier, Integer> id;

}

