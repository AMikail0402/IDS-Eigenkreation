package com.example;

import java.util.Scanner;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;


public class AnalyzePackets {

public static void main(String[] args) throws PcapNativeException, NotOpenException {
    PcapHandle handle;

    try {
        handle = Pcaps.openOffline("suspicious_online.pcap", TimestampPrecision.NANO);
    } catch (PcapNativeException e) {
        handle = Pcaps.openOffline("suspicious_online.pcap");
    }
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
                 
           
                System.out.println(handle2.getTimestamp());
                System.out.println(packet);

              
            }
        };
     
        try {
            int maxPackets = 100;
            handle.loop(maxPackets, listener);
             } catch (InterruptedException e) {
            e.printStackTrace();
                }

            handle.close();
  
}

}
