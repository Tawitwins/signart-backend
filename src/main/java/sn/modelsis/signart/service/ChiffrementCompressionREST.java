/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.modelsis.signart.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import sn.modelsis.signart.Abonne;
import sn.modelsis.signart.Abonnement;
import sn.modelsis.signart.ArchiveSignart;
import sn.modelsis.signart.CodeSignart;
import sn.modelsis.signart.Delai;
import sn.modelsis.signart.HistoriqueAbonnement;
import sn.modelsis.signart.Licence;
import sn.modelsis.signart.ListeSelection_Oeuvres;
import sn.modelsis.signart.OeuvreNumerique;
import sn.modelsis.signart.Utilisateur;
import sn.modelsis.signart.dto.AbonnementDto;
import sn.modelsis.signart.dto.ArchiveSignartDto;
import sn.modelsis.signart.dto.CodeSignartDto;
import sn.modelsis.signart.dto.LicenceDto;
import sn.modelsis.signart.exception.SignArtException;
import sn.modelsis.signart.facade.AbonneFacade;
import sn.modelsis.signart.facade.AbonnementFacade;
import sn.modelsis.signart.facade.ArchiveSignartFacade;
import sn.modelsis.signart.facade.CodeSignartFacade;
import sn.modelsis.signart.facade.DelaiFacade;
import sn.modelsis.signart.facade.EtatAbonnementFacade;
import sn.modelsis.signart.facade.EtatLicenceFacade;
import sn.modelsis.signart.facade.HistoriqueAbonnementFacade;
import sn.modelsis.signart.facade.LicenceFacade;
import sn.modelsis.signart.facade.ListeSelectionFacade;
import sn.modelsis.signart.facade.ListeSelection_OeuvresFacade;
import sn.modelsis.signart.facade.UtilisateurFacade;

/**
 *
 * @author snfayemp
 */
@Stateless
@javax.ws.rs.Path("chiffrementCompression")
public class ChiffrementCompressionREST {
    
    public final static String PATH = "/opt/images";
    public final static String PATHTEST = "C:\\Users\\snfayemp\\Documents\\Projet\\Stockage\\";
    
    @Inject
    AbonneFacade abonnefacade;

    @Inject
    EtatAbonnementFacade etatAbonnementFacade;
    
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
    HistoriqueAbonnementFacade historiqueAbonnementFacade;
    
    @Inject
    ListeSelection_OeuvresFacade listeOeuvreFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response traitementAbonnement(AbonnementDto dto) throws SignArtException, IOException, NoSuchAlgorithmException{
        Abonnement abonnement = abonnementFacade.findById(dto.getId());
        
        ArchiveSignart archive = new ArchiveSignart();
        //Creation de la licence
        Licence licence = dtoToEntityLicence(dto);
        String licenceValue = licence.getValeur();
        Licence entLic = licenceFacade.add(licence);
        //Creation du code signart
        CodeSignart code = dtoToEntityCode(entLic,dto);
        codeSignartFacade.create(code);
        //Chiffrement + Compression + supression repertoire
        Abonne abonne = abonneFacade.findById(dto.getIdAbonne());
        Utilisateur user = utilisateurFacade.findById(abonne.getIdUtilisateur().getId());
        String zipFolderName = chiffrement(abonne,user,dto,licenceValue);
        archive.setIdAbonnement(abonnementFacade.findById(dto.getId()));
        archive.setNom(zipFolderName);
        archiveSignartFacade.create(archive);
        abonnement.setEtatAbonnement(etatAbonnementFacade.findByLibelle("en_attente_paiement"));
        abonnementFacade.edit(abonnement);

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
   
    @GET
    @javax.ws.rs.Path("identification/{code}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response identification(@PathParam("code") String code) throws SignArtException {
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
	Calendar c = Calendar.getInstance();
	c.setTime(currentDate);
         
        
             CodeSignart codeSignart = codeSignartFacade.findByCode(code);
             Licence licence = licenceFacade.findById(codeSignart.getIdLicence().getId());
             licence.setIdEtatLicence(etatLicenceFacade.findByLibelle("ACTIF"));
             Abonnement abonnement = abonnementFacade.findById(licence.getIdAbonnement().getId());
             Abonne abonne = abonneFacade.findById(abonnement.getIdAbonne().getId());
             String username = (abonne.getPrenom()+""+abonne.getNom()).toLowerCase();
             String zipName = username+""+abonne.getId()+".zip";
             HistoriqueAbonnement historique = new HistoriqueAbonnement();
             c.add(Calendar.MONTH, abonnement.getIdDelai().getNbMois());
	     Date currentDatePlusOne = c.getTime();
             historique.setDateDebut(currentDate);
             historique.setDateFin(currentDatePlusOne);
             historique.setIdAbonnement(abonnement);
             historique.setIdUtilisateur(abonne.getIdUtilisateur());
             historiqueAbonnementFacade.add(historique);
             //File fileDownload = new File(PATHTEST+"\\archives\\"+zipName);
             File fileDownload = new File(PATH+"/"+zipName);
             ResponseBuilder response = Response.ok((Object) fileDownload);
             response.header("Content-Disposition", "attachment;filename=" + zipName);
                            
       return response.build();           
    }
    
    
  
    
    
    private CodeSignart dtoToEntityCode(Licence licence, AbonnementDto dto) throws SignArtException, NoSuchAlgorithmException{
        CodeSignart entityCode = new CodeSignart();
        entityCode.setIdAbonne(abonneFacade.findById(dto.getIdAbonne()));
        entityCode.setIdLicence(licence);
        String codeValue = licence.getValeur();
        
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update(codeValue.getBytes());
                
		String codeSignart = bytesToHex(messageDigest.digest());
                
        entityCode.setCode(codeSignart.substring(20));
                
        return entityCode;
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
        dto.setCode(entity.getCode());
        dto.setIdAbonne(entity.getIdAbonne().getId());
        dto.setIdLicence(entity.getIdLicence().getId());
        return dto;
    }
    
    private ArchiveSignartDto entityToDtoArchive(ArchiveSignart entity){
        ArchiveSignartDto dto = new ArchiveSignartDto();
        dto.setId(entity.getId());
        dto.setIdAbonnement(entity.getIdAbonnement());
        dto.setNom(entity.getNom());
        return dto;
    }
    
     private Licence dtoToEntityLicence(AbonnementDto dto) throws SignArtException {
        
        Licence entityLicence = new Licence();
        //Abonnement abonnement = abonnementFacade.findById(dto.getId());
        //System.out.println(abonnement+"++++++++++++++++++++++++++++++abonnement++++++++++++++++++++++++++++++++++++");
        Abonne abonne = abonneFacade.findById(dto.getIdAbonne());
       System.out.println(abonne+"++++++++++++++++++++++++++++++abonne++++++++++++++++++++++++++++++++++++");
        Delai delai = delaiFacade.findById(dto.getIdDelai());
        String username = abonne.getPrenom()+""+abonne.getNom()+""+abonne.getTelephone();  
        System.out.println(username+"++++++++++++++++++++++++++++++username++++++++++++++++++++++++++++++++++++");
        int nbMois = delai.getNbMois();
        String licenceValue = createLicence(username, nbMois);
        entityLicence.setIdAbonnement(abonnementFacade.findById(dto.getId()));
        entityLicence.setValeur(licenceValue);
        entityLicence.setIdEtatLicence(etatLicenceFacade.findByLibelle("INACTIF"));
        return entityLicence;
    }
    
    
     private String createLicence(String username, int nbMois){
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
                String licence2 = "";
                String licence3 = "";
		c.add(Calendar.MONTH, nbMois);
		
		Date currentDatePlusOne = c.getTime();
		//System.out.println(dateFormat.format(currentDatePlusOne));
		String date = dateFormat.format(currentDatePlusOne);
		//System.out.println(date);
		String nombreMois = Integer.toString(nbMois);
		String licence = "Application: SignArt\n" + 
				         "Version: 1.0\n" + 
				         "Delai_Abonnement: nombre_mois\n" + 
				         "Utilisateur: nom_du_client";
                
		//System.out.println("++++++++Avant+++++++++");
		//System.out.println(licence);
		//String nom = "Penda_Faye_00777966116";
		licence2 = licence.replaceAll("nom_du_client", username);
		licence3 = licence2.replaceAll("nombre_mois", nombreMois );
		//System.out.println("++++++++Après+++++++++");
		//System.out.println(licence2);
                String filename = "licence00"+username;    
                 try {
                        FileWriter myWriter = new FileWriter("/opt/images"+filename);
                                                //System.out.println(LicenceREST.class.getResource("/Stockage/licences").getPath()+"++++++++++++++++++++++++++++++path++++++++++++++++++++++++++++++++++++");

                        //File file = new File("");
                       // FileWriter myWriter = new FileWriter();
                       // FileWriter myWriter = new FileWriter(LicenceREST.class.getResource("/Stockage/licences").getPath()+"/"+filename+".txt");
                       // File(ImageNumeriqueREST.class.getResource("/Stockage/images").getPath()+"/"+nom+".jpg")

                        myWriter.write(licence3);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
		
		return licence3;
    }
     
     	
     public  String bytesToHex(byte[] b) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < b.length; j++) {
              buffer.append(hexDigits[(b[j] >> 4) & 0x0f]);
              buffer.append(hexDigits[b[j] & 0x0f]);
            }
            return buffer.toString();
          }
     
     ///Etape 1: Creation du répertoire abonné
	public static String createRertory(String path, String folderName) throws IOException{
		//String folderPath = path+"\\"+folderName;
                String folderPath = path+"/"+folderName;
		Path chemin = Paths.get(folderPath);
		Files.createDirectory(chemin);
            	System.out.println("++++++++++++++++++++++++++++++++++++Creation du repertoire: OK++++++++++++++++++++++++++");    
		return folderPath;
	}
        
        ///Etape 2: Generation de la clé
	public static SecretKeySpec generateKey(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{

		String sault = "Signart Galerie";
		String paramsKey = username+password+sault;
		byte[] key = (paramsKey).getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		System.out.println("++++++++++++++++++++++++++++++++++++Generation de la cle: OK++++++++++++++++++++++++++");
		return secretKeySpec;
	}

	///Etape 3: Recuperation du fichier image
	public static byte[] getFile(String urlImage) {
        File f = new File(urlImage);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        byte[] content = null;
        try {
            content = new byte[is.available()];
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            is.read(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++recuperation du fichier: OK++++++++++++++++++++++++++++++++++++");
        return content;
    }

	///Etape 4: Chiffrement de l'image
	public static byte[] encryptFile(SecretKeySpec key, byte[] content) {
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
            encrypted = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++chiffrement du fichier: OK++++++++++++++++++++++++++++++++++++++++++");
        return encrypted;
    }

	///Stockage de l'image sur le repertoire abonné
	public static void saveFile(byte[] bytes, String path) throws IOException {

        FileOutputStream fos = new FileOutputStream(path);
        fos.write(bytes);
        fos.close();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++Sauvegarde du fichier: OK+++++++++++++++++++++++++++++++++++");
    }

	///Compression du fichier
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
	        if (fileToZip.isHidden()) {
	            return;
	        }
	        if (fileToZip.isDirectory()) {
	            if (fileName.endsWith("/")) {
	                zipOut.putNextEntry(new ZipEntry(fileName));
	                zipOut.closeEntry();
	            } else {
	                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
	                zipOut.closeEntry();
	            }
	            File[]children = fileToZip.listFiles();
	            for (File childFile : children) {
	                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
	            }
	            return;
	        }
	        FileInputStream fis = new FileInputStream(fileToZip);
	        ZipEntry zipEntry = new ZipEntry(fileName);
	        zipOut.putNextEntry(zipEntry);
	        byte[]bytes = new byte[1024];
	        int length;
	        while ((length = fis.read(bytes)) >= 0) {
	            zipOut.write(bytes, 0, length);
	        }
	        fis.close();
	        System.out.println("++++++++++++++++++++++++++++++++Compression du repertoire: OK+++++++++++++++++++++++++++++");
	    }

	///Supression du repertoire
	public static void deleteDirectoryRecursion(Path path) throws IOException {
		  if (Files.isDirectory(path, java.nio.file.LinkOption.NOFOLLOW_LINKS)) {
		    try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
		      for (Path entry : entries) {
		        deleteDirectoryRecursion(entry);
		      }
		    }
		  }
		  Files.delete(path);
		  System.out.println("Supression repertoire: OK");
	}

     
        public String chiffrement(Abonne abonne, Utilisateur user,AbonnementDto dto,String licenceValue) throws SignArtException, IOException, UnsupportedEncodingException, NoSuchAlgorithmException{
               //String path = PATHTEST+"\\archives"; // "/Users/macbookpro/Desktop/TestSignart/repertoires";
                String path = PATH; // "/Users/macbookpro/Desktop/TestSignart/repertoires";
		String username = abonne.getPrenom()+""+abonne.getNom(); //MamePendaFaye";
		String password = user.getPassword();//"Konya2020";
		String folderName = username.toLowerCase()+""+abonne.getId();//mamependafaye33";
		  
		String folderPath = createRertory(path, folderName);
                SecretKeySpec key = generateKey(username,password);
                
                List<ListeSelection_Oeuvres> list = listeOeuvreFacade.findByIdListe(dto.getIdListeSelection());
                for(int i=0; i<list.size(); i++){
                    //String saveImagePath = folderPath+"\\"+list.get(i).getNomOeuvre()+".jpg";
                    String saveImagePath = folderPath+"/"+list.get(i).getNomOeuvre()+".jpg";
                    byte[] image = getRealImage(list.get(i));
                    byte[] encryptedImage = encryptFile(key,image);
                    saveFile(encryptedImage,saveImagePath);
                }
                
                String filename = "licence00"+abonne.getId()+username+abonne.getTelephone();                  
                //FileWriter myWriter = new FileWriter(folderPath+"\\"+filename);
                FileWriter myWriter = new FileWriter(folderPath+"/"+filename);
                myWriter.write(licenceValue);
                myWriter.close();
                                   
                String zipName = folderName+".zip";
	        String sourceFile = folderPath; //"/Users/macbookpro/Desktop/StageMemoireSignArt/testChiffrement/zipTest";
                //FileOutputStream fos = new FileOutputStream(path+"\\"+folderName+".zip");//"/Users/macbookpro/Desktop/StageMemoireSignArt/testChiffrement/dirCompressed.zip");
                FileOutputStream fos = new FileOutputStream(path+"/"+folderName+".zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                File fileToZip = new File(sourceFile);

                zipFile(fileToZip, fileToZip.getName(), zipOut);
                zipOut.close();
                fos.close();

                Path folderDeletePath = Paths.get(folderPath);
                deleteDirectoryRecursion(folderDeletePath);

                System.out.println("+++++++++++++++++++++++++++++++++++chiffremetn compression et supression repertoire done!+++++++++++++++++++++++++++++++");
                return zipName;
        }
     
     
        public byte[] getRealImage(ListeSelection_Oeuvres oeuvre) throws IOException{
            //BufferedImage image = ImageIO.read(new File(PATHTEST+"\\images\\"+oeuvre.getNomOeuvre()+".jpg"));
            BufferedImage image = ImageIO.read(new File(PATH+"/"+oeuvre.getNomOeuvre()+".jpg"));
            byte[] imageByte = toByteArray(image,"jpg");          
            return imageByte;
        }
        
        private byte[] toByteArray(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
        
         
    }
     
   
        

}

