package com.poro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String username;
    private Poro poro;
    private Poro secondaryPoro;
    private Equipment equipment;
    private List<Headgear> headgear;
    private List<Misc> misc;
    private List<Poro> storage;
    
    public User(String username){
        this.username=username;
        this.headgear=new ArrayList<>();
        this.misc=new ArrayList<>();
        this.storage=new ArrayList<>();
    }
    
    public void addMisc(Misc m){
        this.misc.add(m);
        this.poro.addMisc(m.getHp());
        if(this.hasSecondary()){
            this.secondaryPoro.addMisc(m.getHp());
            for(Poro p:this.storage){
                p.addMisc(m.getHp());
            }
        }
    }
    
    public void addHeadgear(Headgear h){
        this.headgear.add(h);
        this.poro.addHeadgear(h.getDef());
        if(this.hasSecondary()){
            this.secondaryPoro.addHeadgear(h.getDef());
            for(Poro p:this.storage){
                p.addHeadgear(h.getDef());
            }
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
        for(int i=1;i<=this.equipment.getLevel();i++){
            this.poro.addEquipmentLevel((int) Math.pow(1.5, 0.5*i));
            if(this.hasSecondary()){
                this.secondaryPoro.addEquipmentLevel((int) Math.pow(1.5, 0.5*i));
                for(Poro p:this.storage){
                    p.addEquipmentLevel((int) Math.pow(1.5, 0.5*i));
                }
            }
        }
    }
    
    public Equipment getEquipment(){
        return this.equipment;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public List<Poro> getStorage(){
        return this.storage;
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
        }else if(this.secondaryPoro==null){
            this.secondaryPoro=newPoro;
        }else{
            this.storage.add(newPoro);
        }
    }
    
    public void swapPoros(){
        if(this.poro!=null&&this.secondaryPoro!=null){
            Poro p=this.poro;
            this.poro=this.secondaryPoro;
            this.secondaryPoro=p;
        }
    }
    
    public void getPoro(int slot){
        if(this.storage.size()>slot){
            Poro p=this.secondaryPoro;
            this.secondaryPoro=this.storage.get(slot);
            this.storage.remove(slot);
            this.storage.add(p);
        }
    }
    
    public void deletePoro(int id){
        try{
            this.storage.remove(id);
        }catch(Exception e){}
    }
    
    public void sortPoros(){
        try{
            Collections.sort(this.storage);
        }catch(Exception e){}
    }
}
