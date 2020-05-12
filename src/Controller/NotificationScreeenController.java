/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    void Update() {

    }

    @FXML
    void closeScreen() {

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
