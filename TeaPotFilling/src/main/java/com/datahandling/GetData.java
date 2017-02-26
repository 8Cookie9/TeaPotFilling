package com.datahandling;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetData {
    private String filename;
    
    public GetData(String filename){
        this.filename=filename;
        
    }
    
    public List<String> data(){   
        ArrayList<String> data=new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new FileReader(filename));
            while(scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }catch(Exception e){
            return null;
        }
        return data;
    }
    
    public void changeFile(String f){
        this.filename=f;
    }
    
}
