package sn.modelsis.signart.utils;

import javax.ws.rs.core.MultivaluedMap;


public class Utils {

    //static Logger logger = LoggerUtil.getLOGGER(Utils.class.getName());

    private Utils() {

    }

    // Parse Content-Disposition header to get the original file name
    public static String parseFileName(final MultivaluedMap<String, String> headers) {
        final String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        for (final String name : contentDispositionHeader) {
            if (name.trim().startsWith("filename")) {
                final String[] tmp = name.split("=");
                return tmp[1].trim().replaceAll("\"", "");
            }
        }
        return "randomName";
    }

    /*public static String hashFileName(final String fileName, final String codePiece) {

        // Generation d'un nouveau nom du fichier
        final Hashids hashids = new Hashids(("referentiel-piece" + codePiece), 30);
        final String extension = FilenameUtils.getExtension(fileName);
        final int random = (int) (Math.random() * (1000001)); //random de 0 à 1000000
        final String basenameHash = hashids.encode(System.currentTimeMillis() + random);

        logger.debug("Basename Hash :> : " + basenameHash + " | Extension:> " + extension);

        return StringUtils.isEmpty(extension) ? basenameHash : basenameHash + "." + extension;
    }*/

}
