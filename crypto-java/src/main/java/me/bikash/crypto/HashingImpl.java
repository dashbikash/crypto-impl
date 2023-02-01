/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.bikash.crypto;

import com.google.common.io.BaseEncoding;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author bikash
 */
public class HashingImpl {
    public static String hashing(String input,String algorithm) throws Exception{
        MessageDigest md= MessageDigest.getInstance(algorithm);
        byte[] hashbyte=md.digest(input.getBytes("utf-8"));
        String hash
            = BaseEncoding.base16().encode(hashbyte);
  
    return hash;
    }
    public static String hashingWithSalt(String input,String algorithm) throws Exception{
        byte[] salt={0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
        
        MessageDigest md= MessageDigest.getInstance(algorithm);
        md.update(salt);
        byte[] hashbyte=md.digest(input.getBytes("utf-8"));
        String hash
            = BaseEncoding.base16().encode(hashbyte);
 
    return hash;
    }
}
