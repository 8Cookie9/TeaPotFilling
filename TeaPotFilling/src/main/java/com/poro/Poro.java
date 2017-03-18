package com.poro;

public class Poro extends Level{
    private final String type;
    
    public Poro(String type, int hp, int atk, int def, int hpmod, int defmod, int atkmod, int levelgain){
        super(hp,def,atk,hpmod,defmod,atkmod,levelgain);
        this.type=type;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void gainExp(int exp){
        super.addExp(exp);
    }
    
    public void addMisc(int hp){
        super.addHpIV(hp);
    }
    
    public void addHeadgear(int def){
        super.addDefIV(def);
    }
}
