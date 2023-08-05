package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearch{

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