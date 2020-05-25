/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainPostItCreator;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PostItCreatorController implements Initializable {

  
    
    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private JFXTimePicker tmTime;

    @FXML
    private JFXToggleButton tgSound;

    @FXML
    private ComboBox<?> cbTypes;

    @FXML
    private Rectangle recType;

    @FXML
    private JFXTextArea txtBody;

    @FXML
    private TextField txtTitle;

    @FXML
    private Button btSound;

    @FXML
    private Label lblSound;
    
    private boolean soundIsVisible = false;
    
    private File sound;
    
   // private PostItDAO;
    
    @FXML
    public void close() {

       MainPostItCreator window = new MainPostItCreator();
        
    }

    @FXML
    private void create() {

    }

    @FXML
    public void look() {

    }
    
    @FXML
    void changeSoundVisibility() {

        soundIsVisible = !soundIsVisible;
            
            btSound.setVisible(soundIsVisible);
            lblSound.setVisible(soundIsVisible);
            
        if(soundIsVisible == false){
            
            sound = null;
            lblSound.setText("Se nao escolher será selecionado o padrao");
            
        }        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
