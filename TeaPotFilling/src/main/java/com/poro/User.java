package com.poro;

public class User {
    private String username;
    private Poro poro;
    private Poro newPoro;
    
    public User(String username){
        this.username=username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void changePoro(){
        this.poro=this.newPoro;
        this.newPoro=null;
    }
    public void changePoro(Poro newPoro){
        this.poro=newPoro;
    }
    
    public void newPoro(Poro newPoro){
        this.newPoro=newPoro;
    }
}
