package com.poro;

import com.datahandling.GetData;
import java.util.Random;

public class EquipmentFactory {
    
    public EquipmentFactory(){
        
    }
    
    public Equipment newEquipment(String name, int hpMin, int hpMax, int atkMin, int atkMax, int defMin, int defMax){
        return new Equipment(name, getStat(hpMin, hpMax), getStat(atkMin, atkMax), getStat(defMin, defMax));
    }
    
    private int getStat(int min, int max){
        Random r=new Random();
        return (int) Math.round(((r.nextGaussian()+1)/2)*(max-min)+min);
    }
    
    public Equipment equipmentFromFile(String filepath){
        GetData get = new GetData(filepath);
        String s=get.data().get(new Random().nextInt(get.data().size()));
        String[] args = s.split(";");
        return newEquipment(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
    }
}
