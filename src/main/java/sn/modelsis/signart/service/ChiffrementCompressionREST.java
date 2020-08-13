/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.ArchiveSignart;
import sn.modelsis.signart.CodeSignart;
import sn.modelsis.signart.ListeSelection_Oeuvres;
import sn.modelsis.signart.dto.ArchiveSignartDto;
import sn.modelsis.signart.dto.CodeSignartDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.ArchiveSignartFacade;
import sn.modelsis.signart.facade.CodeSignartFacade;
import sn.modelsis.signart.facade.LicenceFacade;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.ListeSelection_OeuvresFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author snfayemp
 */
/*@Stateless
@Path("chiffrementCompression")
public class ChiffrementCompressionREST {
    
    public final static String PATH = "";
    public final static String PATHTEST = "C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\";
    
    @Inject
    AbonneFacade abonnefacade;
    
    @Inject
    UtilisateurFacade utilisateurFacade;
    
    @Inject
    ListeSelectionFacade listeSelectionFacade;
    
    @Inject
    AbonnementFacade abonnementfacade;
    
    @Inject
    LicenceFacade licenceFacade;
    
    @Inject
    CodeSignartFacade codeSignartFacade;
    
    @Inject
    ArchiveSignartFacade archiveSignartFacade;
    
    @Inject
    ListeSelection_OeuvresFacade listeOeuvreFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCode(CodeSignartDto dto) throws SignArtException, IOException{
        //recuperer liste des oeuvres de l'abonnement
        Abonnement abonnement = abonnementfacade.findById(dto.getIdAbonnement());
        Abonne abonne = abonnefacade.findById(abonnement.getIdAbonne().getId());
        List<ListeSelection_Oeuvres> listOeuvre = listeOeuvreFacade.findByIdListe(abonnement.getIdListeSelection().getId());
        String folderName = "signart"+abonne.getId();
        String aliasKey = abonne.getPrenom()+"alias"+abonne.getId();
        String codeValeur = ""; 
        
        
        
        
        
        //recuperer le nom de chaque oeuvre de la liste et les soumettre au cryptage
        for(int i=0; i<listOeuvre.size(); i++){
           String oeuvreName = listOeuvre.get(i).getNomOeuvre();          
           String urlImage = PATHTEST+"images\\"+oeuvreName+".jpg";
            try {
                
                chiffrementImage(folderName, oeuvreName, aliasKey, urlImage);
                
            } catch (KeyStoreException ex) {
                Logger.getLogger(ChiffrementCompressionREST.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ChiffrementCompressionREST.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificateException ex) {
                Logger.getLogger(ChiffrementCompressionREST.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnrecoverableEntryException ex) {
                Logger.getLogger(ChiffrementCompressionREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //codeSignartFacade.create(dtoToEntityCode(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createArchive(ArchiveSignartDto dto){
        //licenceFacade.create(dtoToEntity(dto));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    private CodeSignart dtoToEntityCode(CodeSignartDto dto, String codeValeur, String alias) throws SignArtException{
        CodeSignart entity = new CodeSignart();
        entity.setIdAbonnement(abonnementfacade.findById(dto.getIdAbonnement()));
        entity.setKeyAlias(alias);
        entity.setKeyValue(codeValeur);
        return entity;
    }
    
    private ArchiveSignart dtoToEntityArchive(ArchiveSignartDto dto){
        ArchiveSignart entity = new ArchiveSignart();
        entity.setIdAbonnement(dto.getIdAbonnement());
        entity.setNom(dto.getNom());
        return entity;
    }
    
    private CodeSignartDto entityToDtoCode(CodeSignart entity){
        CodeSignartDto dto = new CodeSignartDto();
        dto.setId(entity.getId());
        dto.setIdAbonnement(entity.getIdAbonnement().getId());
        dto.setKeyAlias(dto.getKeyAlias());
        dto.setKeyValue(entity.getKeyValue());
        return dto;
    }
    
    private ArchiveSignartDto entityToDtoArchive(ArchiveSignart entity){
        ArchiveSignartDto dto = new ArchiveSignartDto();
        dto.setId(entity.getId());
        dto.setIdAbonnement(entity.getIdAbonnement());
        dto.setNom(entity.getNom());
        return dto;
    }
     
    private boolean compressionDossier(){
        boolean res = false;
        
        return res;
    }
    
    
    
    private void chiffrementImage(String folderName,String nomImage, String alias, String urlImage) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException{
     
        String path = PATHTEST+"archives\\"+folderName;
         //Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
         System.out.println("Directory created successfully");
         }else{
         System.out.println("Sorry couldn’t create specified directory");
         }
        
        //Generation de la cle
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        System.out.println(storeKey(alias,key));
        
        byte[] content = getFile(urlImage);

        byte[] encrypted = encryptPdfFile(key, content);
        saveFile(encrypted,path+"\\"+nomImage+".jpg");

        System.out.println("Done");
      
    }
    
    public static String storeKey(String KeyAlias, SecretKey mySecretKey) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
    	//Creating the KeyStore object
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        //Loading the KeyStore object
        char[] password = "changeit".toCharArray();
        String path = PATHTEST+"KeyStore\\cacerts";
        java.io.FileInputStream fis = new FileInputStream(path);
        keyStore.load(fis, password);
        
        //Creating the KeyStore.ProtectionParameter object
        KeyStore.ProtectionParameter protectionParam = new KeyStore.PasswordProtection(password);
        
        //Creating SecretKeyEntry object
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(mySecretKey);
        keyStore.setEntry(KeyAlias, secretKeyEntry, protectionParam);

        //Storing the KeyStore object
        java.io.FileOutputStream fos = null;
        fos = new java.io.FileOutputStream(PATHTEST+"KeyStore\\cacerts");
        keyStore.store(fos, password);
        
             
    	return "data stored";
    }
     
    public static byte[] encryptPdfFile(Key key, byte[] content) {
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
            encrypted = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }
    
    public static void saveFile(byte[] bytes, String chemin) throws IOException {

        FileOutputStream fos = new FileOutputStream(chemin);
        fos.write(bytes);
        fos.close();

    }
    
    public static byte[] getFile(String urlImage) {

        File f = new File(urlImage);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        byte[] content = null;
        try {
            content = new byte[is.available()];
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            is.read(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }*/

//}

