package sn.modelsis.signart;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionOeuvre.class)
public abstract class PromotionOeuvre_ {

	public static volatile SingularAttribute<PromotionOeuvre, Integer> tauxReduction;
	public static volatile SingularAttribute<PromotionOeuvre, BigDecimal> montantReduction;
	public static volatile SingularAttribute<PromotionOeuvre, Date> dateDebut;
	public static volatile SingularAttribute<PromotionOeuvre, Oeuvre> idOeuvre;
	public static volatile SingularAttribute<PromotionOeuvre, Integer> id;
	public static volatile SingularAttribute<PromotionOeuvre, Date> dateFin;

}

