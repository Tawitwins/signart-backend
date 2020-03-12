package sn.modelsis.signart.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author SNLOM
 */
@javax.ws.rs.ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.jboss.resteasy.plugins.providers.multipart.ListMultipartReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.ListMultipartWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MapMultipartFormDataReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MapMultipartFormDataWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MimeMultipartProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartFormAnnotationReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartFormAnnotationWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartRelatedReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartRelatedWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.MultipartWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.XopWithMultipartRelatedReader.class);
        resources.add(org.jboss.resteasy.plugins.providers.multipart.XopWithMultipartRelatedWriter.class);
        resources.add(sn.modelsis.signart.rest.application.config.SignArtExceptionMapper.class);
        resources.add(sn.modelsis.signart.service.AccountREST.class);
        resources.add(sn.modelsis.signart.service.AdresseREST.class);
        resources.add(sn.modelsis.signart.service.AnnonceREST.class);
        resources.add(sn.modelsis.signart.service.ArtisteREST.class);
        resources.add(sn.modelsis.signart.service.BiographieREST.class);
        resources.add(sn.modelsis.signart.service.ClientFacadeREST.class);
        resources.add(sn.modelsis.signart.service.CommandeREST.class);
        resources.add(sn.modelsis.signart.service.CommentaireFacadeREST.class);
        resources.add(sn.modelsis.signart.service.CompteFacadeREST.class);
        resources.add(sn.modelsis.signart.service.CouleurFacadeREST.class);
        resources.add(sn.modelsis.signart.service.DeviseFacadeREST.class);
        resources.add(sn.modelsis.signart.service.DomaineFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatArtisteFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatClientFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatCommandeFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatLivraisonFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatPaiementFacadeREST.class);
        resources.add(sn.modelsis.signart.service.EtatPanierFacadeREST.class);
        resources.add(sn.modelsis.signart.service.ExpositionREST.class);
        resources.add(sn.modelsis.signart.service.FilmographieREST.class);
        resources.add(sn.modelsis.signart.service.FonctionREST.class);
        resources.add(sn.modelsis.signart.service.FormationREST.class);
        resources.add(sn.modelsis.signart.service.ImageREST.class);
        resources.add(sn.modelsis.signart.service.LigneCommandeREST.class);
        resources.add(sn.modelsis.signart.service.LignePanierREST.class);
        resources.add(sn.modelsis.signart.service.LivraisonREST.class);
        resources.add(sn.modelsis.signart.service.LoginREST.class);
        resources.add(sn.modelsis.signart.service.MarquageArtisteFacadeREST.class);
        resources.add(sn.modelsis.signart.service.MarquageOeuvreFacadeREST.class);
        resources.add(sn.modelsis.signart.service.MenuFacadeREST.class);
        resources.add(sn.modelsis.signart.service.ModeLivraisonFacadeREST.class);
        resources.add(sn.modelsis.signart.service.ModePaiementFacadeREST.class);
        resources.add(sn.modelsis.signart.service.MotCleFacadeREST.class);
        resources.add(sn.modelsis.signart.service.NewCrossOriginResourceSharingFilter.class);
        resources.add(sn.modelsis.signart.service.NewsletterREST.class);
        resources.add(sn.modelsis.signart.service.OeuvreFacadeREST.class);
        resources.add(sn.modelsis.signart.service.OeuvreSouscriptionREST.class);
        resources.add(sn.modelsis.signart.service.PaiementREST.class);
        resources.add(sn.modelsis.signart.service.PanierREST.class);
        resources.add(sn.modelsis.signart.service.PaysFacadeREST.class);
        resources.add(sn.modelsis.signart.service.ProfilFacadeREST.class);
        resources.add(sn.modelsis.signart.service.PromotionREST.class);
        resources.add(sn.modelsis.signart.service.TechniqueFacadeREST.class);
        resources.add(sn.modelsis.signart.service.ThemeFacadeREST.class);
        resources.add(sn.modelsis.signart.service.TypeCommandeFacadeREST.class);
        resources.add(sn.modelsis.signart.service.TypeCompteFacadeREST.class);
        resources.add(sn.modelsis.signart.service.UserFacadeREST.class);
        resources.add(sn.modelsis.signart.service.VisiteurFacadeREST.class);
    }
}
