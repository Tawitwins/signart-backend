package sn.modelsis.signart;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OeuvreSouscription.class)
public abstract class OeuvreSouscription_ {

	public static volatile SingularAttribute<OeuvreSouscription, Byte> image;
	public static volatile SingularAttribute<OeuvreSouscription, Integer> tauxremise;
	public static volatile SingularAttribute<OeuvreSouscription, BigDecimal> prix;
	public static volatile SingularAttribute<OeuvreSouscription, Boolean> lithographie;
	public static volatile SingularAttribute<OeuvreSouscription, Boolean> nouveau;
	public static volatile SingularAttribute<OeuvreSouscription, Date> dateAjout;
	public static volatile SingularAttribute<OeuvreSouscription, BigDecimal> taxes;
	public static volatile SingularAttribute<OeuvreSouscription, String> description;
	public static volatile SingularAttribute<OeuvreSouscription, Integer> annee;
	public static volatile SingularAttribute<OeuvreSouscription, String> nom;
	public static volatile SingularAttribute<OeuvreSouscription, String> auteur;
	public static volatile SingularAttribute<OeuvreSouscription, Couleur> idCouleur;
	public static volatile SingularAttribute<OeuvreSouscription, Integer> id;
	public static volatile SingularAttribute<OeuvreSouscription, Artiste> idArtiste;
	public static volatile SingularAttribute<OeuvreSouscription, Technique> idTechnique;
	public static volatile SingularAttribute<OeuvreSouscription, String> dimensions;

}

