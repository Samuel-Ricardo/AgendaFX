/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Reminder;
import Model.LiteRow;
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
    private ListView<LiteRow> list;
    
    private static ArrayList<Reminder> notifications = new ArrayList<>();
    

      @Override
    public void initialize(URL url, ResourceBundle rb) {
        

          ArrayList<LiteRow> alNotifications = new ArrayList<>();
                  
        for (Reminder notification : notifications) {

            
            LiteRow row = new LiteRow(notification);
            
       
            row.setOnMouseClicked((t) -> {
                
                try {
                    Notify.showNotification(notification);
             } catch (Exception ex) {
                   Logger.getLogger(NotificationListController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
            });

  
            alNotifications.add(row);
            
        }
        ObservableList<LiteRow> obsNotifications = FXCollections.observableArrayList(alNotifications);
        
      //list.setPadding(new Insets(5,5,5,5));
        list.setFixedCellSize(138);
        list.setItems(obsNotifications);
        System.out.println("prenchida");
    
     }

    public ListView<LiteRow> getList() {
        return list;
    }

    public void setList(ListView<LiteRow> list) {
        this.list = list;
    }

    public static ArrayList<Reminder> getNotifications() {
        return notifications;
    }

    public static void setNotifications(ArrayList<Reminder> notifications) {
        NotificationListController.notifications = notifications;
    }

  
   
    
}
