/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.HomeController;
import DAO.NotificationDAO;
import Model.Notification;
import Model.Row;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Filler {

    private static HomeController controller;
    private static Notification notification;
    private static Row row;
    private static ArrayList<Notification> notifications;
    private static NotificationDAO dao;
    private static SimpleDateFormat day;
    private static Notify notify;

    public Filler(HomeController controller) {

        Filler.controller = controller;
        Filler.dao = new NotificationDAO();
        Filler.day = new SimpleDateFormat("dd/MM/yyy");
        Filler.notify = controller.getNotify();

    }

    public static void fillOutProfileNotification() { // Load notifiacation // carrega as notificaçoes

        int cont = 0;
        ArrayList<Pane> alPanes = new ArrayList<>();
        
        checkNotifications();
        
        for (Notification notification : notifications) {  // Create panels with notification data // Cria paineis com os dados das notificaçoes
            
            Row row = new Row(notifications.get(cont));

            row.setOnMouseClicked((t) -> {  // opens the notification when you click // abre a notificaçao ao clicar

                try {
                    notify.showNotification(row.getNotification());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi Possivel Abrir a notificaçao " + ex);
                }

            });

            String today = day.format(new Date());
            String notificationDay = day.format(row.getNotification().getSQLScheduledDay());

            if (today.equals(notificationDay)) {
                alPanes.add(row);
            }
            cont++;
        }

         ObservableList<Pane> olPanes = FXCollections.observableArrayList(alPanes);
        
         System.out.println(olPanes.isEmpty());
         
        controller.getLvTodayNotifications().getItems().clear();
        controller.getLvTodayNotifications().setItems(olPanes);

    }

    public static void checkNotifications() {
        notifications = (ArrayList<Notification>) dao.selectAllFromUser(controller.getLogUser().getId().intValue());
    }

    public static void fillOutAllEventNotifications() {
    
        int cont = 0;
        ArrayList<Row> alRow = new ArrayList<>();
        checkNotifications();
        
        for(Notification notification: notifications){
            
            Row row = new Row(notification);
            
            row.setOnMouseClicked((t) -> {
            
                try {
                    Notify.showNotification(row.getNotification());
                } catch (Exception ex) {
                    Logger.getLogger(Filler.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            });
            
            alRow.add(row);
            
            cont++;
        }
        
        ObservableList<Row> oblRow = FXCollections.observableArrayList(alRow);
        
        controller.getLvAllEvents().setItems(oblRow);
    }

}
