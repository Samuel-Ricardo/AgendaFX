/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Controller.HomeController;
import Controller.NotificationListController;
import DAO.NotificationDAO;
import DAO.TypeDAO;
import DAO.UserDAO;
import Model.Notification;
import Model.LiteRow;
import Model.Row;
import Model.Type;
import Model.TypeRow;
import Services.Notify;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
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
    private static ArrayList<Type> types ;
    private static NotificationDAO dao = new NotificationDAO();
    private static TypeDAO typeDao = new TypeDAO();
    private static SimpleDateFormat day;
    private static Notify notify;
    private static ListView<?> list;

    public Filler(HomeController controller) {

        Filler.controller = controller;
        Filler.dao = new NotificationDAO();
        Filler.day = new SimpleDateFormat("dd/MM/yyy");
        Filler.notify = controller.getNotify();

    }

    public static void fillOutNotificationsOfToday(ListView list) { // Load notifiacation // carrega as notificaçoes

        int cont = 0;
        ArrayList<Row> alPanes = new ArrayList<>();
        
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

         ObservableList<Row> olPanes = FXCollections.observableArrayList(alPanes);

         list.getItems().clear();
         list.setItems(olPanes);

    }
    public static void fillOutActivitiesDoneToday(ListView list) { // Load notifiacation // carrega as notificaçoes

        int cont = 0;
        ArrayList<Row> alPanes = new ArrayList<>();
        
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

            if (today.equals(notificationDay) && row.getNotification().isWarned() == true) {
                alPanes.add(row);
            }
            cont++;
        }

         ObservableList<Row> olPanes = FXCollections.observableArrayList(alPanes);

         list.getItems().clear();
         list.setItems(olPanes);

    }
    public static void fillTodaysScheduledActivities(ListView list) { // Load notifiacation // carrega as notificaçoes

        int cont = 0;
        ArrayList<Row> alPanes = new ArrayList<>();
        
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

            if (today.equals(notificationDay) && row.getNotification().isWarned() == false) {
                alPanes.add(row);
            }
            cont++;
        }

         ObservableList<Row> olPanes = FXCollections.observableArrayList(alPanes);

         list.getItems().clear();
         list.setItems(olPanes);

    }

    public static void checkNotifications() {
        notifications = (ArrayList<Notification>) dao.selectAllFromUser(UserDAO.getUser().getId().intValue());
    }
    
    public static void checkTypes() {
        types = (ArrayList<Type>) typeDao.selectAllFromUser(UserDAO.getUser().getId().intValue());
    }

    public static void fillOutAllEventNotifications(ListView list) {
    
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
        
         list.getItems().clear();
         list.setItems(oblRow);

    }
    

    public static void fillOutCurrentLiteNotifications(ListView list){
        
        ArrayList<LiteRow> alNotifications = new ArrayList<>();
                  
        for (Notification notification : notifications) {

            
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
        
        list.setPadding(new Insets(0,0,5,0));
        list.setFixedCellSize(140);
        list.setItems(obsNotifications);
        System.out.println("prenchida");
        
    }
    
    public static void fillOutAllTypes(ListView list) {
        
        ArrayList<TypeRow> alRow = new ArrayList<>();
        checkTypes();
        
        for(Type type: types){
            
            TypeRow row = new TypeRow(type);
            
            alRow.add(row);
        }
        
        ObservableList<TypeRow> oblRow = FXCollections.observableArrayList(alRow);
        
         list.getItems().clear();
         list.setItems(oblRow);

    }
    
    public static HomeController getController() {
        return controller;
    }

    public static void setController(HomeController controller) {
        Filler.controller = controller;
    }

    public static Notification getNotification() {
        return notification;
    }

    public static void setNotification(Notification notification) {
        Filler.notification = notification;
    }

    public static Row getRow() {
        return row;
    }

    public static void setRow(Row row) {
        Filler.row = row;
    }

    public static ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public static void setNotifications(ArrayList<Notification> notifications) {
        Filler.notifications = notifications;
    }

    public static NotificationDAO getDao() {
        return dao;
    }

    public static void setDao(NotificationDAO dao) {
        Filler.dao = dao;
    }

    public static SimpleDateFormat getDay() {
        return day;
    }

    public static void setDay(SimpleDateFormat day) {
        Filler.day = day;
    }

    public static Notify getNotify() {
        return notify;
    }

    public static void setNotify(Notify notify) {
        Filler.notify = notify;
    }

    public static ListView<?> getList() {
        return list;
    }

    public static void setList(ListView<?> list) {
        Filler.list = list;
    }

    
    
}
