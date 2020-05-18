/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.HomeController;
import DAO.NotificationDAO;
import Model.Notification;
import Model.RowNotification;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Filler {

    private final HomeController controller;
    private Notification notification;
    private RowNotification row;
    private ArrayList<Notification> notifications;
    private final NotificationDAO dao;
    private final SimpleDateFormat day;
    private final Notify notify;

    public Filler(HomeController controller) {

        this.controller = controller;
        this.dao = new NotificationDAO();
        this.day = new SimpleDateFormat("dd/MM/yyy");
        this.notify = controller.getNotify();

    }

    public void fillOutProfileNotification() { // Load notifiacation // carrega as notificaçoes

        int cont = 0;
        ArrayList<Pane> alPanes = new ArrayList<>();
        
        checkNotifications();
        
        for (Notification notification : notifications) {  // Create panels with notification data // Cria paineis com os dados das notificaçoes
            
            RowNotification row = new RowNotification(notifications.get(cont));

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

    public void checkNotifications() {
        notifications = (ArrayList<Notification>) dao.selectAllFromUser(controller.getLogUser().getId().intValue());
    }

}
