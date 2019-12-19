package sn.modelsis.signart;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LignePanier.class)
public abstract class LignePanier_ {

	public static volatile SingularAttribute<LignePanier, BigDecimal> prix;
	public static volatile SingularAttribute<LignePanier, Oeuvre> idOeuvre;
	public static volatile SingularAttribute<LignePanier, Integer> id;
	public static volatile SingularAttribute<LignePanier, Panier> idPanier;
	public static volatile SingularAttribute<LignePanier, EtatLignePanier> idEtatLignePanier;
	public static volatile SingularAttribute<LignePanier, Integer> quantite;

}

