package com.poro;

public class Headgear {
    private final String name;
    private final int def;
    
    public Headgear(String name, int def){
        this.name=name;
        this.def=def;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getDef(){
        return this.def;
    }
    
    @Override
    public String toString(){
        return this.name +"("+this.def+")";
    }
}
