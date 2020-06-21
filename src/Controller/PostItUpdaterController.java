/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostItDAO;
import DAO.TypeDAO;
import DAO.UserDAO;
import Main.MainPostItCreator;
import Main.MainPostItScreen;
import Model.PostIt;
import Model.Type;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PostItUpdaterController implements Initializable {

   @FXML
    private JFXDatePicker dpDate;

    @FXML
    private JFXTimePicker tmTime;

    @FXML
    private JFXToggleButton tgAttachment;

    @FXML
    private ComboBox<Type> cbTypes;

    @FXML
    private Rectangle recType;

    @FXML
    private JFXTextArea txtBody;

    @FXML
    private TextField txtTitle;

    @FXML
    private Button btSound;

    @FXML
    private Label lblAttachment;
    
    private boolean AttachmentIsVisible = false;
    
    private File Attachment;
    
    private LocalDate date;
    
    private LocalTime time;
    
    private PostItDAO dao = new PostItDAO();
    
    private TypeDAO typeDao = new  TypeDAO();

 @FXML
    public void close() {

       MainPostItCreator.getWindow().close();
        
    }

    @FXML
    public void update() {
       
        PostIt postIt = new PostIt();
                

        postIt.setTitle(txtTitle.getText());
        postIt.setBody(txtBody.getText());
        postIt.setAttachment(Attachment);
        postIt.setType(cbTypes.getSelectionModel().getSelectedItem());
        postIt.setUser(UserDAO.getUser());
        PostItDAO.setPostIt(postIt);
        
        if(dao.Insert(postIt)){
            
            JOptionPane.showMessageDialog(null, "Criado");
            close();
        }
        
        
         
    }

    @FXML
    public void look() {

        try {
            
            if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            MainPostItScreen postItScreen = new MainPostItScreen();
            
            postItScreen.start(new Stage());
            
            PostIt postIt = new PostIt();

                postIt.setTitle(txtTitle.getText());
                postIt.setBody(txtBody.getText());
                postIt.setAttachment(Attachment);
                postIt.setType(cbTypes.getSelectionModel().getSelectedItem());
                postIt.setUser(UserDAO.getUser());

                PostItDAO.setPostIt(postIt);
            
            PostItScreenController.loadPostIt(postIt);
            
        } catch (Exception ex) {
            Logger.getLogger(PostItCreatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void changeSoundVisibility() {

        tgAttachment.fontProperty().get();
        
        AttachmentIsVisible = !AttachmentIsVisible;
            
            btSound.setVisible(AttachmentIsVisible);
            lblAttachment.setVisible(AttachmentIsVisible);
            
        if(AttachmentIsVisible == false){
            
            Attachment = null;
            lblAttachment.setText("Se nao escolher será selecionado o padrao");
            
        }        
    }
    
    
    @FXML
    void soundChoose() {

        FileChooser.ExtensionFilter soundFilter = new FileChooser.ExtensionFilter("Sounds", "*.wav", "*.mp3");
        FileChooser SoundChooser = new FileChooser();
        SoundChooser.getExtensionFilters().add(soundFilter);
        
        Attachment = SoundChooser.showOpenDialog(new Stage());
        
        if (Attachment != null) {

            lblAttachment.setText(Attachment.getName());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillComboBox();
        
        cbTypes.valueProperty().addListener((ov, t, t1) -> {
        
             String color = cbTypes.getSelectionModel().getSelectedItem().getPrimaryColor(); // change the color of Rectangle: recType when change the notification //alterar a cor do retângulo: recType ao alterar o tipo de notificação
                
             recType.setStyle("-fx-fill: "+color+";");
            
        });
        
        startElemnts();
    }    

    private void fillComboBox() {
        
         ArrayList<Type> arTypes = new ArrayList<>();
         
         for(Type type:  typeDao.selectAllFromUser(UserDAO.getUser().getId().intValue())){
             
             arTypes.add(type);
             
         }

        ObservableList<Type> obTypes = FXCollections.observableArrayList(arTypes);    

        cbTypes.setItems(obTypes);  
    
    }

    private void startElemnts() {
      
        PostIt postIt = PostItDAO.getPostIt();

        if(postIt.getAttachment() != null){
            
            AttachmentIsVisible = true;
            
            btSound.setVisible(AttachmentIsVisible);
            lblAttachment.setVisible(AttachmentIsVisible);
        
            tgAttachment.selectedProperty().set(AttachmentIsVisible);
            
            Attachment = postIt.getAttachment();
            lblAttachment.setText(Attachment.getName());
    }
        
       cbTypes.getSelectionModel().select(postIt.getType());
       recType.setStyle("-fx-fill:"+postIt.getType().getPrimaryColor()+";");
       

    }
}
