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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Notify extends Thread {

    private boolean online = true;
    private final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final NotificationDAO dao = new NotificationDAO();
    private final User user = UserDAO.getUser();
    private ArrayList<Notification> notifications = (ArrayList<Notification>) dao.selectAll(user.getId().intValue());
    private HomeController controller;
    private int choice = 0;
    private SoundPlayer player;

    public Notify(HomeController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        int cont = 0;
        
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            MainHome.getWindow().setOnCloseRequest((t) -> {

                this.choice = JOptionPane.showConfirmDialog(null, "A aplicaçao Precisa rodar em 2° plano para poder notifica-lo \n Voce permite a aplicaçao rodar em segundo plano?");

                if(choice != 0){
                    System.exit(0);
                }
            });


        while (online == true) {

            if (choice == 0) {
                
                Platform.setImplicitExit(false);
                
                Date currentTime = new Date();

                System.out.println(currentTime);

                for (Notification notification : notifications) {

                    if (notifications.get(cont).getScheduledDay() != null) {
                        
                        String scheduled = formater.format(notifications.get(cont).getScheduledDay());
                        String today = formater.format(currentTime);
                        
                        if (scheduled.equals(today)) {

                            try {

                                controller.showNotification(notifications.get(cont));
                                
                                player = new SoundPlayer(notifications.get(cont).getMusic().getAbsolutePath());
                                player.start();
                                
                                player.justPlaySound();
                                
                                MainNotificationScreen.getWindow().setOnCloseRequest((t) -> {
                                    player.stopSound();
                                });
                                
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Nao foi Possivel Abrir a notificaçao " + ex);
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
                    try {
                        sleep(45000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Notify.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                
                JOptionPane.showMessageDialog(null, "A aplicaçao sera fechada completametne e vc nao será mais notificado","Segundo Plano",JOptionPane.QUESTION_MESSAGE);
                online = false;
                System.exit(0);
            }

        }

    }
}
