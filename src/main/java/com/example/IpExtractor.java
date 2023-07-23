package com.example;
public class IpExtractor{

    public static void main(String[] args){
      String ip  = "c0 a8 b2 38";
      String res =  hexToIp(ip);
        System.out.println(res);
       
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
          String ipnum = hex.replaceAll(" ","");  
    for (int j = 0; j < ipnum.length(); j+=2) {
        String sub = ipnum.substring(j, j+2);
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