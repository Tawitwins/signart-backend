package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtatLigneCommande.class)
public abstract class EtatLigneCommande_ {

	public static volatile SingularAttribute<EtatLigneCommande, String> code;
	public static volatile SingularAttribute<EtatLigneCommande, String> libelle;
	public static volatile SetAttribute<EtatLigneCommande, LigneCommande> ligneCommandeSet;
	public static volatile SingularAttribute<EtatLigneCommande, Integer> id;

}

