package me.bikash.crypto;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bikash
 */
public class RSAImpl { 
    public static KeyPair keyPairGenerator() throws Exception{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }
        
    public static String encryptString(PublicKey publicKey,String plaintext) throws Exception{
        Cipher cipher =Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] ciphertext=cipher.doFinal(plaintext.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(ciphertext);
    }
    public static String decryptString(PrivateKey privateKey,String ciphertext) throws Exception{
        Cipher cipher =Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plaintext=cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(plaintext);
    }

}
