/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.ArchiveSignartFacade;
import sn.modelsis.signart.facade.CodeSignartFacade;
import sn.modelsis.signart.facade.DelaiFacade;
import sn.modelsis.signart.facade.EtatLicenceFacade;
import sn.modelsis.signart.facade.LicenceFacade;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.ListeSelection_OeuvresFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
@javax.ws.rs.Path("decompressionDechiffrement")
public class DecompressionDechiffrementREST {
    
    public final static String GOODPATH = "/opt/images";
   // public final static String PATH = "C:\\Users\\snmbengueo\\Documents\\SignartRepSave\\abonnement\\";
    public final static String PATHTEST = "C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\";
    public final static String PATH = "/signartFiles/abonnement/";

    @Inject
    AbonneFacade abonnefacade;

    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @Inject
    AbonnementFacade abonnementFacade;
    
    @Inject
    AbonneFacade abonneFacade;
    
    @Inject
    LicenceFacade licenceFacade;
    
    @Inject
    EtatLicenceFacade etatLicenceFacade;
    
    @Inject
    CodeSignartFacade codeSignartFacade;
    
    @Inject
    DelaiFacade delaiFacade;
    
    @Inject
    ArchiveSignartFacade archiveSignartFacade;
    
    @Inject
    ListeSelection_OeuvresFacade listeOeuvreFacade;
    
}
