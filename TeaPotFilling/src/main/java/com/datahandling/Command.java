package com.datahandling;

import com.poro.PoroFactory;
import com.poro.User;
import com.poro.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Command {
    private String file;
    private Data data;
    private List<String> commands;
    private PoroFactory poroFactory;
    
    public Command(Data data){
        this.data=data;
        this.file=data.getFilepath();
        this.commands=new ArrayList<>();
        this.poroFactory=new PoroFactory(this.file+"/poro.txt");
    }
    
    public void getCommands(){
        GetData getData=new GetData(this.file+"/commands.txt");
        SetData setData=new SetData(this.file+"/commands.txt");
        this.commands=getData.data();
        setData.clean();
    }
    
    public void runCommands(){
        for(String command:this.commands){
            runCommand(command);
        }
    }
    
    public void runCommand(String command){
        String[] com=command.split(" ");
        if(com.length<2){
            return;
        }
        String username=normalise(com[0]);
        String commandName=com[1];
        String[] args=null;
        if(com.length-2>0){
            args=new String[com.length-2];
            for(int i=2;i<com.length;i++){
                args[i-2]=com[i];
            }
        }
        User userdata=getUserData(username);
        if(userdata==null&&commandName.equals("firstporo")){
            User user=new User(username);
            user.newPoro(this.poroFactory.getPoro(50));
            this.data.getData().add(user);
            SetData set=new SetData(this.data.getFilepath()+"/Users/"+username+".txt");
            set.clean();
            this.commands=new ArrayList<>();
        }else if(userdata!=null&&commandName.equals("addexp")){
            userdata.getPoro().addExp(Integer.parseInt(args[0]));
        }else if(userdata!=null&&commandName.equals("newporo")){
            userdata.newPoro(this.poroFactory.getPoro(0));
        }else if(userdata!=null&&commandName.equals("swap")){
            userdata.swapPoros();
        }else if(userdata!=null&&commandName.equals("randomtea")){
            String tea="";
            for(int i=0;i<args.length;i++){
                tea+=args[i];
                if(i!=args.length-1){
                    tea+=" ";
                }
            }
            userdata.getPoro().addExp(expFromTea(tea));
        }else if(userdata!=null&&commandName.equals("getweapon")){
            int r=new Random().nextInt(6);
            if(r==0){
                userdata.setWeapon(new Weapon("pan", this.file, 0));
            }else if(r==1){
                userdata.setWeapon(new Weapon("hammer", this.file, 0));
            }else if(r==2){
                userdata.setWeapon(new Weapon("fishingrod", this.file, 0));
            }else if(r==3){
                userdata.setWeapon(new Weapon("pillow", this.file, 0));
            }else if(r==4){
                userdata.setWeapon(new Weapon("net", this.file, 0));
            }else if(r==5){
                userdata.setWeapon(new Weapon("lollipop", this.file, 0));
            }
        }else if(userdata!=null&&commandName.equals("headgear")){
            
        }else if(userdata!=null&&commandName.equals("misc")){
            
        }else if(userdata!=null&&commandName.equals("pastry")){
            String pastry="";
            for(int i=0;i<args.length;i++){
                pastry+=args[i];
                if(i!=args.length-1){
                    pastry+=" ";
                }
            }
            userdata.getPoro().addExp(expFromTea(pastry));
        }
        this.data.saveData();
        this.commands=new ArrayList<>();
    }
    
    public User getUserData(String username){
        for(User user:this.data.getData()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    
    private String normalise(String s){
        String characters="abcdefghijklmnopqrstuvwxyz0123456789_ ";
        String res="";
        for(int i=0;i<s.length();i++){
            if(characters.contains(s.toLowerCase().charAt(i)+"")){
                res+=s.charAt(i);
            }
        }
        return res;
    }
    
    public int expFromTea(String t){
        String[] tea=t.split(" ");
        int exp;
        if(tea[0].equals("low-quality")){
            exp=1;
        }else if(tea[0].equals("standard")){
            exp=5;
        }else if(tea[0].equals("high-quality")){
            exp=10;
        }else if(tea[0].equals("top-quality")){
            exp=100;
        }else if(tea[0].equals("perfect")){
            exp=1000;
        }else{
            exp=1;
        }
        return exp*(new Random().nextInt(50)+1);
    }
}
