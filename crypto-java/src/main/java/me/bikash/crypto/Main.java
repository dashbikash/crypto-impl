/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package me.bikash.crypto;

import com.google.common.io.BaseEncoding;
import java.security.KeyPair;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import static me.bikash.crypto.HashingImpl.*;

/**
 *
 * @author bikash
 */
public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("Hashing:\n--------------------");
        System.out.println("SHA-256 :" + hashing("Bikash", "SHA-256"));
        System.out.println("SHA3-256 :" + hashing("Bikash", "SHA3-256"));
        System.out.println("MD5 :" + hashing("Bikash", "MD5"));
        System.out.println("\nHashing with salt:\n--------------------");
        System.out.println("SHA-256 :" + hashingWithSalt("Bikash", "SHA-256"));
        System.out.println("SHA3-256 :" + hashingWithSalt("Bikash", "SHA3-256"));
        System.out.println("MD5 :" + hashingWithSalt("Bikash", "MD5"));
        
        System.out.println("\nAES Implimentation:\n--------------------"); 
        System.out.println("AES Key :" +BaseEncoding.base16().encode(AESImpl.generateKey(256).getEncoded()));
        SecretKey aesKeyFromPassword=AESImpl.generateKeyFromPassword("password","salt");
        IvParameterSpec randomIv=AESImpl.generateIv();
        String enctext=AESImpl.encryptString("AES/CBC/PKCS5Padding", aesKeyFromPassword, randomIv, "bikash") ;
        String dectext=AESImpl.decryptString("AES/CBC/PKCS5Padding", aesKeyFromPassword, randomIv, enctext) ;
        System.out.println("AES Key from password :" +BaseEncoding.base16().encode(aesKeyFromPassword.getEncoded()));
        System.out.println("AES encrypted text of (bikash) :" +enctext);
        System.out.println("AES decrypted text :" +dectext);
        
        System.out.println("\nDES Implimentation:\n--------------------"); 
        SecretKey desKeyFromPassword=DESImpl.generateKeyFromPassword("password", "salt");
        randomIv=DESImpl.generateIv();
        enctext=DESImpl.encryptString("DES/CBC/PKCS5Padding", desKeyFromPassword, randomIv, "bikash") ;
        dectext=DESImpl.decryptString("DES/CBC/PKCS5Padding", desKeyFromPassword, randomIv, enctext) ;
        System.out.println("DES Key from password :" +BaseEncoding.base64().encode(desKeyFromPassword.getEncoded()));
        System.out.println("DES encrypted text of (bikash) :" +enctext);
        System.out.println("DES decrypted text :" +dectext);
        
        System.out.println("\nTriple DES Implimentation:\n--------------------"); 
        SecretKey threeDesKeyFromPassword=TripleDESImpl.generateKeyFromPassword("password0000000000000000","salt");
        randomIv=TripleDESImpl.generateIv();
        enctext=TripleDESImpl.encryptString("TripleDES/CBC/PKCS5Padding", threeDesKeyFromPassword, randomIv, "bikash") ;
        dectext=TripleDESImpl.decryptString("TripleDES/CBC/PKCS5Padding", threeDesKeyFromPassword, randomIv, enctext) ;
        System.out.println("Triple DES Key from password :" +BaseEncoding.base64().encode(threeDesKeyFromPassword.getEncoded()));
        System.out.println("Triple DES encrypted text of (bikash) :" +enctext);
        System.out.println("Triple DES decrypted text :" +dectext);
        
        System.out.println("\nRSA Implimentation:\n--------------------");
        KeyPair keyPair=RSAImpl.keyPairGenerator();
        System.out.println("RSA Public Key: "+BaseEncoding.base64().encode(keyPair.getPublic().getEncoded()));
        System.out.println("RSA Private Key:"+BaseEncoding.base64().encode(keyPair.getPrivate().getEncoded()));
        enctext=RSAImpl.encryptString(keyPair.getPublic(), "bikash");
        dectext=RSAImpl.decryptString(keyPair.getPrivate(), enctext);
        System.out.println("RSA encrypted text of (bikash) :" +enctext);
        System.out.println("RSA decrypted text :" +dectext);
        
        
        
    }
}
