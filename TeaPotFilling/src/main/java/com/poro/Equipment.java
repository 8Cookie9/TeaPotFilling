package com.poro;

public class Equipment {
    private String name;
    private String quality;
    private int hp;
    private int atk;
    private int def;
    
    public Equipment(String name, int hp, int atk, int def){
        this.name=name;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.quality=getQuality();
    }
    
    private String getQuality(){
        String part1="";
        String part2="";
        if(hp+atk+def>=100){
            part1="legendary";
        }else if(hp+atk+def>=75){
            part1="epic";
        }else if(hp+atk+def>=35){
            part1="good";
        }else if(hp+atk+def>=15){
            part1="";
        }else if(hp+atk+def>=0){
            part1="awful";
        }else{
            part1="cursed";
        }
        
        if(hp==atk&&atk==def){
            part2="balanced";
        }else if(hp==Math.max(atk, Math.max(hp, def))&&hp/(def+atk)>0.6){
            part2="soft";
        }else if(atk==Math.max(atk, Math.max(hp, def))&&atk/(def+hp)>0.6){
            part2="sharp";
        }else if(def==Math.max(atk, Math.max(hp, def))&&def/(hp+atk)>0.6){
            part2="hard";
        }
        return part1+" "+part2;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    public int getDef(){
        return this.def;
    }
    
    public int getAtk(){
        return this.atk;
    }
}
