package sn.modelsis.signart;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Artiste.class)
public abstract class Artiste_ {

	public static volatile SetAttribute<Artiste, Annonce> annonceSet;
	public static volatile SingularAttribute<Artiste, String> villeGalerie;
	public static volatile SingularAttribute<Artiste, String> formation;
	public static volatile SingularAttribute<Artiste, String> nom;
	public static volatile SingularAttribute<Artiste, Utilisateur> idUser;
	public static volatile SetAttribute<Artiste, Compte> compteSet;
	public static volatile SingularAttribute<Artiste, Pays> idPays;
	public static volatile SetAttribute<Artiste, Oeuvre> oeuvreSet;
	public static volatile SingularAttribute<Artiste, Biographie> idBiographie;
	public static volatile SetAttribute<Artiste, ArtisteFonction> artisteFonctionSet;
	public static volatile SingularAttribute<Artiste, String> genre;
	public static volatile SingularAttribute<Artiste, Integer> id;
	public static volatile SingularAttribute<Artiste, String> specialites;
	public static volatile SetAttribute<Artiste, Formation> formationSet;
	public static volatile SingularAttribute<Artiste, String> prenom;
	public static volatile SingularAttribute<Artiste, String> email;
	public static volatile SingularAttribute<Artiste, String> surnom;
	public static volatile SingularAttribute<Artiste, String> profession;
	public static volatile SingularAttribute<Artiste, String> ville;
	public static volatile SetAttribute<Artiste, Exposition> expositionSet;
	public static volatile SingularAttribute<Artiste, String> biographie;
	public static volatile SingularAttribute<Artiste, byte[]> photo;
	public static volatile SingularAttribute<Artiste, String> telephone;
	public static volatile SetAttribute<Artiste, Fonction> fonctionSet;
	public static volatile SingularAttribute<Artiste, String> nomGalerie;
	public static volatile SingularAttribute<Artiste, EtatArtiste> idEtatArtiste;
	public static volatile SingularAttribute<Artiste, String> adresse;
	public static volatile SingularAttribute<Artiste, String> adrGalerie;
	public static volatile SetAttribute<Artiste, Filmographie> filmographieSet;
	public static volatile SingularAttribute<Artiste, String> expositions;

}

