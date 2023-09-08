package com;

import com.example.Rulegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRuleGenerator {

    static String tcpTestPacket ="08 00 27 14 13 e9 0a 00 27 00 00 0b 08 00 45 00\n" + //
            "00 28 b9 4d 40 00 80 06 4f c9 c0 a8 38 01 c0 a8\n" + //
            "38 67 f3 ca 00 16 a8 03 18 0d cf f9 67 84 50 10\n" + //
            "03 fe ce ad 00 00";         

    static String httpTestPacket = "08 00 27 f5 94 77 08 5b d6 07 f1 57 08 00 45 00\n"+
    "02 79 e2 95 40 00 80 06 2f ca c0 a8 b2 8d c0 a8\n"+
    "b2 40 c8 a6 00 50 1e 3a 06 42 c3 f5 a9 54 50 18\n"+
    "10 01 d4 9d 00 00 47 45 54 20 2f 20 48 54 54 50\n"+
    "2f 31 2e 31 0d 0a 48 6f 73 74 3a 20 31 39 32 2e";
    
    static String testTcpRule = "TCP source-ip 192.168.56.1 dest-ip 192.168.56.103 source-port 62410 dest-port 22";

    static String testHttpRule = "HTTP source-ip any dest-ip any source-port any dest-port 80";        

    public static void main(String[] args) throws FileNotFoundException{

        testMatching();
    }
  
    public static void testMatching(){

        System.out.println("///////////////////////// TEST - 1 ///////////////////////////////\n");
        String tcpPattern = Rulegenerator.totalRule(testTcpRule);
        Pattern pattern1 = Pattern.compile(tcpPattern);
        System.out.println(tcpPattern);
      
        Matcher matcher1 = pattern1.matcher(tcpTestPacket);
        System.out.println(tcpTestPacket);
       

        if(matcher1.find()){
            System.out.println("Erster Test war erfolgreich");
        }
        else{
            System.out.println("Erster Test ist fehlgeschlagen");
        }
        System.out.println(matcher1.group(1));
        System.out.println("\n/////////////////////////          ///////////////////////////////");

        System.out.println("///////////////////////// TEST - 2 ///////////////////////////////\n");
        String httpPattern = Rulegenerator.totalRule(testHttpRule);
        System.out.println(httpPattern);
        System.out.println(httpPattern);
        Pattern pattern2 = Pattern.compile(httpPattern);
       System.out.println(httpTestPacket);
        Matcher matcher2 = pattern2.matcher(httpTestPacket);
      
        
        if(matcher2.find()){
            System.out.println("Zweiter Test war erfolgreich");
        }
        else{
            System.out.println("Zweiter Test ist fehlgeschlagen");
        }
        System.out.println(matcher2.group(1));

        System.out.println("\n/////////////////////////          ///////////////////////////////\n");
        
    }



}
