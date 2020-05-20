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
public class MainNotificationList extends Application {

    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/View/NotificationList.fxml"));

        Scene scene = new Scene(root);

        Image icon = new Image(getClass().getResourceAsStream("/View/Images/manLoad.png"));

        stage.getIcons().add(icon);
        stage.setTitle("Notificaçoes");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setX(565);
        stage.setY(530);
        stage.show();

        setWindow(stage);
    }
    
    public static void main(String[] args) {
        
        Platform.runLater(() -> {
            
        MainNotificationList list = new MainNotificationList();
        
            try {
                list.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(MainNotificationList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            NotificationListController lists = new NotificationListController();
        
            NotificationDAO dao = new NotificationDAO();
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainNotificationList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            lists.loadNotications((ArrayList<Notification>) dao.selectAll());
            
        });
        
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        MainNotificationList.window = window;
    }

}
