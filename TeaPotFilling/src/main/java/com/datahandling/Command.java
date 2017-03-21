package com.datahandling;

import com.poro.Poro;
import com.poro.PoroFactory;
import com.poro.User;
import com.poro.Equipment;
import com.poro.Headgear;
import com.poro.Misc;
import java.io.File;
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
        this.commands=new ArrayList<>();
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
        System.out.println(username);
        User userdata=getUserData(username);
        if(userdata==null&&commandName.equals("firstporo")){
            User user=new User(username);
            String poro="";
            for(int i=2;i<com.length;i++){
                poro+=com[i];
            }
            String[] p=poro.split(";");
            user.newPoro(new Poro(command.split(":")[1],Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4]),Integer.parseInt(p[5]),Integer.parseInt(p[6]),Integer.parseInt(p[7])));
            int r=new Random().nextInt(19);
            if(r==0){
                user.setEquipment(new Equipment(this.file+"/giantteaspoon.txt", 0));
            }else if(r==1||r==2){
                user.setEquipment(new Equipment(this.file+"/lollipop.txt", 0));
            }else if(r==3||r==4){
                user.setEquipment(new Equipment(this.file+"/candycane.txt", 0));
            }else if(r<=7){
                user.setEquipment(new Equipment(this.file+"/fryingpan.txt", 0));
            }else if(r<=10){
                user.setEquipment(new Equipment(this.file+"/pillow.txt", 0));
            }else if(r<=14){
                user.setEquipment(new Equipment(this.file+"/net.txt", 0));
            }else if(r<=18){
                user.setEquipment(new Equipment(this.file+"/fishingrod.txt", 0));
            }
            this.data.getData().add(user);
            File dir=new File(this.data.getFilepath()+"/Users/"+username);
            dir.mkdir();
            SetData set=new SetData(this.data.getFilepath()+"/Users/"+username+"/equipment.txt");
            set.clean();
            set=new SetData(this.data.getFilepath()+"/Users/"+username+"/poros.txt");
            set.clean();
            set=new SetData(this.data.getFilepath()+"/Users/"+username+"/headgear.txt");
            set.clean();
            set=new SetData(this.data.getFilepath()+"/Users/"+username+"/misc.txt");
            set.clean();
        }else if(userdata!=null&&commandName.equals("addexp")){
            userdata.getPoro().addExp(Integer.parseInt(args[0]));
        }else if(userdata!=null&&commandName.equals("newporo")){
            String poro="";
            for(int i=2;i<com.length;i++){
                poro+=com[i];
            }
            String[] p=poro.split(";");
            userdata.newPoro(new Poro(command.split(":")[1],Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4]),Integer.parseInt(p[5]),Integer.parseInt(p[6]),Integer.parseInt(p[7])));
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
        }else if(userdata!=null&&commandName.equals("newequipment")){
            int r=new Random().nextInt(19);
            if(r==0){
                userdata.setEquipment(new Equipment(this.file+"/giantteaspoon.txt", 0));
            }else if(r==1||r==2){
                userdata.setEquipment(new Equipment(this.file+"/lollipop.txt", 0));
            }else if(r==3||r==4){
                userdata.setEquipment(new Equipment(this.file+"/candycane.txt", 0));
            }else if(r<=7){
                userdata.setEquipment(new Equipment(this.file+"/fryingpan.txt", 0));
            }else if(r<=10){
                userdata.setEquipment(new Equipment(this.file+"/pillow.txt", 0));
            }else if(r<=14){
                userdata.setEquipment(new Equipment(this.file+"/net.txt", 0));
            }else if(r<=18){
                userdata.setEquipment(new Equipment(this.file+"/fishingrod.txt", 0));
            }
        }else if(userdata!=null&&commandName.equals("headgear")){
            String name="";
            for(int i=0;i<(args.length-1);i++){
                name+=args[i];
            }
            userdata.addHeadgear(new Headgear(name, Integer.parseInt(args[args.length-1])));
        }else if(userdata!=null&&commandName.equals("misc")){
            String name="";
            for(int i=0;i<(args.length-1);i++){
                name+=args[i];
            }
            userdata.addMisc(new Misc(name, Integer.parseInt(args[args.length-1])));
        }else if(userdata!=null&&commandName.equals("pastry")){
            String pastry="";
            for(int i=0;i<args.length;i++){
                pastry+=args[i];
                if(i!=args.length-1){
                    pastry+=" ";
                }
            }
            userdata.getPoro().addExp(expFromTea(pastry));
        }else if(userdata!=null&&commandName.equals("battle")){
            User targetdata = getUserData(args[0]);
            if(args[1].equals("win")){
                userdata.getEquipment().levelUp(5);
            }else if(args[1].equals("lose")){
                targetdata.getEquipment().levelUp(3);
            }else{
                userdata.getEquipment().levelUp(2);
            }
            if(args[2].equals("true")&&!targetdata.getMisc().isEmpty()){
                int steal=new Random().nextInt(targetdata.getMisc().size());
                userdata.addMisc(targetdata.getMisc().get(steal));
                targetdata.getMisc().remove(steal);
            }
        }else if(userdata!=null&&commandName.equals("practice")){
            userdata.getEquipment().levelUp(3);
        }
        this.data.saveData();
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
