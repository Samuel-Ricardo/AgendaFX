/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.HomeController;
import Controller.NotificationListController;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainHome;
import Main.MainNotificationList;

import Main.Viewers.MainNotificationScreen;
import Model.Notification;
import Model.User;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
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
    //private NotificationListController list = new NotificationListController();

    public Notify(HomeController controller) {
        checkDataBase();
        this.controller = controller;
    }

    @Override
    public void run() {

        
        
        try {
            sleep(2500);  // waits the start of aplication // espera a aplicaçao iniciar
        } catch (InterruptedException ex) {
            Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
        }

        checkPermission();

        while (online == true) {

            if (choice == 0) {

                Platform.setImplicitExit(false); // when closing does not close the application // quando fechar nao encerra a aplicaçao

                checkNotifications();

            } else {

                closeApplication();
            }

            waitChangeMinute(new Date(), new Date());
            
            checkPermission();
        }
    }

    public void checkDataBase() {
        this.notifications = (ArrayList<Notification>) dao.selectAllFromUser(user.getId().intValue());
    }

    public void waitChangeMinute(Date nowDate, Date lastDate) {

        String last = minute.format(lastDate);
        String now = minute.format(nowDate);

        do {
            try {

                sleep(100);

            } catch (InterruptedException ex) {
                Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
            }

            now = minute.format(new Date());

        } while (now.equals(last));
    }

    public void closeApplication() throws HeadlessException {
        // completely close the application // fecha completamente a aplicaçao

        JOptionPane.showMessageDialog(null, "A aplicaçao sera fechada completametne e vc nao será mais notificado", "Segundo Plano", JOptionPane.QUESTION_MESSAGE);
        online = false;
        System.exit(0);
    }

    public boolean checkHorary(Notification notification) {

        boolean check;
        Date currentTime = new Date();

        String scheduled = formater.format(notification.getScheduledDay().toDate());
        String today = formater.format(currentTime);

        System.out.println(today);
        System.out.println(scheduled);

        check = scheduled.equals(today);

        return check;
    }

    public void checkNotifications() throws HeadlessException {
        
        checkDataBase();
        
        int cont = 0;

        ArrayList<Notification> notificationWarned = new ArrayList<>();

        for (Notification notification : notifications) { // scans notifications and examines whether to be notified // varre as notificaçoes e analisa se devem ser notificadas

            if (notifications.get(cont).getScheduledDay() != null) {    // check if it's time to notify something // verifica se já chegou a hora de notificar algo

                if (checkHorary(notification)) {

                    try {

                                if (notification.isWarned() == false) {
                                   
                                    notification.setWarned(true);
                                    
                                    notificationWarned.add(notification);
                                    System.out.println(notificationWarned.get(0).getScheduledDay().getOnlyTime()+ " array Acorda");
                                    
                                    dao.update(notification);
                                }
                                
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

        System.out.println(notificationWarned.isEmpty()+" fazio ;-;");
        if (notificationWarned.isEmpty() == false) {
            listNotification(notificationWarned);
        }

    }

    public void playNotificationSound(Notification notification) {

        if (SoundPlayer.isPlaying()) {
            System.out.println("já está tocando");
        } else {

            player = new SoundPlayer(notification.getMusic().getAbsolutePath());
            player.start();
            SoundPlayer.justPlaySound();

            if (MainNotificationScreen.getWindow() != null) {
                MainNotificationScreen.getWindow().setOnCloseRequest((t) -> {
                    SoundPlayer.stopSound();
                });
            }
        }
    }

    public void checkPermission() {

        if (MainHome.getWindow() != null) {
            MainHome.getWindow().setOnCloseRequest((y) -> {  // send a message when close application // envia menssagem ao fechar a aplicaçao

                this.choice = JOptionPane.showConfirmDialog(null, "A aplicaçao Precisa rodar em 2° plano para poder notifica-lo \n Voce permite a aplicaçao rodar em segundo plano?");

                if (choice == 1) {
                    System.exit(0);
                }
            });

        }
    }

    public static void showNotification(Notification notification) throws Exception { // opens the notification screen // abre a tela de notificaçao 

        NotificationDAO.setNotification(notification);

        if (MainNotificationScreen.getWindow() != null) {
            MainNotificationScreen.getWindow().close();
        }
        MainNotificationScreen notificationScreen = new MainNotificationScreen();

        notificationScreen.start(new Stage());
    }

    private void listNotification(ArrayList<Notification> notifications) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("lista          t");
                
                if (MainNotificationList.getWindow() != null) {
                    MainNotificationList.getWindow().close();
                }
                    
                NotificationListController.setNotifications(notifications);
                
                MainNotificationList notificationlist = new MainNotificationList();
            
                
                try {
                    notificationlist.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("lista");
                playNotificationSound(notifications.get(0));
            }
        });

        

    }

}
