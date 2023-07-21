package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rulegenerator {
    
 

    public String mode; 

    public String searchPattern;
     /**File file = new File("rules.conf");
        Scanner scan = new Scanner(file);
        String rule = scan.nextLine();
        System.out.println("Das ist die Regel "+rule);
        System.out.println("Das ist die Source-IP"+ ruleGenerator(rule));
        TCP source-ip 192.168.56.1 dest-ip 192.168.178.56.103 source-port any dest-port 22
        */


    public static void main(String[] args) throws FileNotFoundException{
    String totalRule = "";

     String rule = "TCP source-ip 192.168.56.1 dest-ip 192.168.56.103 source-port any dest-port 22";
     String srcIp = Rulegenerator.sourceIpPattern(rule);    
     String dstIp = Rulegenerator.destIpPattern(rule);   
     
     totalRule = srcIp + dstIp;

     totalRule = totalRule.toLowerCase();

     System.out.println("GesamtRegel "+totalRule);

    }

    public static String sourceIpPattern(String input){   

    String match = RegexSearch.match(input,Patterns.SRCIP.getText());
  
    if(match.equals("")){
        return "(.|\\n)*..(.|\\n)..(.|\\n)..(.|\\n)..";
    }
    match = Converter.convertIPtoHexRule(match); 
     return match;
    }

    public static String destIpPattern(String input){   
     String match = RegexSearch.match(input,Patterns.DSTIP.getText());
     
     if(match.equals("")){
        return "(.|\\n)*..(.|\\n)..(.|\\n)..(.|\\n)..";
    }
     match = Converter.convertIPtoHexRule(match); 


     return match;
    }

    public static String ruleGen(String input){
         System.out.println("Source-IP: "+sourceIpPattern(input));

        return "";
    }

}
