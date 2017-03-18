package com.poro;

public class User {
    private String username;
    private Poro poro;
    private Poro secondaryPoro;
    private Equipment equipment;
    private int headgearDef;
    private int miscHp;
    
    public User(String username){
        this.username=username;
        this.headgearDef=0;
        this.miscHp=0;
    }
    
    public void addMisc(int hp){
        this.miscHp+=hp;
        this.poro.addMisc(hp);
        if(this.hasSecondary()){
            this.secondaryPoro.addMisc(hp);
        }
    }
    
    public void addHeadgear(int def){
        this.headgearDef+=def;
        this.poro.addHeadgear(def);
        if(this.hasSecondary()){
            this.secondaryPoro.addHeadgear(def);
        }
    }
    
    public int getHeadgearDef(){
        return this.headgearDef;
    }
    
    public int getMiscHp(){
        return this.miscHp;
    }
    
    public void setEquipment(Equipment w){
        this.equipment=w;
    }
    
    public Equipment getEquipment(){
        return this.equipment;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public Poro getPoro(){
        return this.poro;
    }
    
    public Poro getSecondaryPoro(){
        return this.secondaryPoro;
    }
    
    public boolean hasSecondary(){
        return this.secondaryPoro!=null;
    }
    
    public void newPoro(Poro newPoro){
        if(this.poro==null){
            this.poro=newPoro;
        }else{
            this.secondaryPoro=newPoro;
        }
    }
    
    public void swapPoros(){
        if(this.poro!=null&&this.secondaryPoro!=null){
            Poro p=this.poro;
            this.poro=this.secondaryPoro;
            this.secondaryPoro=p;
        }
    }
}
