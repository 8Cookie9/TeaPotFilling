package com.poro;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private Poro poro;
    private Poro secondaryPoro;
    private Equipment equipment;
    private List<Headgear> headgear;
    private List<Misc> misc;
    
    public User(String username){
        this.username=username;
        this.headgear=new ArrayList<>();
        this.misc=new ArrayList<>();
    }
    
    public void addMisc(Misc m){
        this.misc.add(m);
        this.poro.addMisc(m.getHp());
        if(this.hasSecondary()){
            this.secondaryPoro.addMisc(m.getHp());
        }
    }
    
    public void addHeadgear(Headgear h){
        this.headgear.add(h);
        this.poro.addMisc(h.getDef());
        if(this.hasSecondary()){
            this.secondaryPoro.addMisc(h.getDef());
        }
    }
    
    public List<Misc> getMisc(){
        return this.misc;
    }
    
    public List<Headgear> getHeadgear(){
        return this.headgear;
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
