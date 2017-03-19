package com.poro;

import com.datahandling.GetData;

public class Equipment{
    private final String filepath;
    private int level;
    private int exp;
    private int totalExp;
    
    public Equipment(String filepath, int exp){
        this.filepath=filepath;
        this.level=1;
        this.exp=0;
        this.totalExp=exp;
        this.levelUp(exp);
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public int getExp(){
        return this.exp;
    }
    
    public int getTotalExp(){
        return this.totalExp;
    }
    
    public String getFilepath(){
        return this.filepath;
    }
    
    public String exp(){
        String xpToNext="0";
        if(level==1){
            xpToNext="10";
        }else if(level==2){
            xpToNext="15";
        }else if(level==3){
            xpToNext="20";
        }else if(level==4){
            xpToNext="25";
        }else if(level<7){
            xpToNext="30";
        }else if(level<9){
            xpToNext="40";
        }else if(level<12){
            xpToNext="60";
        }else if(level<15){
            xpToNext="90";
        }else if(level<20){
            xpToNext="275";
        }else if(level<30){
            xpToNext="300";
        }else{
            xpToNext="∞";
        }
        return "level "+this.level+" ("+this.exp+"/"+xpToNext+")";
    }
    
    public int levelUp(int experience){
        int xp=this.exp+experience;
        if(level==1&&xp>=10){
            this.level++;
            return levelUp(xp-10);
        }else if(level==2&&xp>=15){
            this.level++;
            return levelUp(xp-15);
        }else if(level==3&&xp>=20){
            this.level++;
            return levelUp(xp-20);
        }else if(level==4&&xp>=25){
            this.level++;
            return levelUp(xp-25);
        }else if(level<7&&xp>=30){
            this.level++;
            return levelUp(xp-30);
        }else if(level<9&&xp>=40){
            this.level++;
            return levelUp(xp-40);
        }else if(level<12&&xp>=60){
            this.level++;
            return levelUp(xp-60);
        }else if(level<15&&xp>=90){
            this.level++;
            return levelUp(xp-90);
        }else if(level<20&&xp>=275){
            this.level++;
            return levelUp(xp-275);
        }else if(level<30&&xp>=300){
            this.level++;
            return levelUp(xp-300);
        }else{
            this.exp=xp;
            return xp;
        }
    }
    
    @Override
    public String toString(){
        GetData get = new GetData(this.filepath);
        String n="Equipment name";
        for(String s:get.data()){
            if(Integer.parseInt(s.split(";")[0])==this.level){
                n=s.split(";")[1];
            }
        }
        return n+": "+this.exp();
    }
}
