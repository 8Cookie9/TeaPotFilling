package com.teapotfilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import com.database.Database;
import com.datahandling.Command;
import com.datahandling.Data;
import com.datahandling.GetData;
import com.datahandling.SetData;
import com.gui.GUI;
import com.poro.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class App {
    public static void main( String[] args ) throws Exception {
        Data data=new Data("C:/Users/Pumpkin/Documents/Streamstuff/Poro");
        Command command=new Command(data);
        GUI gui=new GUI(command);
        SwingUtilities.invokeLater(gui);
        
//        if (System.getenv("PORT") != null) {
//            port(Integer.valueOf(System.getenv("PORT")));
//        }
//
//        String jdbcOsoite = "jdbc:sqlite:kanta1.db";
//        if (System.getenv("DATABASE_URL") != null) {
//            jdbcOsoite = System.getenv("DATABASE_URL");
//        }
//        Database database = new Database(jdbcOsoite);
//        
//        Spark.get("/", (req, res) -> {
//            res.redirect("/");
//            return "ok";
//        });
    }
}
