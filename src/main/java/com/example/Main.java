package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

public class Main {
    public static void main(String[] args) throws PcapNativeException, NotOpenException, InterruptedException, FileNotFoundException{
           userLoop(0);
    }
    public static void userLoop(int num) throws PcapNativeException, NotOpenException, InterruptedException, FileNotFoundException{
        File file = new File("rules.conf");
        Scanner scan = new Scanner(file);
         String customRule = scan.nextLine();

    if(num==0){
    System.out.println("Was wollen sie tun? Online-Filtering: on; Offline-Filtering: off; Auswertung: analysis");}
    
    if(num==1){
    System.out.println("Was wollen sie jetzt tun? Online-Filtering: on; Offline-Filtering: off; Auswertung: analysis");}
    Scanner myScanner = new Scanner(System.in);
    String answer = myScanner.nextLine().toLowerCase();
    
    if(answer.equals("on")){
        IdsOnline.onlineAnalysis(customRule);
    }
    else if(answer.equals("off")){
        IdsOffline.offlineAnalysis(customRule);
    }
   
    else if(answer.equals("analysis")){
        AnalyzePackets.analyze();
    }
   
    }
    }