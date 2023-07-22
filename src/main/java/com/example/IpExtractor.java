package com.example;
public class IpExtractor{

    public static void main(String[] args){
        String hex = "08 00 27 14 13 e9 0a 00 27 00 00 0b 08 00 45 00 00 34 e5 9f 40 00 80 06 23 6b c0 a8 38 01 c0 a8 38 67 f2 7a 00 16 a1 09 bf ab 00 00 00 00 80 02 fa f0 2f 20 00 00 02 04 05 b4 01 03 03 08 01 01 04 02";
        String exampleSrcPort = extractSrcPort(hex);
        String exampleDstPort = extractDstPort(hex);
        System.out.println("Quellpoert "+ exampleSrcPort);
        System.out.println("Zielport "+ exampleDstPort);
       
    }

    public static String extractDest(String hex){
        String res = RemoveWhiteSpace(hex);
        String addr = res.substring(60,68);
        addr= hexToIp(addr);
        return addr;
    }
    public static String extractSource(String hex){
        String res = RemoveWhiteSpace(hex);
        String addr = res.substring(52,60);
        addr= hexToIp(addr);
        return addr;
    }

    public static String extractSrcPort(String hex){
        String res = RemoveWhiteSpace(hex);
        String port = res.substring(68,72);
        port = Converter.hextoDec(port);
        return port;
    }
      public static String extractDstPort(String hex){
        String res = RemoveWhiteSpace(hex);
        String port = res.substring(72,76);
        port = Converter.hextoDec(port);
        return port;
    }
    public static String hexToIp(String hex){
         String ip= "";

    for (int j = 0; j < hex.length(); j+=2) {
        String sub = hex.substring(j, j+2);
        int num = Integer.parseInt(sub, 16);
        ip += num+".";
    }

    ip = ip.substring(0, ip.length()-1);
    return ip;
    }
    public static String RemoveWhiteSpace(String input){
       return input.replaceAll("\\s","");
    }
}