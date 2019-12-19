package sn.modelsis.signart;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Panier.class)
public abstract class Panier_ {

	public static volatile SetAttribute<Panier, LignePanier> lignePanierSet;
	public static volatile SingularAttribute<Panier, Client> idClient;
	public static volatile SingularAttribute<Panier, EtatPanier> idEtatPanier;
	public static volatile SingularAttribute<Panier, BigDecimal> montant;
	public static volatile SingularAttribute<Panier, Integer> id;
	public static volatile SingularAttribute<Panier, Devise> idDevise;

}

