package sn.modelsis.signart.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class AESUtil {

    public static String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }
    private static byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public  static String encrypt(String algorithm, String plain, String key, String ivParam) {
        try {
            IvParameterSpec iv = generateIv(ivParam);
            SecretKeySpec skeySpec = generateKey(key);

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(plain.getBytes());
            return encode(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public  static String decrypt( String algorithm, String encrypted, String key, String ivParam) {
        try {
            IvParameterSpec iv = generateIv(ivParam);
            SecretKeySpec skeySpec = generateKey(key);

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static SecretKeySpec generateKey(String key) throws UnsupportedEncodingException {

        return new SecretKeySpec(key.getBytes("UTF-8"), "AES");
    }

    public static IvParameterSpec generateIv(String iv) throws UnsupportedEncodingException {
        return new IvParameterSpec(iv.getBytes("UTF-8"));
    }
}
