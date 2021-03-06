package com.poro;

public class Poro extends Level implements Comparable<Poro>{
    private final String type;
    
    public Poro(String type, int hp, int atk, int def, int hpmod, int atkmod, int defmod, int levelgain){
        super(hp,def,atk,hpmod,atkmod,defmod,levelgain);
        this.type=type;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void gainExp(int exp){
        super.addExp(exp);
    }
    
    public void addMisc(int hp){
        super.addHp(hp);
    }
    
    public void addHeadgear(int def){
        super.addDef(def);
    }
    
    public void addEquipmentLevel(int atk){
        super.addAtk(atk);
    }

    @Override
    public int compareTo(Poro o) {
        return this.type.compareTo(o.type);
    }
}
