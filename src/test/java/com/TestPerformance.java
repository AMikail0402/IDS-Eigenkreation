package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import com.example.RegexSearch;
import com.example.Rulegenerator;
import com.example.api.SendEntry;

public class TestPerformance {

    public static void main(String[] args) throws PcapNativeException, NotOpenException, FileNotFoundException{
        File file = new File("rules.conf");
        Scanner scan = new Scanner(file);
         String customRule = scan.nextLine();
         System.out.println(customRule);
            offlineAnalysis(customRule);

    }

    public static void offlineAnalysis(String rule) throws PcapNativeException, NotOpenException {

     final String pattern = Rulegenerator.totalRule(rule);
     System.out.println(pattern);   
    PcapHandle handle;
    try {
        handle = Pcaps.openOffline("test.pcap", TimestampPrecision.NANO);
    } catch (PcapNativeException e) {
        handle = Pcaps.openOffline("test.pcap");
    }
    final PcapHandle handle2 = handle;
    final PcapDumper dumper = handle.dumpOpen("suspicious.pcap");
    
    final String cve ="2023-1810";
    final String msg ="unerlaubte SSH-Anmeldung";

     PacketListener listener = new PacketListener() {
           
            @Override
            public void gotPacket(Packet packet) {
               
            
                // Print packet information to screen
                
                System.out.println(packet.toString());
                boolean keyword = RegexSearch.search(packet.toString(),pattern);
                
                if(keyword == true){
                //Send DB Entry
                try {
                    SendEntry.sendEntry(cve,msg,handle2.getTimestamp().toString());
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }    
                // Dump packets to file 
                System.out.println("!!! Match !!!");
                System.out.println(handle2.getTimestamp());
                System.out.println(packet);
                 
                    try {
                        dumper.dump(packet, handle2.getTimestamp());
                    } catch (NotOpenException e) {
                        e.printStackTrace();
                    }
                     
              
                }
               
            
            }
        };
    
       long startTime = System.currentTimeMillis();
       long elapsedTime = 0L;
        try {
            int maxPackets = 200;
            handle.loop(maxPackets, listener);
             } catch (InterruptedException e) {
            e.printStackTrace();
        }

            handle.close();
            
            elapsedTime = new Date().getTime()-startTime;
            System.out.println("Das Auslesen hat "+elapsedTime+"ms lang gedauert");
            System.out.println();
}

}
