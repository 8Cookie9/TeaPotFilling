package com.datahandling;

import java.io.FileWriter;
import java.util.List;


public class SetData {
    private String filename;
    
    public SetData(String file){
        this.filename=file;
    }
    
    public void write(List<String> lines){
        try{
            FileWriter writer = new FileWriter(this.filename);
            GetData get=new GetData(filename);
            List<String> content=get.data();
            content.addAll(lines);
            for(String line:content){
                writer.append(line+"\n");
            }
            writer.close();
        }catch(Exception e){}
    }
    
    public void clean(){
        try{
            FileWriter writer = new FileWriter(this.filename);
            writer.write("");
            writer.close();
        }catch(Exception e){}
    }
}
