/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Creators;

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
public class MainTypeCreator extends Application{
    
    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
     
        Parent root = FXMLLoader.load(getClass().getResource("/View/Creators/TypeCreator.fxml"));
        
        Scene scne = new Scene(root);
        
        Image icon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png"));
        
        stage.setTitle("Type Creator");
        stage.getIcons().setAll(icon);
        stage.setResizable(false);
        stage.setScene(scne);
        
        stage.show();
        setWindow(stage);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        MainTypeCreator.window = window;
    }
    
}
