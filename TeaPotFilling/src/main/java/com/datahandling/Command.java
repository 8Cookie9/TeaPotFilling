package com.datahandling;

import com.poro.Poro;
import com.poro.User;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private String file;
    private Data data;
    private List<String> commands;
    
    public Command(String file, Data data){
        this.file=file;
        this.data=data;
        this.commands=new ArrayList<>();
    }
    
    public void getCommands(){
        GetData getData=new GetData(this.file);
        SetData setData=new SetData(this.file);
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
        String username=com[0];
        String commandName=com[1];
        String[] args=null;
        if(com.length-2>0){
            args=new String[com.length-2];
            for(int i=2;i<com.length;i++){
                args[i-2]=com[i];
            }
        }
        if(getUserData(username)==null){
            User user=new User(username);
            user.newPoro(new Poro("Poro",0,0,0,1,1,1,1));
            this.data.getData().add(user);
        }
        if(commandName.equals("addexp")){
            getUserData(username).getPoro().addExp(Integer.parseInt(args[0]));
        }
        this.data.setData(this.data.getData());
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
}
