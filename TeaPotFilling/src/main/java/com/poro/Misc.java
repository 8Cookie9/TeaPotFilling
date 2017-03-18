package com.poro;

public class Misc {
    private String name;
    private int hp;
    
    public Misc(String name, int hp){
        this.name=name;
        this.hp=hp;;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    @Override
    public String toString(){
        return this.name +"("+this.hp+")";
    }
}

