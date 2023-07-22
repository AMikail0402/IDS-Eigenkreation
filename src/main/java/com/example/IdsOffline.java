package com.example;
import java.util.Date;


import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.packet.Packet;

public class IdsOffline{

    public static void offlineAnalysis(String rule) throws PcapNativeException, NotOpenException {

        final String pattern = Rulegenerator.totalRule(rule);
        System.out.println(pattern);   
       PcapHandle handle;
       try {
           handle = Pcaps.openOffline("out.pcap", TimestampPrecision.NANO);
       } catch (PcapNativeException e) {
           handle = Pcaps.openOffline("out.pcap");
       }
       final PcapHandle handle2 = handle;
       final PcapDumper dumper = handle.dumpOpen("suspicious.pcap");
      
        PacketListener listener = new PacketListener() {
              
               @Override
               public void gotPacket(Packet packet) {
                  
               
                   // Print packet information to screen
                   
                   
                   boolean keyword = RegexSearch.search(packet.toString(),pattern);
                   
                   if(keyword == true){
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
            int maxPackets = (int)(Math.pow(10, 5));
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