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

public static void main(String[] args) throws PcapNativeException, NotOpenException {
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
                 
              //  long packetStartTime = System.currentTimeMillis();
              //  long packetElapsedTime= 0L;
                
                // Print packet information to screen
                System.out.println(handle2.getTimestamp());
                System.out.println(packet);
                boolean keyword = RegexSearch.search(packet.toString(), Patterns.SSH.getText());
                if(keyword == true){
                // Dump packets to file
                System.out.println("!!! Anmeldeversuch !!!");
                    try {
                        dumper.dump(packet, handle2.getTimestamp());
                    } catch (NotOpenException e) {
                        e.printStackTrace();
                    }
                }
               // packetElapsedTime = new Date().getTime()-packetStartTime;
             
              //  System.out.println("Das Auslesen dieses Pakets hat "+packetElapsedTime+"ms lang gedauert");
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
}

}








