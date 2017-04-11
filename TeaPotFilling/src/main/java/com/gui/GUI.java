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
            }
            this.cycle++;
        }
        
        public void toplist(){
            Data data=this.command.getData();
            data.loadAll();
            List<User> userdata=data.getData();
            List<Poro> poros=new ArrayList<>();
            HashMap<User, List<Poro>> poromap=new HashMap<>();
            for(User u:userdata){
                List<Poro> poro=new ArrayList<>();
                poro.add(u.getPoro());
                poro.add(u.getSecondaryPoro());
                poro.addAll(u.getStorage());
                poromap.put(u, poro);
                poros.add(u.getPoro());
                poros.add(u.getSecondaryPoro());
                poros.addAll(u.getStorage());
            }
            Poro bestHp=new Poro("none",0,0,0,0,0,0,1);
            Poro bestDef=new Poro("none",0,0,0,0,0,0,1);
            Poro bestAtk=new Poro("none",0,0,0,0,0,0,1);
            Poro sexy=new Poro("none",0,0,0,0,0,0,1);
            User hp=new User("none");
            User atk=new User("none");
            User def=new User("none");
            User sexyy=new User("none");
            for(User u:poromap.keySet()){
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
                    if(p.getType().equals("Crocoro")){
                        sexy=p;
                        sexyy=u;
                    }
                }
            }
            SetData set=new SetData(this.command.getFilepath()+"/toplist.txt");
            List<String> list=new ArrayList<>();
            list.add("#1 sexiest poro: "+sexy.getType()+" ("+sexyy.getUsername()+") | #1 strongest poro: "+bestAtk.getType()+" ("+atk.getUsername()+") | #1 fancy poro: "+bestDef.getType()+" ("+def.getUsername()+") | #1 gourmet poro: "+bestHp.getType()+" ("+hp.getUsername()+")");
            set.clean();
            set.write(list);
        }
    }
}
