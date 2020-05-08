/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationCreaterController implements Initializable {

    
    @FXML
    private TextField txtTitle;

    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private Rectangle typeColor;

    @FXML
    private ImageView imgNotific;

    @FXML
    private JFXToggleButton tbSound;

    @FXML
    private Button btSound;

    @FXML
    private Label lblSound;

    @FXML
    private JFXToggleButton tbAttachment;

    @FXML
    private Button btAttachment;

    @FXML
    private JFXToggleButton tbImage;

    @FXML
    private TextField txtHorary;

    @FXML
    void cancel() {

    }

    @FXML
    void chooseFile() {

    }

    @FXML
    void chooseSound() {

    }

    @FXML
    void create() {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
