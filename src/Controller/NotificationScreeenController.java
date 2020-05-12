/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Main.MainNotificationScreen;
import Model.Notification;
import Model.User;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationScreeenController implements Initializable {

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

    // private UserDAO dao = new UserDAO();
    private User user = UserDAO.getUser();

    private Notification notification = NotificationDAO.getNotification();

    @FXML
    void Update() {

    }

    @FXML
    void closeScreen() {

        MainNotificationScreen.getWindow().close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadScreen();

    }

    private void loadScreen() {

        lblTitle.setText(notification.getTitle());
        txtaDescription.setText(notification.getDescription());
        lblScheduled.setText(notification.getScheduledDate() + " as " + notification.getScheduledHour());

        if (notification.isWarned() == true) {
            
            lblWarned.setText("Foi avisado");
        }else{
            lblWarned.setText("Nao foi avisado");
        }
        
        if(notification.getAttachment() != null){
            lblAttenchement.setText(notification.getAttachment().getName());
        }else{
            lblAttenchement.setText("Nenhum arquivo selecionado");
        }
        
        if(notification.getMusic() != null){
            lblSound.setText(notification.getMusic().getName());
        }else{
            lblSound.setText("Nenhum Som selecionado");
        }
        
        if(notification.getImage()!= null || notification.getImage()!= ""){
            imgNotification.setImage(new Image("file:///"+notification.getImage()));
        }
    }

}
