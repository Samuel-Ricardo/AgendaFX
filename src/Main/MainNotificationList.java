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
public class MainNotificationList extends Application{

    private static Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
      
        Parent root = FXMLLoader.load(getClass().getResource("/View/NotificationScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        Image icon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png"));
    
        stage.getIcons().add(icon);
        stage.setTitle("Notificaçao");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setX(700);
        stage.setY(500);
        stage.show();
        
        setWindow(stage);
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        MainNotificationList.window = window;
    }
    
    
    
}
