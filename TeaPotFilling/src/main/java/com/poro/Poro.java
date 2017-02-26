package com.poro;

public class Poro extends Level{
    private String type;
    private User user;
    
    public Poro(String type,int hp,int atk,int def, int hpmod, int defmod, int atkmod, int levelgain){
        super(hp,def,atk,hpmod,defmod,atkmod,levelgain);
        this.type=type;
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public String getType(){
        return this.type;
    }
}
