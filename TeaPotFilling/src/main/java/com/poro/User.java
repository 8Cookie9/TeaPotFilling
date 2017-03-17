package com.poro;

public class User {
    private String username;
    private Poro poro;
    private Poro secondaryPoro;
    private Weapon weapon;
    
    public User(String username){
        this.username=username;
    }
    
    public void setWeapon(Weapon w){
        this.weapon=w;
    }
    
    public Weapon getWeapon(){
        return this.weapon;
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
