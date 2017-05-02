package com.datahandling;

import com.poro.Equipment;
import com.poro.Headgear;
import com.poro.Misc;
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
            if(f.isDirectory()){
                User user=new User(f.getName());
                GetData get=new GetData(f.getAbsolutePath().replace("\\", "/")+"/poros.txt");
                List<String> filedata=get.data();
                Poro p=new Poro(filedata.get(0),Integer.parseInt(filedata.get(1)),Integer.parseInt(filedata.get(2)),Integer.parseInt(filedata.get(3)),Integer.parseInt(filedata.get(4)),Integer.parseInt(filedata.get(5)),Integer.parseInt(filedata.get(6)),Integer.parseInt(filedata.get(7)));
                p.gainExp(Integer.parseInt(filedata.get(8)));
                user.newPoro(p);
                if(filedata.size()>15){
                    Poro p2=new Poro(filedata.get(11),Integer.parseInt(filedata.get(12)),Integer.parseInt(filedata.get(13)),Integer.parseInt(filedata.get(14)),Integer.parseInt(filedata.get(15)),Integer.parseInt(filedata.get(16)),Integer.parseInt(filedata.get(17)),Integer.parseInt(filedata.get(18)));
                    p2.gainExp(Integer.parseInt(filedata.get(19)));
                    user.newPoro(p2);
                }
                get=new GetData(f.getAbsolutePath().replace("\\", "/")+"/storage.txt");
                filedata=get.data();
                for(int i=0;i<filedata.size()/9;i++){
                    p=new Poro(filedata.get(9*i+0),Integer.parseInt(filedata.get(9*i+1)),Integer.parseInt(filedata.get(9*i+2)),Integer.parseInt(filedata.get(9*i+3)),Integer.parseInt(filedata.get(9*i+4)),Integer.parseInt(filedata.get(9*i+5)),Integer.parseInt(filedata.get(9*i+6)),Integer.parseInt(filedata.get(9*i+7)));
                    p.gainExp(Integer.parseInt(filedata.get(9*i+8)));
                    user.newPoro(p);
                }
                for(File file:f.listFiles()){
                    get=new GetData(file.getAbsolutePath().replace("\\", "/"));
                    if(file.getName().contains("misc")){
                        for(String s:get.data()){
                            user.addMisc(new Misc(s.split(";")[0],Integer.parseInt(s.split(";")[1])));
                        }
                    }else if(file.getName().contains("headgear")){
                        for(String s:get.data()){
                            user.addHeadgear(new Headgear(s.split(";")[0],Integer.parseInt(s.split(";")[1])));
                        }
                    }else if(file.getName().contains("equipment")){
                        filedata=get.data();
                        user.setEquipment(new Equipment(filedata.get(0),Integer.parseInt(filedata.get(1))));
                    }
                }
                this.data.add(user);
            }
        }
    }
    
    public void saveData(){
        for(User user:this.data){
            SetData setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/poros.txt");
            setData.clean();
            List<String> userData=new ArrayList<>();
            userData.add(user.getPoro().getType());
            userData.add(user.getPoro().getHpIV()+"");
            userData.add(user.getPoro().getAttackIV()+"");
            userData.add(user.getPoro().getDefenseIV()+"");
            userData.add(user.getPoro().getHpModifier()+"");
            userData.add(user.getPoro().getAttackModifier()+"");
            userData.add(user.getPoro().getDefenseModifier()+"");
            userData.add(user.getPoro().getLevelGain()+"");
            userData.add(user.getPoro().getTotalExp()+"");
            userData.add("level "+user.getPoro().getLevel()+" ("+user.getPoro().getExp()+"/"+user.getPoro().expForNextLevel()+")");
            userData.add("("+user.getPoro().getHP()+", "+user.getPoro().getAttack()+", "+user.getPoro().getDefense()+")");
            if(user.hasSecondary()){
                userData.add(user.getSecondaryPoro().getType());
                userData.add(user.getSecondaryPoro().getHpIV()+"");
                userData.add(user.getSecondaryPoro().getAttackIV()+"");
                userData.add(user.getSecondaryPoro().getDefenseIV()+"");
                userData.add(user.getSecondaryPoro().getHpModifier()+"");
                userData.add(user.getSecondaryPoro().getAttackModifier()+"");
                userData.add(user.getSecondaryPoro().getDefenseModifier()+"");
                userData.add(user.getSecondaryPoro().getLevelGain()+"");
                userData.add(user.getSecondaryPoro().getTotalExp()+"");
                userData.add("level "+user.getSecondaryPoro().getLevel()+" of friendship ("+user.getSecondaryPoro().getExp()+"/"+user.getSecondaryPoro().expForNextLevel()+")");
                userData.add("("+user.getSecondaryPoro().getHP()+", "+user.getSecondaryPoro().getAttack()+", "+user.getSecondaryPoro().getDefense()+")");
            }
            setData.write(userData);
            
            setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/equipment.txt");
            setData.clean();
            userData=new ArrayList<>();
            userData.add(user.getEquipment().getFilepath());
            userData.add(""+user.getEquipment().getTotalExp());
            userData.add(""+user.getEquipment().getLevel());
            userData.add(user.getEquipment().toString());
            setData.write(userData);
            
            setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/headgear.txt");
            setData.clean();
            userData=new ArrayList<>();
            for(Headgear h:user.getHeadgear()){
                userData.add(h.getName()+";"+h.getDef());
            }
            setData.write(userData);
            
            setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/misc.txt");
            setData.clean();
            userData=new ArrayList<>();
            for(Misc m:user.getMisc()){
                userData.add(m.getName()+";"+m.getHp());
            }
            setData.write(userData);
            
            setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/storage.txt");
            setData.clean();
            userData=new ArrayList<>();
            for(Poro p:user.getStorage()){
                userData.add(p.getType());
                userData.add(p.getHpIV()+"");
                userData.add(p.getAttackIV()+"");
                userData.add(p.getDefenseIV()+"");
                userData.add(p.getHpModifier()+"");
                userData.add(p.getAttackModifier()+"");
                userData.add(p.getDefenseModifier()+"");
                userData.add(p.getLevelGain()+"");
                userData.add(p.getTotalExp()+"");
            }
            setData.write(userData);
            
            setData=new SetData(this.filepath+"/Users/"+user.getUsername()+"/storagetext.txt");
            setData.clean();
            userData=new ArrayList<>();
            String s="";
            int b=0;
            for(Poro p:user.getStorage()){
                if((b+1)%5==1){
                    s+="Room ["+(int) Math.ceil((b+5)/5)+"/"+(int) Math.ceil((user.getStorage().size()+4)/5)+"] ";
                }
                s+="["+b+"] "+p.getType()+" (Lv "+p.getLevel()+")";
                if(b==user.getStorage().size()){
                    userData.add(s);
                    s="";
                }else if((b+1)%5!=0&&b!=user.getStorage().size()-1){
                    s+=", ";
                }else{
                    userData.add(s);
                    s="";
                }
                b++;
            }
            setData.write(userData);
        }
    }
    
    public String getFilepath(){
        return this.filepath;
    }
}
