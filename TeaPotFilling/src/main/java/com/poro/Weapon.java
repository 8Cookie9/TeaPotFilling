package com.poro;

import com.datahandling.GetData;

public class Weapon{
    private final String type;
    private final String filepath;
    private int level;
    private int exp;
    
    public Weapon(String type, String filepath, int exp){
        this.type=type;
        this.filepath=filepath;
        this.level=0;
        this.levelUp(exp);
    }
    
    public String getType(){
        return this.type;
    }
    
    public int levelUp(int exp){
        if(level==1&&exp>=10){
            this.level++;
            return levelUp(exp-10);
        }else if(level==2&&exp>=15){
            this.level++;
            return levelUp(exp-15);
        }else if(level==3&&exp>=20){
            this.level++;
            return levelUp(exp-20);
        }else if(level==4&&exp>=25){
            this.level++;
            return levelUp(exp-25);
        }else if(level<7&&exp>=30){
            this.level++;
            return levelUp(exp-30);
        }else if(level<9&&exp>=40){
            this.level++;
            return levelUp(exp-40);
        }else if(level<12&&exp>=60){
            this.level++;
            return levelUp(exp-60);
        }else if(level<15&&exp>=90){
            this.level++;
            return levelUp(exp-90);
        }else if(level<20&&exp>=15){
            this.level++;
            return levelUp(exp-15);
        }else if(level<30&&exp>=275){
            this.level++;
            return levelUp(exp-300);
        }else{
            return exp;
        }
    }
    
    @Override
    public String toString(){
        GetData get = new GetData(this.filepath+"/"+this.type+".txt");
        String n=this.type;
        for(String s:get.data()){
            if(Integer.parseInt(s.split(";")[0])==this.level){
                n=s.split(";")[1];
            }
        }
        return n;
    }
}
