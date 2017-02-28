package com.poro;

import com.datahandling.GetData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PoroFactory {
    private String filepath;
    private HashMap<Integer, List<String[]>> poroes;
    
    public PoroFactory(String filepath){
        this.filepath=filepath;
        this.getPoroes();
    }
    
    private void getPoroes(){
        GetData get=new GetData(this.filepath);
        for(String s:get.data()){
            String args[]=s.split(";");
            int rarity=Integer.parseInt(args[1]);
            if(!this.poroes.containsKey(rarity)){
                this.poroes.put(rarity, new ArrayList<>());
            }
            this.poroes.get(rarity).add(args);
        }
    }
    
    public int randomRarity(int luck){
        Random random=new Random();
        int r=random.nextInt(10000);
        if(r<=0+luck){
            return 5;
        }else if(r<=10+luck){
            return 4;
        }else if(r<=100+luck){
            return 3;
        }else if(r<=1000+luck){
            return 2;
        }else{
            return 1;
        }
    }
    
    public Poro getPoro(int luck){
        int rarity=this.randomRarity(luck);
        Random random=new Random();
        List<String[]> porolist=this.poroes.get(rarity);
        String[] poroInfo=porolist.get(random.nextInt(porolist.size()));
        Poro poro=new Poro(poroInfo[0],0,0,0,Integer.parseInt(poroInfo[3]),Integer.parseInt(poroInfo[4]),Integer.parseInt(poroInfo[5]),Integer.parseInt(poroInfo[2]));
        return poro;
    }
}
