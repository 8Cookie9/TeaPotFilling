package com.poro;

public class Level {
    private int exp;
    private int hp;
    private int defense;
    private int attack;
    private int hpModifier;
    private int defenseModifier;
    private int attackModifier;
    private int level;
    private int levelGain;
    
    public Level(int hp, int def, int atk, int hpmod, int defmod, int atkmod, int levelgain){
        this.exp=0;
        this.level=1;
        this.hp=hp;
        this.defense=def;
        this.attack=atk;
        this.hpModifier=hpmod;
        this.defenseModifier=defmod;
        this.attackModifier=atkmod;
        this.levelGain=levelgain;
    }
    
    public int expForNextLevel(){
        return (int) (this.levelGain*Math.pow(2,this.level));
    }
    
    public void addExp(int experience){
        this.exp+=experience;
        while(this.exp>=this.expForNextLevel()){
            this.exp-=this.expForNextLevel();
            this.level++;
        }
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public int getHP(){
        return this.hp;
    }
    
    public int getDefense(){
        return this.defense;
    }
    
    public int getAttack(){
        return this.attack;
    }
}
