package com.poro;

import java.util.Random;

public class EquipmentFactory {
    
    public EquipmentFactory(){
        
    }
    
    public Equipment newEquipment(String name){
        int hpMin=0;
        int atkMin=0;
        int defMin=0;
        int hpMax=0;
        int atkMax=0;
        int defMax=0;
        if(name.equals("sword")){
            atkMin=10;
            atkMax=20;
        }
        return new Equipment(name, getStat(hpMin, hpMax), getStat(atkMin, atkMax), getStat(defMin, defMax));
    }
    
    private int getStat(int min, int max){
        Random r=new Random();
        return (int) Math.round(((r.nextGaussian()+1)/2)*(max-min)+min);
    }
}
