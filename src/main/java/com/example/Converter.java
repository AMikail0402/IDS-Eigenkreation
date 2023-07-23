package com.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    public static void main(String[] args){

                System.out.println(hextoDec(" fe bc"));

                
    }
    

    public static String convertIpToHexRule(String ip){
        String numPattern = "(\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})";
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(ip);

        String firstNum, secondNum, thirdNum, fourthNum;

        if(matcher.find()){
        //erstes Oktett    
        firstNum = matcher.group(1);
            //System.out.println("Als Nummer "+firstNum);
        firstNum = decToHex(firstNum);
            //System.out.println("Als Hex "+firstNum);
        //zweites Oktett
        secondNum = matcher.group(2);
            //System.out.println("Als Nummer "+secondNum);
        secondNum = decToHex(secondNum);
            //System.out.println("Als Hex "+secondNum);
        //drittes Oktett
        thirdNum = matcher.group(3);
            //System.out.println("Als Nummer "+thirdNum);
        thirdNum = decToHex(thirdNum);
            //System.out.println("Als Hex "+thirdNum);
        //viertes Oktett
        fourthNum = matcher.group(4);
            //System.out.println("Als Nummer "+fourthNum);
        fourthNum = decToHex(fourthNum);
            //System.out.println("Als Hex "+fourthNum);
            
        //Erstellung des Zusatzes
        String rule = firstNum+"(.|\\n)"+secondNum+"(.|\\n)"+thirdNum+"(.|\\n)"+fourthNum;
            //System.out.println(rule.toLowerCase());
        return rule.toLowerCase();
    }
            
        return "";
    }

    public static String convertPortToHexRule(String port){
        String result = decToHex(port);

        return result.toLowerCase();

    }

    public static String hextoDec(String hex){
        String res = hex.replaceAll("\\s","");
        int sum =0;
        sum = Integer.parseInt(res,16);
        String sumString =  Integer.toString(sum);
        return sumString;
    }

    public static String decToHex(String decNum){
        int dec = Integer.parseInt(decNum);
        int temp;
        String hexNum ="";
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(dec>0){
            temp=dec%16;
            hexNum=hex[temp]+hexNum;
            dec=dec/16;
        }

        if(hexNum.length()==1){

            return "0"+hexNum;
        }

        return hexNum;
    }
    
}
