package com.teapotfilling;

import com.datahandling.*;
import com.timer.Timer;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        GetData get=new GetData("file.txt");
        SetData set=new SetData("file.txt");
        Timer timer=new Timer(3000);
        set.clean();
        List<String> list;
        for(int i=0;i<5;i++){
            list=new ArrayList<>();
            list.add(i+"");
            set.write(list);
            timer.startWait();
        }
        
        for(String s:get.data()){
            System.out.println(s);
        }
    }
}
