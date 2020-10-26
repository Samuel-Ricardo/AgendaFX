/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Viewers;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.Viewers.MainNotificationScreen;
import Main.Updaters.MainNotificationUpdater;
import Model.Notification;
import Model.User;
import Services.SoundPlayer;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imgNotification;

    @FXML
    private JFXTextArea txtaDescription;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblScheduled;

    @FXML
    private Label lblAttenchement;

    @FXML
    private Label lblSound;

    @FXML
    private Label lblWarned;

    @FXML
    private Button btStopSound;

    private User user = UserDAO.getUser();

    private Notification notification = NotificationDAO.getNotification();

    @FXML
    void Update() {     // updates this notification with new data //Atualiza esta notificaçao com os novos dados

        if(MainNotificationUpdater.getWindow() != null){
            MainNotificationUpdater.getWindow().close();
        }
        
        MainNotificationUpdater updater = new MainNotificationUpdater();
        
        
        try {
            updater.start(new Stage());
            MainNotificationScreen.getWindow().close();
        } catch (Exception ex) {
            Logger.getLogger(NotificationScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void closeScreen() {    // close this screen // fecha esta tela

        MainNotificationScreen.getWindow().close();
        if(SoundPlayer.isPlaying()){
        SoundPlayer.stopSound();    // stop the sound  // para o som
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadScreen();  // loads the screen  //  carrega a tela

    }

    @FXML
    void stopSound() {

        SoundPlayer.stopSound();    // stop the sound // para o som

    }

    private void loadScreen() { // loads the screen  //  carrega a tela

        lblTitle.setText(notification.getTitle());
        txtaDescription.setText(notification.getBody());

        if (notification.getScheduledDay() != null) {   // set the scheduled time if it is not null // coloca o horario marcado caso  nao esteja nulo
            lblScheduled.setText(notification.getScheduledDay().getOnlyDate()+ " as " + notification.getScheduledDay().getOnlyTime());
        } else {
            lblScheduled.setText("Sem Hora Marcada");
        }

        if (notification.isWarned() == true) {   // Says if you've already been notified // Diz se ja foi avisado

            lblWarned.setText("Foi avisado");
        } else {
            lblWarned.setText("Nao foi avisado");
        }

        if (notification.getAttachment().isEmpty() == false) {  // show the attachment if you have // mostra o anexo caso tenha
            lblAttenchement.setText(notification.getAttachment().get(0).getName());
        } else {
            lblAttenchement.setText("Nenhum arquivo selecionado");
        }

        if (notification.getMusic() != null) {  // show the sound if you have // mostra o som caso tenha 
            lblSound.setText(notification.getMusic().getName());
            btStopSound.setVisible(true);
        } else {
            lblSound.setText("Silencioso");

        }

        if (notification.getImage() != null) { // show the image if you have // mostra a imagem caso tenha  
            imgNotification.setImage(notification.getImage().getImage().getImageFX());
        }

//        String fill = notification.getTypeColor();
//        String color = fill.substring(fill.lastIndexOf("#"));
        String style = "-fx-border-color: " + notification.getType().getPrimaryColor()+";";

        System.out.println(style);
        anchorPane.setStyle(style + " -fx-border-width: 5 5 5 5;");

    }

}
