package com.poro;

public class Level {
    private int exp;
    private int hp;
    private int defense;
    private int attack;
    private int hpIV;
    private int defenseIV;
    private int attackIV;
    private int hpModifier;
    private int defenseModifier;
    private int attackModifier;
    private int level;
    private int levelGain;
    private int totalExp;
    
    public Level(int hpIV, int defIV, int atkIV, int hpmod, int defmod, int atkmod, int levelgain){
        this.exp=0;
        this.totalExp=0;
        this.level=1;
        this.hpIV=hpIV;
        this.defenseIV=defIV;
        this.attackIV=atkIV;
        this.hp=hpIV;
        this.defense=defIV;
        this.attack=atkIV;
        this.hpModifier=hpmod;
        this.defenseModifier=defmod;
        this.attackModifier=atkmod;
        this.levelGain=levelgain;
    }
    
    public int expForNextLevel(){
        return (int) (this.levelGain*Math.pow(2,this.level));
    }
    
    public int getTotalExp(){
        return this.totalExp;
    }
    
    public void addExp(int experience){
        this.exp+=experience;
        this.totalExp+=experience;
        while(this.exp>=this.expForNextLevel()){
            this.exp-=this.expForNextLevel();
            this.level++;
            this.hp+=this.hpModifier;
            this.attack+=this.attackModifier;
            this.defense+=this.defenseModifier;
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

    public int getExp() {
        return exp;
    }

    public int getHpModifier() {
        return hpModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getLevelGain() {
        return levelGain;
    }

    public int getHpIV() {
        return hpIV;
    }

    public int getDefenseIV() {
        return defenseIV;
    }

    public int getAttackIV() {
        return attackIV;
    }
}
