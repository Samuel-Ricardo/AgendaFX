/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Viewers;

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
public class MainNotificationScreen extends Application{

    private static Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
      
        Parent root = FXMLLoader.load(getClass().getResource("/View/Viewers/NotificationScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        Image icon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png"));
    
        stage.getIcons().add(icon);
        stage.setTitle("Notificaçao");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        setWindow(stage);
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        MainNotificationScreen.window = window;
    }
    
    
    
}
