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
    
    public Data(String filepath){
        this.filepath=filepath;
        this.data=new ArrayList<>();
        this.loadAll();
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
            for(File f:new File(this.filepath+"/Users").listFiles()){
                if(f.isFile()){
                    User user=new User(f.getName().split(".txt")[0]);
                    GetData reader=new GetData(f.getAbsolutePath());
                    List<String> coms=reader.data();
                    String type=coms.get(0);
                    int hpIV=Integer.parseInt(coms.get(1));
                    int atkIV=Integer.parseInt(coms.get(2));
                    int defIV=Integer.parseInt(coms.get(3));
                    int hpmod=Integer.parseInt(coms.get(4));
                    int defmod=Integer.parseInt(coms.get(5));
                    int atkmod=Integer.parseInt(coms.get(6));
                    int levelgain=Integer.parseInt(coms.get(7));
                    int totalExp=Integer.parseInt(coms.get(8));
                    Poro poro=new Poro(type, hpIV, atkIV, defIV, hpmod, defmod, atkmod, levelgain);
                    Poro poro2=null;
                    if(coms.size()>10){
                        String type2=coms.get(12);
                        int hpIV2=Integer.parseInt(coms.get(13));
                        int atkIV2=Integer.parseInt(coms.get(14));
                        int defIV2=Integer.parseInt(coms.get(15));
                        int hpmod2=Integer.parseInt(coms.get(16));
                        int defmod2=Integer.parseInt(coms.get(17));
                        int atkmod2=Integer.parseInt(coms.get(18));
                        int levelgain2=Integer.parseInt(coms.get(19));
                        int totalExp2=Integer.parseInt(coms.get(20));
                        poro2=new Poro(type2, hpIV2, atkIV2, defIV2, hpmod2, defmod2, atkmod2, levelgain2);
                        poro2.gainExp(totalExp2);
                    }
                    poro.gainExp(totalExp);
                    user.newPoro(poro);
                    if(poro2!=null){
                        user.newPoro(poro2);
                    }
                    this.data.add(user);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void saveData(){
        for(User user:this.data){
            SetData setData=new SetData(this.filepath+"/Users/"+user.getUsername()+".txt");
            setData.clean();
            List<String> userData=new ArrayList<>();
            userData.add(user.getPoro().getType());
            userData.add(user.getPoro().getHpIV()+"");
            userData.add(user.getPoro().getAttackIV()+"");
            userData.add(user.getPoro().getDefenseIV()+"");
            userData.add(user.getPoro().getHpModifier()+"");
            userData.add(user.getPoro().getDefenseModifier()+"");
            userData.add(user.getPoro().getAttackModifier()+"");
            userData.add(user.getPoro().getLevelGain()+"");
            userData.add(user.getPoro().getTotalExp()+"");
            userData.add("("+user.getPoro().getExp()+"/"+user.getPoro().expForNextLevel()+")");
            userData.add(user.getPoro().getLevel()+"");
            userData.add("("+user.getPoro().getHP()+", "+user.getPoro().getAttack()+", "+user.getPoro().getDefense()+")");
            if(user.hasSecondary()){
                userData.add(user.getSecondaryPoro().getType());
                userData.add(user.getSecondaryPoro().getHpIV()+"");
                userData.add(user.getSecondaryPoro().getAttackIV()+"");
                userData.add(user.getSecondaryPoro().getDefenseIV()+"");
                userData.add(user.getSecondaryPoro().getHpModifier()+"");
                userData.add(user.getSecondaryPoro().getDefenseModifier()+"");
                userData.add(user.getSecondaryPoro().getAttackModifier()+"");
                userData.add(user.getSecondaryPoro().getLevelGain()+"");
                userData.add(user.getSecondaryPoro().getTotalExp()+"");
                userData.add("("+user.getSecondaryPoro().getExp()+"/"+user.getSecondaryPoro().expForNextLevel()+")");
                userData.add(user.getSecondaryPoro().getLevel()+"");
                userData.add("("+user.getSecondaryPoro().getHP()+", "+user.getSecondaryPoro().getAttack()+", "+user.getSecondaryPoro().getDefense()+")");
            }
            setData.write(userData);
        }
    }
    
    public String getFilepath(){
        return this.filepath;
    }
}
