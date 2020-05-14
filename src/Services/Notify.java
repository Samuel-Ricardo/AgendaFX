/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.HomeController;
import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainHome;
import Main.MainNotificationScreen;
import Model.Notification;
import Model.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Notify extends Thread {

    private boolean online = true;
    private final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final SimpleDateFormat minute = new SimpleDateFormat("mm");
    private final NotificationDAO dao = new NotificationDAO();
    private final User user = UserDAO.getUser();
    private ArrayList<Notification> notifications;
    private HomeController controller;
    private int choice = 0;
    private SoundPlayer player;

    public Notify(HomeController controller) {
        this.notifications = (ArrayList<Notification>) dao.selectAll(user.getId().intValue());
        this.controller = controller;
    }

    @Override
    public void run() {

        int cont = 0;

        try {
            sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (MainHome.getWindow() != null) {
            MainHome.getWindow().setOnCloseRequest((y) -> {

                this.choice = JOptionPane.showConfirmDialog(null, "A aplicaçao Precisa rodar em 2° plano para poder notifica-lo \n Voce permite a aplicaçao rodar em segundo plano?");

                if (choice == 1) {
                    System.exit(0);
                }
            });
        }

        while (online == true) {

            if (choice == 0) {

                Platform.setImplicitExit(false);

                Date currentTime = new Date();

                cont = 0;
                for (Notification notification : notifications) {

                    if (notifications.get(cont).getScheduledDay() != null) {

                        String scheduled = formater.format(notifications.get(cont).getScheduledDay());
                        String today = formater.format(currentTime);

                        System.out.println(today);
                        System.out.println(scheduled);
                        if (scheduled.equals(today)) {

                            try {

                                Platform.runLater(() -> {
                                    
                                    if (MainNotificationScreen.getWindow() == null) {
                                        
                                        if (notification.isWarned() == false) {
                                            
                                            notification.setWarned(true); 
                                            
                                            try {
                                                controller.showNotification(notification);
                                            } catch (Exception ex) {
                                                Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                            player = new SoundPlayer(notification.getMusic().getAbsolutePath());
                                            player.start();
                                            SoundPlayer.justPlaySound();

                                            MainNotificationScreen.getWindow().setOnCloseRequest((t) -> {
                                                SoundPlayer.stopSound();
                                            });

                                        }
                                    }
                                });

                                Platform.runLater(() -> {

                                });

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Nao foi Possivel Abrir a notificaçao: " + ex);
                                int result = JOptionPane.showConfirmDialog(null, "Notificaçao: " + notifications.get(cont).getTitle()
                                        + "\n  deseja marcar como avisado?");

                                switch (result) {

                                    case 0:

                                        notifications.get(cont).setWarned(true);

                                        break;

                                    case 1:

                                        notifications.get(cont).setWarned(false);

                                        break;
                                }
                            }

                        }
                    }

                    cont++;
                }

            } else {

                JOptionPane.showMessageDialog(null, "A aplicaçao sera fechada completametne e vc nao será mais notificado", "Segundo Plano", JOptionPane.QUESTION_MESSAGE);
                online = false;
                System.exit(0);
            }
            
            String now = minute.format(new Date());
            String last = minute.format(new Date());
            
            
            do {
                try {

                    sleep(900);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
                }

                now = minute.format(new Date());
            } while (now.equals(last));

            this.notifications = (ArrayList<Notification>) dao.selectAll(user.getId().intValue());

            MainHome.getWindow().setOnCloseRequest((t) -> {

                this.choice = JOptionPane.showConfirmDialog(null, "A aplicaçao Precisa rodar em 2° plano para poder notifica-lo \n Voce permite a aplicaçao rodar em segundo plano?");

                if (choice == 1) {
                    System.exit(0);
                }

            });

        }
    }
}
