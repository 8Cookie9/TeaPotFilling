package com.teapotfilling;

import com.datahandling.Command;
import com.datahandling.Data;
import com.datahandling.GetData;
import com.datahandling.SetData;
import com.gui.GUI;
import com.poro.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class App 
{
    public static void main( String[] args )
    {
        Data data=new Data();
        Command command=new Command("C:/Users/Jaakko/AppData/Roaming/AnkhHeart/AnkhBotR2/Twitch/Files/commands.txt", data);
        GUI gui=new GUI(command);
        SwingUtilities.invokeLater(gui);
    }
}