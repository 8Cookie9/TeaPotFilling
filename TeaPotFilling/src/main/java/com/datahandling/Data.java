package com.datahandling;

import com.poro.Poro;
import com.poro.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Data {
    private String filepath;
    private List<User> data;
    
    public Data(){
        this.filepath="C:/Users/Jaakko/AppData/Roaming/AnkhHeart/AnkhBotR2/Twitch/Files/Users";
        this.loadAll();
    }
    
    public Data(String filepath){
        this.filepath=filepath;
        this.data=new ArrayList<>();
    }
    
    public List<User> getData(){
        return this.data;
    }
    
    public void setData(List<User> userData){
        this.data=userData;
    }
    
    public void loadAll(){
        this.data=new ArrayList<>();
        try{
            for(File f:new File(this.filepath).listFiles()){
                if(f.isFile()){
                    User user=new User(f.getName().split(".txt")[0]);
                    GetData reader=new GetData(f.getAbsolutePath());
                    List<String> coms=reader.data();
                    String type=coms.get(0);
                    int hp=Integer.parseInt(coms.get(1));
                    int atk=Integer.parseInt(coms.get(2));
                    int def=Integer.parseInt(coms.get(3));
                    int hpmod=Integer.parseInt(coms.get(4));
                    int defmod=Integer.parseInt(coms.get(5));
                    int atkmod=Integer.parseInt(coms.get(6));
                    int levelgain=Integer.parseInt(coms.get(7));
                    int totalExp=Integer.parseInt(coms.get(8));
                    Poro poro=new Poro(type, hp, atk, def, hpmod, defmod, atkmod, levelgain);
                    poro.gainExp(totalExp);
                    user.newPoro(poro);
                    this.data.add(user);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void saveData(){
        for(User user:this.data){
            SetData setData=new SetData(this.filepath+"/"+user.getUsername()+".txt");
            setData.clean();
            List<String> userData=new ArrayList<>();
            userData.add(user.getPoro().getType());
            userData.add(user.getPoro().getHP()+"");
            userData.add(user.getPoro().getAttack()+"");
            userData.add(user.getPoro().getDefense()+"");
            userData.add(user.getPoro().getHpModifier()+"");
            userData.add(user.getPoro().getDefenseModifier()+"");
            userData.add(user.getPoro().getAttackModifier()+"");
            userData.add(user.getPoro().getLevelGain()+"");
            userData.add(user.getPoro().getTotalExp()+"");
            setData.write(userData);
        }
    }
    
    public String getFilepath(){
        return this.filepath;
    }
}
