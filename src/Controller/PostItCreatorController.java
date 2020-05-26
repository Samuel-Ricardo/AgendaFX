/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostItDAO;
import DAO.UserDAO;
import Main.MainPostItCreator;
import Model.PostIt;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private ComboBox<String> cbTypes;

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
    
    private LocalDate date;
    
    private LocalTime time;
    
    private PostItDAO dao = new PostItDAO();
    
    @FXML
    public void close() {

       MainPostItCreator.getWindow().close();
        
    }

    @FXML
    private void create() {
       
        PostIt postIt = new PostIt();
                

        postIt.setTitle(txtTitle.getText());
        postIt.setDescription(txtBody.getText());
        postIt.setMusic(sound);
        postIt.setType(cbTypes.getSelectionModel().getSelectedItem());
        postIt.setTypeColor(recType.getStyle());
        postIt.setUser(UserDAO.getUser());
        postIt.setWarned(false);
        
        date = dpDate.getValue();
        time = tmTime.getValue();
        
        postIt.setScheduledDay(date,time);
        
        if(dao.Insert(postIt)){
            
            JOptionPane.showMessageDialog(null, "Criado");
            
        }
        
        PostItDAO.setPostIt(postIt);
         
    }

    @FXML
    public void look() {

    }
    
    @FXML
    void changeSoundVisibility() {

        tgSound.fontProperty().get();
        
        soundIsVisible = !soundIsVisible;
            
            btSound.setVisible(soundIsVisible);
            lblSound.setVisible(soundIsVisible);
            
        if(soundIsVisible == false){
            
            sound = null;
            lblSound.setText("Se nao escolher será selecionado o padrao");
            
        }        
    }
    
    
    @FXML
    void soundChoose() {

        ExtensionFilter soundFilter = new ExtensionFilter("Sounds", "*.wav", "*.mp3");
        FileChooser SoundChooser = new FileChooser();
        SoundChooser.getExtensionFilters().add(soundFilter);
        
        sound = SoundChooser.showOpenDialog(new Stage());
        
        if (sound != null) {

            lblSound.setText(sound.getName());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillComboBox();
        
        cbTypes.valueProperty().addListener((o) -> {
        
            int index = cbTypes.getSelectionModel().getSelectedIndex(); // change the color of Rectangle: recType when change the notification //alterar a cor do retângulo: recType ao alterar o tipo de notificação
                String style = "";

                switch (index) {

                    case 0:

                        style = "-fx-fill: #ff0000;";
                        recType.setStyle(style);
                        break;

                    case 1:

                        style = "-fx-fill: #8a2be2;";
                        recType.setStyle(style);
                        break;

                    case 2:

                        style = "-fx-fill: #d4ff00;";
                        recType.setStyle(style);
                        break;

                    case 3:

                        style = "-fx-fill: #ffd700;";
                        recType.setStyle(style);
                        break;

                    case 4:

                        style = "-fx-fill: #000080;";
                        recType.setStyle(style);
                        break;
                        
                    case 5:
                        
                        style = "-fx-fill: #0700ff;";
                        recType.setStyle(style);
                        break;
                }
            
        });
        
    }    

    private void fillComboBox() {
     
         ArrayList<String> arTypes = new ArrayList<>();

        arTypes.add("Urgente"); 
        arTypes.add("Trabalho / Escola");
        arTypes.add("Evento");
        arTypes.add("Especial");
        arTypes.add("Banal");
        arTypes.add("Meta");

        ObservableList<String> obTypes = FXCollections.observableArrayList(arTypes);    

        cbTypes.setItems(obTypes);  
    
    }
    
}
