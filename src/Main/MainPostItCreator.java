/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class MainPostItCreator extends Application{
    
    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
     
        Parent root = FXMLLoader.load(getClass().getResource("/View/PostItCreator.fxml"));
        
        Scene scne = new Scene(root);
        
        Image icon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png"));
        
        stage.setTitle("PostIt Creator");
        stage.getIcons().setAll(icon);
        stage.setResizable(false);
        stage.setScene(scne);
        
        stage.show();
        setWindow(stage);
        
    }
    
    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        MainPostItCreator.window = window;
    }
    
    
    
}
