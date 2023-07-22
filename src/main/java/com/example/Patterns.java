package com.example;

public enum Patterns {
    SSH("00 16.{25}80 02|01 bb.{31}50 18"),
    MODE("TCP|HTTP"),
    SRCIP("source-ip (\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})"),
    DSTIP("dest-ip (\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})"),
    SRCPORT("source-port (\\d{1,5})"), 
    DSTPORT("dest-port (\\d{1,5})");

    private String content;
    Patterns(String content){
        this.content = content;
    }
    String getText(){
        return this.content;
    }
}
