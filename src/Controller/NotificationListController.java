/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Notification;
import Model.NotificationRow;
import Services.Notify;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationListController implements Initializable {
   
    
    @FXML
    private ListView<BorderPane> list;
    
    private static ArrayList<Notification> notifications = new ArrayList<>();
    

      @Override
    public void initialize(URL url, ResourceBundle rb) {
        

          ArrayList<BorderPane> alNotifications = new ArrayList<>();
                  
        for (Notification notification : notifications) {

            
            NotificationRow row = new NotificationRow(notification);
            
       
            row.setOnMouseClicked((t) -> {
                
                try {
                    Notify.showNotification(notification);
             } catch (Exception ex) {
                   Logger.getLogger(NotificationListController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
            });

  
            alNotifications.add(row);
            
        }
        ObservableList<BorderPane> obsNotifications = FXCollections.observableArrayList(alNotifications);
        
        list.setPadding(new Insets(0,0,5,0));
        list.setItems(obsNotifications);
            System.out.println("prenchida");
    
     }

    public ListView<BorderPane> getList() {
        return list;
    }

    public void setList(ListView<BorderPane> list) {
        this.list = list;
    }

    public static ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public static void setNotifications(ArrayList<Notification> notifications) {
        NotificationListController.notifications = notifications;
    }

  
   
    
}
