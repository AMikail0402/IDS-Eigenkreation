package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rulegenerator {
    
 
    public static void main(String[] args) throws FileNotFoundException{
        String rule = Rulegenerator.totalRule("HTTP source-ip 192.168.178.141 dest-ip 192.168.178.141 source-port 62070 dest-port 80");
        System.out.println(rule);
     
    }

    public static String totalRule(String rule){
    String mode = Rulegenerator.mode(rule);
 
     String modePattern = Rulegenerator.modePattern(mode);

     String srcIp = Rulegenerator.sourceIpPattern(rule);    
     String dstIp = Rulegenerator.destIpPattern(rule); 

     String srcPort = Rulegenerator.sourcePortPattern(rule);
     String dstPort = Rulegenerator.destPortPattern(rule);
     String totalrule = "";

    if(mode.equals("TCP")){

    totalrule = modePattern+srcIp+dstIp+srcPort+dstPort;
    return totalrule;

    }

    else if(mode.equals("HTTP")){    

     totalrule = "06(?:.|\\n){7}"+srcIp+dstIp+srcPort+dstPort+modePattern;
     return totalrule;

    }
    
    else{
        totalrule= srcIp+dstIp+srcPort+dstPort;
        return totalrule;
    }
   
    }

    public static String modePattern(String mode){
     
        if(mode.equals("TCP")){
           return "06(?:.|\\n){7}";
        }
        if(mode.equals("HTTP")){
            return "(?:.|\\n)*48(?:.|\\n)54(?:.|\\n)54(?:.|\\n)50(?:.|\\n)2f(?:.|\\n)31(?:.|\\n)2e(?:.|\\n)31";
        }
        return "";
    }

    public static String mode(String input){
        String match = RegexSearch.match(input,Patterns.MODE.getText());
        if(match == "TCP"){
           return "TCP";
        }
        if(match == "HTTP"){
            return "HTTP";
        }
        return match;
    }

    public static String sourcePortPattern(String input){
        String match = RegexSearch.match(input,Patterns.SRCPORT.getText());

        if(match.equals("")){
        return "((?:.|\\n){6})";
        }        

        match = Converter.convertPortToHexRule(match); 

        while(match.length()<4){
            match = "0"+match;
        }
        
        String frstHalf= match.substring(0,2);
        String secHalf = match.substring(2);
        match = frstHalf+" "+secHalf;
    
        return "("+match+") ";
    }

    public static String destPortPattern(String input){
        String match = RegexSearch.match(input,Patterns.DSTPORT.getText());

        if(match.equals("")){
        return "((?:.|\\n){6})";
        }        

        match = Converter.convertPortToHexRule(match); 

        while(match.length()<4){
            match = "0"+match;
        }

        String frstHalf= match.substring(0,2);
        String secHalf = match.substring(2);
      
        match = frstHalf+" "+secHalf;
    
        return "("+match+")";
    }

    public static String sourceIpPattern(String input){   

    String match = RegexSearch.match(input,Patterns.SRCIP.getText());
  
    if(match.equals("")){
        return "((?:.|\\n){12})";
    }
    match = Converter.convertIpToHexRule(match); 
    
     return match+" ";
    }

    public static String destIpPattern(String input){   
     String match = RegexSearch.match(input,Patterns.DSTIP.getText());
     
     if(match.equals("")){
        return "((?:.|\\n){12})";
    }
     match = Converter.convertIpToHexRule(match); 


     return "("+match+") ";
    }

    public static String ruleGen(String input){
         System.out.println("Source-IP: "+sourceIpPattern(input));

        return "";
    }

}
