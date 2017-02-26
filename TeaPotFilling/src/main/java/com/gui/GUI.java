package com.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI implements Runnable{
    private JFrame frame;
    private boolean bool;
    
    public GUI(){
        this.bool=true;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Poros and tea :3");
        frame.setPreferredSize(new Dimension(500, 500));

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
            if(!this.bool){
                this.bool=true;
                this.update(5000);
            }
        });
        stop.addActionListener((ActionEvent e) ->{
            this.bool=false;
        });
        container.add(start);
        container.add(stop);
    }
    
    private void update(int delay){
        
    }
}
