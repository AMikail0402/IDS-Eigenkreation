package com.example;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;


public class AnalyzePackets {

public static void analyze(String rule) throws PcapNativeException, NotOpenException, InterruptedException, FileNotFoundException {
    PcapHandle handle = null;
    final  Pattern pattern = Pattern.compile(Rulegenerator.totalRule(rule));
    System.out.println(pattern);
    
    Scanner myScanner = new Scanner(System.in);
    System.out.println("Offline(off) oder Online(on) Ergebnisse ?");
    String answer = myScanner.nextLine();
    
    if(answer.equals("on"))
    try {
        handle = Pcaps.openOffline("suspicious_online.pcap", TimestampPrecision.NANO);
    } catch (PcapNativeException e) {
        handle = Pcaps.openOffline("suspicious_online.pcap");
    }

    else if(answer.equals("off")){
        try {
        handle = Pcaps.openOffline("suspicious.pcap", TimestampPrecision.NANO);
    } catch (PcapNativeException e) {
        handle = Pcaps.openOffline("suspicious.pcap");
    }
    }
    final PcapHandle handle2 = handle;

     PacketListener listener = new PacketListener() {
            @Override
            public void gotPacket(Packet packet) {
                System.out.println("Zeitpunkt: "+handle2.getTimestamp());
                StringBuilder packetToAn = new StringBuilder();
                
                    packetToAn.append(packet.toString());
                
                String packetMatch = packetToAn.toString();
                Matcher matcher = pattern.matcher(packetMatch);
                 matcher.find();
               
                 String srcAddr = matcher.group(1);
                 String dstAddr = matcher.group(2);
                 String srcPort = matcher.group(3);
                 String dstPort = matcher.group(4);
                

                System.out.println("Ursprungs-Adresse: "+IpExtractor.hexToIp(srcAddr));
                System.out.println("Ziel-Adresse: "+IpExtractor.hexToIp(dstAddr));
                System.out.println("Ursprungsport: "+Converter.hextoDec(srcPort));
                System.out.println("Zielport: "+Converter.hextoDec(dstPort));
                System.out.println(packetMatch);
              
            }
        };
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        try {
            int maxPackets = (int)(Math.pow(10, 5));
            handle.loop(maxPackets, listener);
             } catch (InterruptedException e) {
            e.printStackTrace();
                }

           handle.close();
            elapsedTime = new Date().getTime()-startTime;
            System.out.println("Das Analysieren hat "+elapsedTime+"ms lang gedauert");
           
  
}

}