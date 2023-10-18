package com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.api.SendEntry;

public class TestApi {
    public static void main(String[] args) throws IOException{
        apiLoadTest();
    }
    public static void apiLoadTest() throws IOException{
        for(int i=0;i<1500;i++){
        String time = new SimpleDateFormat("HH_mm_ss").format(Calendar.getInstance().getTime());
        String timeStamp = ""+time;
        SendEntry.sendEntry("CVE-2023-1237","Server-Zugriff-Test",timeStamp);
    }
}
}
