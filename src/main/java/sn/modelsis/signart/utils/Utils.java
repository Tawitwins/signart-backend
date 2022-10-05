package sn.modelsis.signart.utils;

import javax.ws.rs.core.MultivaluedMap;
import java.text.*;
import java.util.Random;

public class Utils {

    //static Logger logger = LoggerUtil.getLOGGER(Utils.class.getName());

    public Utils() {

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
    final String[] dizaineNames = {
            "",
            "",
            "vingt",
            "trente",
            "quarante",
            "cinquante",
            "soixante",
            "soixante",
            "quatre-vingt",
            "quatre-vingt"
    };
    final String[] uniteNames1 = {
            "",
            "un",
            "deux",
            "trois",
            "quatre",
            "cinq",
            "six",
            "sept",
            "huit",
            "neuf",
            "dix",
            "onze",
            "douze",
            "treize",
            "quatorze",
            "quinze",
            "seize",
            "dix-sept",
            "dix-huit",
            "dix-neuf"
    };

    String[] uniteNames2 = {
            "",
            "",
            "deux",
            "trois",
            "quatre",
            "cinq",
            "six",
            "sept",
            "huit",
            "neuf",
            "dix"
    };

    /**
     *
     * @param number
     * @return
     */
    String convertZeroToHundred(int number) {
        int laDizaine = number / 10;
        int lUnite = number % 10;
        String resultat = "";

        switch (laDizaine) {
            case 1 :
            case 7 :
            case 9 :
                lUnite = lUnite + 10;
                break;
            default:
        }
        // séparateur "-" "et"  ""
        String laLiaison = "";
        if (laDizaine > 1) {
            laLiaison = "-";
        }
        // cas particuliers
        switch (lUnite) {
            case 0:
                laLiaison = "";
                break;
            case 1 :
                if (laDizaine == 8) {
                    laLiaison = "-";
                }
                else {
                    laLiaison = " et ";
                }
                break;
            case 11 :
                if (laDizaine==7) {
                    laLiaison = " et ";
                }
                break;
            default:
        }

        // dizaines en lettres
        switch (laDizaine) {
            case 0:
                resultat = uniteNames1[lUnite];
                break;
            case 8 :
                if (lUnite == 0) {
                    resultat = dizaineNames[laDizaine];
                }
                else {
                    resultat = dizaineNames[laDizaine]
                            + laLiaison + uniteNames1[lUnite];
                }
                break;
            default :
                resultat = dizaineNames[laDizaine]
                        + laLiaison + uniteNames1[lUnite];
        }
        return resultat;
    }

    /**
     *
     * @param number
     * @return
     */
    String convertLessThanOneThousand(int number){
        int lesCentaines = number / 100;
        int leReste = number % 100;
        String sReste = convertZeroToHundred(leReste);
        String resultat;
        switch (lesCentaines) {
            case 0:
                resultat = sReste;
                break;
            case 1 :
                if (leReste > 0) {
                    resultat = "cent " + sReste;
                }
                else {
                    resultat = "cent";
                }
                break;
            default :
                if (leReste > 0) {
                    resultat = uniteNames2[lesCentaines] + " cent " + sReste;
                }
                else {
                    resultat = uniteNames2[lesCentaines] + " cents";
                }
        }
        return resultat;
    }

    /**
     *
     * @param number
     * @return
     */
   public String convertToLetter(long number) {
        // 0 à 999 999 999 999
        if (number == 0) { return "zéro"; }

        String snumber = Long.toString(number);

        // pad des "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        int lesMilliards = Integer.parseInt(snumber.substring(0,3));

        int lesMillions  = Integer.parseInt(snumber.substring(3,6));

        int lesCentMille = Integer.parseInt(snumber.substring(6,9));

        int lesMille = Integer.parseInt(snumber.substring(9,12));

        String tradMilliards;
        switch (lesMilliards) {
            case 0:
                tradMilliards = "";
                break;
            case 1 :
                tradMilliards = convertLessThanOneThousand(lesMilliards)
                        + " milliard ";
                break;
            default :
                tradMilliards = convertLessThanOneThousand(lesMilliards)
                        + " milliards ";
        }
        String resultat =  tradMilliards;

        String tradMillions;
        switch (lesMillions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(lesMillions)
                        + " million ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(lesMillions)
                        + " millions ";
        }
        resultat =  resultat + tradMillions;

        String tradCentMille;
        switch (lesCentMille) {
            case 0:
                tradCentMille = "";
                break;
            case 1 :
                tradCentMille = "mille ";
                break;
            default :
                tradCentMille = convertLessThanOneThousand(lesCentMille)
                        + " mille ";
        }
        resultat =  resultat + tradCentMille;

        String tradMille;
        tradMille = convertLessThanOneThousand(lesMille);
        resultat =  resultat + tradMille;

        return resultat;
    }

    public String generateReference(){
        return  new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
