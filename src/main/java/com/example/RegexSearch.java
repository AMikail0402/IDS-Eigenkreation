package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearch{
    public static void main(String[] args){
        boolean contains = search("08 00 27 14 13 e9 0a 00 27 00 00 0b 08 00 45 00 00 34 e5 4d 40 00 80 06 23 bd c0 a8 38 01 c0 a8 38 67 f1 cb 00 16 de 6f 70 a0 00 00 00 00 80 02 fa f0 41 74 00 00 02 04 05 b4 01 03 03 08 01 01 04 02","(00 16) ........................80 02");
        System.out.println(contains);
    }
    public static boolean search(String input,String searchPattern){
        
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(input);
        
        if(matcher.find()){
            return true;
        }
        
        return false;
    };
  

   
    public static String match(String input,String searchPattern){
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(input);
        String match;

        if(matcher.find()){
            if(matcher.groupCount()>0){
            match = matcher.group(1);
            return match;
        }
            else{
                match = matcher.group(0);
                return match;
            }
           
        }
    
       
       return "";
   }
}