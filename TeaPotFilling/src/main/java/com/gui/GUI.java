package com.gui;

import com.datahandling.Command;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
        public Update(Command command){
            this.command=command;
        }

        @Override
        public void run() {
            this.command.getCommands();
            this.command.runCommands();
        }
    }
}
