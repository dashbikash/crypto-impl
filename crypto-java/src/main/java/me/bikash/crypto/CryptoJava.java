/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package me.bikash.crypto;

import java.util.Scanner;

/**
 *
 * @author bikash
 */
public class CryptoJava {

    public static void main(String[] args) throws Exception{
        System.out.println("Algorithms\n------------------");
        System.out.println("1. SHA-256");
        Scanner myinput = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter Option:");
        int x=myinput.nextInt();
        switch (x) {
            case 1:
                {
                    Hashing.sha256WithSalt("bikash");
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
