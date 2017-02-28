package com.poro;

public class User {
    private String username;
    private Poro poro;
    
    public User(String username){
        this.username=username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public Poro getPoro(){
        return this.poro;
    }
    
    public void changePoro(Poro newPoro){
        this.poro=newPoro;
    }
    
    public void newPoro(Poro newPoro){
        this.poro=newPoro;
    }
}
