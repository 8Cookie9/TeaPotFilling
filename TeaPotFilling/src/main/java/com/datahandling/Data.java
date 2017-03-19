package com.datahandling;

import com.poro.Equipment;
import com.poro.Poro;
import com.poro.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



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
        for(File f:new File(this.filepath+"/Users").listFiles()){
            if(f.isFile()){
                User user=new User(f.getName().split(".txt")[0]);
                GetData reader=new GetData(f.getAbsolutePath());
                List<String> coms=reader.data();
                user.setEquipment(new Equipment(coms.get(3),Integer.parseInt(coms.get(2))));
                String type=coms.get(6);
                int hpIV=Integer.parseInt(coms.get(7));
                int atkIV=Integer.parseInt(coms.get(8));
                int defIV=Integer.parseInt(coms.get(9));
                int hpmod=Integer.parseInt(coms.get(10));
                int defmod=Integer.parseInt(coms.get(11));
                int atkmod=Integer.parseInt(coms.get(12));
                int levelgain=Integer.parseInt(coms.get(13));
                int totalExp=Integer.parseInt(coms.get(14));
                Poro poro=new Poro(type, hpIV, atkIV, defIV, hpmod, defmod, atkmod, levelgain);
                poro.gainExp(totalExp);
                user.newPoro(poro);
                if(coms.size()>20){
                    Poro poro2;
                    String type2=coms.get(18);
                    int hpIV2=Integer.parseInt(coms.get(19));
                    int atkIV2=Integer.parseInt(coms.get(20));
                    int defIV2=Integer.parseInt(coms.get(21));
                    int hpmod2=Integer.parseInt(coms.get(22));
                    int defmod2=Integer.parseInt(coms.get(23));
                    int atkmod2=Integer.parseInt(coms.get(24));
                    int levelgain2=Integer.parseInt(coms.get(25));
                    int totalExp2=Integer.parseInt(coms.get(26));
                    poro2=new Poro(type2, hpIV2, atkIV2, defIV2, hpmod2, defmod2, atkmod2, levelgain2);
                    poro2.gainExp(totalExp2);
                    user.newPoro(poro2);
                }
                user.addHeadgear(Integer.parseInt(coms.get(4)));
                user.addMisc(Integer.parseInt(coms.get(5)));
                this.data.add(user);
            }
        }
    }
    
    public void saveData(){
        for(User user:this.data){
            SetData setData=new SetData(this.filepath+"/Users/"+user.getUsername()+".txt");
            setData.clean();
            List<String> userData=new ArrayList<>();
            userData.add(user.getEquipment().toString());
            userData.add(""+user.getEquipment().getLevel());
            userData.add(""+user.getEquipment().getTotalExp());
            userData.add(user.getEquipment().getFilepath());
            userData.add(""+user.getHeadgearDef());
            userData.add(""+user.getMiscHp());
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
