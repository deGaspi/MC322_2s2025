package com.rpg.util;

public class Desistencia extends Exception {
    private static String msg;

    public Desistencia(){
        msg = "Desistiu";
    }

    public String getMessage(){
        return msg;
    }
}
