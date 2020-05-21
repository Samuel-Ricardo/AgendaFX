/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.NotificationListController;
import DAO.NotificationDAO;
import Model.Notification;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
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
   
    Parent root = FXMLLoader.load(getClass().getResource("/View/NotificationList.fxml"));  // Loading a Parent with the JavaFX window fxml  //  Carregando um Parent com o fxml da janela JavaFX
    
        Scene scene = new Scene(root); // Loading Scene with Parent  //  Carregando Scene com o Parent 
        
        Image WindowIcon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png")); // Take an image  // Pega uma imagen
        
        stage.getIcons().add(WindowIcon); // put the icon in the window   //  poe o ícone na janela
        stage.setTitle("Notificaçoes");          // put the icon in the window  //  poe o titulo na janela
        stage.setResizable(false);        // prevents the window from being resized  //  impede que a janela seja redimensionada
        stage.setScene(scene);            // loads the Stage (window)  //  carrega o Stage (janela)
        stage.setY(535);
        stage.setX(582);
        stage.show();                     // show the Stage (window)  //  Mostra o stage(janela) 
        
        setWindow(stage);                 // Load the Stage in a 'window' statistical attribute  //  Carrega o Stage num atributo estatico 'janela' 
    
    }
    
    public static void main(String[] args) {
        
        Platform.runLater(() -> {
             try {
            MainNotificationList list = new MainNotificationList();
               NotificationDAO dao = new NotificationDAO();
                
               NotificationListController.setNotifications((ArrayList<Notification>) dao.selectAll());
                
                list.start(new Stage());
                 System.out.println(NotificationListController.getNotifications().get(0).getTitle());
             
                 
            } catch (Exception ex) {
                Logger.getLogger(MainNotificationList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage janela) {
        MainNotificationList.window = janela;
    }
    
    
    
}
