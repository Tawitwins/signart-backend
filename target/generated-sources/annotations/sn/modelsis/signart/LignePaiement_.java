package sn.modelsis.signart;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LignePaiement.class)
public abstract class LignePaiement_ {

	public static volatile SingularAttribute<LignePaiement, ModePaiement> idModePaiement;
	public static volatile SingularAttribute<LignePaiement, Paiement> idPaiement;
	public static volatile SingularAttribute<LignePaiement, Date> datePaiement;
	public static volatile SingularAttribute<LignePaiement, BigDecimal> montant;
	public static volatile SingularAttribute<LignePaiement, Integer> id;

}

