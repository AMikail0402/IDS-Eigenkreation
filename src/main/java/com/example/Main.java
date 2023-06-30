package com.example;

import java.util.Scanner;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

public class Main {
    public static void main(String[] args) throws PcapNativeException, NotOpenException, InterruptedException{
        userLoop(0);
    }
    public static void userLoop(int num) throws PcapNativeException, NotOpenException, InterruptedException{
        if(num==0){
    System.out.println("Was wollen sie tun? Online-Filtering: on; Offline-Filtering: off; Auswertung: analysis");}
    if(num==1){
    System.out.println("Was wollen sie jetzt tun? Online-Filtering: on; Offline-Filtering: off; Auswertung: analysis");}
    Scanner myScanner = new Scanner(System.in);
    String answer = myScanner.nextLine().toLowerCase();
    if(answer.equals("on")){
        IdsOnline.onlineAnalysis();
    }
    else if(answer.equals("off")){
        IdsOffline.offlineAnalysis();
    }
    else if(answer.equals("analysis")){
        AnalyzePackets.analyze();
    }
    }
    }