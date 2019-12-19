package sn.modelsis.signart;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Promotion.class)
public abstract class Promotion_ {

	public static volatile SingularAttribute<Promotion, Integer> tauxReduction;
	public static volatile SingularAttribute<Promotion, BigDecimal> montantReduction;
	public static volatile SingularAttribute<Promotion, Date> dateDebut;
	public static volatile SingularAttribute<Promotion, SousTechnique> idSousTechnique;
	public static volatile SingularAttribute<Promotion, Integer> id;
	public static volatile SingularAttribute<Promotion, Date> dateFin;

}

