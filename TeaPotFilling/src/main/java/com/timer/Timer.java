package com.timer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {
    private int ms;
    
    public Timer(int ms){
        this.ms=ms;
    }
    
    public void startWait(){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {}
    }
    
    public void changeInterval(int newms){
        this.ms=newms;
    }
}
