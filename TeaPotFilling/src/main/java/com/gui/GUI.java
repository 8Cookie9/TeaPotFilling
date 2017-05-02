package com.gui;

import com.datahandling.Command;
import com.datahandling.Data;
import com.datahandling.SetData;
import com.poro.Poro;
import com.poro.User;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI implements Runnable{
    private JFrame frame;
    private boolean bool;
    private Command command;
    private Timer timer;
    private Update update;
    
    public GUI(Command command){
        this.bool=true;
        this.timer=new Timer();
        this.command=command;
        this.update=new Update(this.command);
    }
    
    @Override
    public void run() {
        frame = new JFrame("Poros and tea :3");
        frame.setPreferredSize(new Dimension(300, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container){
        GridLayout layout=new GridLayout(1,2);
        container.setLayout(layout);
        JButton start=new JButton("Start");
        JButton stop=new JButton("Stop");
        start.addActionListener((ActionEvent e) ->{
            this.timer=new Timer();
            this.update=new Update(this.command);
            this.timer.scheduleAtFixedRate(update, 1000, 3000);
            start.setEnabled(false);
            stop.setEnabled(true);
        });
        stop.addActionListener((ActionEvent e) ->{
            stop.setEnabled(false);
            start.setEnabled(true);
            timer.cancel();
        });
        container.add(start);
        container.add(stop);
    }
    class Update extends TimerTask{
        private Command command;
        private int cycle;
        public Update(Command command){
            this.command=command;
            this.cycle=0;
        }

        @Override
        public void run() {
            this.command.getCommands();
            this.command.runCommands();
            if(this.cycle%100==0){
                this.toplist();
                this.ping();
            }
            this.cycle++;
        }
        
        private void ping(){
            try{
                HttpURLConnection conn=(HttpURLConnection) new URL("http://porostuff.herokuapp.com/").openConnection();
                conn.setRequestMethod("HEAD");
                conn.getResponseCode();
            }catch(Exception e){}
        }
        
        private void toplist(){
            Data data=this.command.getData();
            data.loadAll();
            List<User> userdata=data.getData();
            HashMap<String, List<Poro>> poromap=new HashMap<>();
            for(User u:userdata){
                List<Poro> poro=new ArrayList<>();
                poro.add(u.getPoro());
                if(u.hasSecondary()){
                    poro.add(u.getSecondaryPoro());
                    poro.addAll(u.getStorage());
                }
                poromap.put(u.getUsername(), poro);
            }
            Poro bestHp=new Poro("none",0,0,0,0,0,0,1);
            Poro bestDef=new Poro("none",0,0,0,0,0,0,1);
            Poro bestAtk=new Poro("none",0,0,0,0,0,0,1);
            Poro sexy=new Poro("none",0,0,0,0,0,0,1);
            Poro maxlevel=new Poro("none",0,0,0,0,0,0,1);
            String hp="none";
            String atk="none";
            String def="none";
            String lv="none";
            String sexyy="none";
            for(String u:poromap.keySet()){
                for(Poro p:poromap.get(u)){
                    if(p.getHP()>=bestHp.getHP()){
                        bestHp=p;
                        hp=u;
                    }
                    if(p.getAttack()>bestAtk.getAttack()){
                        bestAtk=p;
                        atk=u;
                    }
                    if(p.getDefense()>bestDef.getDefense()){
                        bestDef=p;
                        def=u;
                    }
                    if(p.getTotalExp()>maxlevel.getTotalExp()){
                        maxlevel=p;
                        lv=u;
                    }
                    if(p.getType().equals("Crocoro")){
                        sexy=p;
                        sexyy=u;
                    }
                }
            }
            SetData set=new SetData(this.command.getFilepath()+"/toplist.txt");
            List<String> list=new ArrayList<>();
            list.add("#1 sexiest poro: "+sexy.getType()+" ("+sexyy+") | #1 strongest poro: "+bestAtk.getType()+" ("+atk+") | #1 fancy poro: "+bestDef.getType()+" ("+def+") | #1 gourmet poro: "+bestHp.getType()+" ("+hp+") | #1 friendly poro: "+maxlevel.getType()+" ("+lv+")");
            set.clean();
            set.write(list);
        }
    }
}
