package sn.modelsis.signart.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.codec.digest.DigestUtils;

@ApplicationScoped
public class PasswordEncoder {

    /**
     *
     * Méthode encodePassword.<br>
     * Rôle : permet d'encoder un mot de passe
     *
     * @param password
     * @param salt
     * @return
     */
    public String encodePassword(final String password, final String salt) {
        //return DigestUtils.md5Hex(password + salt);
        return DigestUtils.sha256Hex(password + salt);
    }

    /*public String encodeOldPassword(final String password) throws NoSuchAlgorithmException {
        byte[] uniqueKey = password.getBytes();
        byte[] hash;
        hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }*/
}
