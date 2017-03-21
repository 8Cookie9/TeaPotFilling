package com.poro;

public class Misc {
    private final String name;
    private final int hp;
    
    public Misc(String name, int hp){
        this.name=name;
        this.hp=hp;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    @Override
    public String toString(){
        return this.name +"("+this.hp+")";
    }
}

