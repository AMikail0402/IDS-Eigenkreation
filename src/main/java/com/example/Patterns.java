package com.example;

public enum Patterns {
    SSH("00 16.{25}80 02|01 bb.{31}50 18");

    private String content;
    Patterns(String content){
        this.content = content;
    }
    String getText(){
        return this.content;
    }
}
