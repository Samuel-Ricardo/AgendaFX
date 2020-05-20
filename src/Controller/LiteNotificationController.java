/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import Model.Notification;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class LiteNotificationController implements Initializable {

    @FXML
    private Pane pane;
      
    @FXML
    private ImageView image;

    @FXML
    private Label lblTitle;

    @FXML
    private JFXTextArea txtDescription;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void load(Notification notification){
        
        txtDescription.setText(notification.getDescription());
        lblTitle.setText(notification.getTitle());
        
        if(notification.getImage() != null || notification.getImage().equals("") == false){ 
            image.setImage(new Image("file:///"+notification.getImage()));
        }
        
        pane.setStyle("-fx-background-color: black;"
                    + "-fx-border-width: 2 2 2 2;"
                    + "-fx-border-color: "+notification.getTypeColor()+";");
        
        System.out.println("load");
    }
    
    
}
