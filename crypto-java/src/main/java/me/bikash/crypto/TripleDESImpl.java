package me.bikash.crypto;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author bikash
 */
public class TripleDESImpl {

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("TripleDES");
        SecretKey key = keygen.generateKey();
        return key;
    }

    public static SecretKey generateKeyFromPassword(String password, String salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes("utf-8"), 65536);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "DESede");
        return secret;

    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[8];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    
    public static String encryptString(String algorithm,SecretKey secret,IvParameterSpec iv,String plaintext) throws Exception{
        Cipher cipher =Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secret,iv);
        byte[] ciphertext=cipher.doFinal(plaintext.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(ciphertext);
    }
    public static String decryptString(String algorithm,SecretKey secret,IvParameterSpec iv,String ciphertext) throws Exception{
        Cipher cipher =Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secret,iv);
        byte[] plaintext=cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(plaintext);
    }

}
