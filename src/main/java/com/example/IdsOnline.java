package com.example;
/**
 * Hello world!
 *
 */

import java.io.IOException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.PcapStat;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;


public class IdsOnline
{

       public static void main(String[] args) throws PcapNativeException, NotOpenException
    {   
           PcapNetworkInterface device = getNetworkDevice();
            System.out.println("You chose: " + device);
            if(device == null ){
                System.out.println("No device chosen");
                System.exit(1);
            }
           
            final PcapHandle handle;
            // Open the Device and get a handle
            int snapshotLength = 65536;
            int readTimeout = 50;
            handle = device.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);
            final PcapDumper dumper = handle.dumpOpen("suspicious_online.pcap");

            // Create a listener that defines what to do with the received packets
            PacketListener listener = new PacketListener() {
                @Override
                public void gotPacket(Packet packet) {
                    // Print packet information to screen
                    System.out.println(handle.getTimestamp());
                    System.out.println(packet.toString());
                     boolean keyword = RegexSearch.search(packet.toString(), Patterns.SSH.getText());
                  
                     if(keyword == true){
                    // Dump packets to file
                    System.out.println("!!! Anmeldeversuch !!!");
                    try {
                        dumper.dump(packet, handle.getTimestamp());
                    } catch (NotOpenException e) {
                        e.printStackTrace();
                    }
                }

                }
            };


            try {
            int maxPackets = 100;
            handle.loop(maxPackets, listener);
             } catch (InterruptedException e) {
            e.printStackTrace();
                }
                PcapStat stats = handle.getStats();
            System.out.println("Packets received: " + stats.getNumPacketsReceived());
            System.out.println("Packets dropped: " + stats.getNumPacketsDropped());
            System.out.println("Packets dropped by interface: " + stats.getNumPacketsDroppedByIf());
            handle.close();

            }
        
        static PcapNetworkInterface getNetworkDevice() {
            PcapNetworkInterface device = null;
            try {
                device = new NifSelector().selectNetworkInterface();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return device;
        }

    
}