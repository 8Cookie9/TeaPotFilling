package com.datahandling;

import com.poro.Poro;
import com.poro.User;
import java.util.ArrayList;
import java.util.List;

public class PoroData {
    private GetData getCommands;
    private SetData setCommands;
    private GetData poroNames;
    private GetData teaTypes;
    private GetData usernames;
    private List<String> commands;
    private List<String> poros;
    private List<String> tea;
    private List<String> user;
    private List<Poro> poro;
    private List<User> users;
    
    public PoroData(){
        this.getCommands=new GetData("commands.txt");
        this.setCommands=new SetData("commands.txt");
        this.poroNames=new GetData("poro.txt");
        this.teaTypes=new GetData("tea.txt");
        this.usernames=new GetData("users.txt");
        updateLocalData();
    }
    
    public void command(String line){
        updateLocalData();
        String[] com=line.split(" ");
        if(!this.user.stream().anyMatch(i->i.split(" ")[0].equals(com[0]))){
            this.users.add(new User(com[0]));
        }
        if(com[1].equals("newporo")){
            for(User u:this.users){
                if(u.getUsername().equals(com[0])){
                    u.newPoro(null);
                }
            }
        }else if(com[1].equals("tea")){
            
        }
    }
    
    private void updateLocalData(){
        this.commands=this.getCommands.data();
        this.poros=this.poroNames.data();
        this.tea=this.teaTypes.data();
        this.user=this.usernames.data();
        this.users=new ArrayList<>();
        for(String u:user){
            this.users.add(new User(u.split(" ")[0]));
        }
    }
}
