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
public class Hashing {
    public static String sha256(String input) throws Exception{
        MessageDigest md= MessageDigest.getInstance("SHA3-256");
        byte[] hashbyte=md.digest(input.getBytes("utf-8"));
        String hash
            = BaseEncoding.base16().encode(hashbyte);
  
        // Print and display the string
        System.out.println(hash);
    return hash;
    }
    public static String sha256WithSalt(String input) throws Exception{
        byte[] salt={0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
        
        MessageDigest md= MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashbyte=md.digest(input.getBytes("utf-8"));
        String hash
            = BaseEncoding.base16().encode(hashbyte);
  
        // Print and display the string
        System.out.println(hash);
    return hash;
    }
}
