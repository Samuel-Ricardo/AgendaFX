/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.TypeDAO;
import DAO.UserDAO;
import Main.MainTypeCreator;
import Model.Type;
import Services.Formater;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSlider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class TypeCreatorController implements Initializable {

 
    @FXML
    private TextField txtName;

    @FXML
    private JFXColorPicker cpPrimaryColor;

    @FXML
    private JFXColorPicker cpSecondaryColor;

    @FXML
    private JFXSlider sldPriority;

    @FXML
    private Label lblPriority;
    
    private int priority;
    
    private TypeDAO dao = new TypeDAO();

    
    
    @FXML
     void define() {
         
         priority = (int) sldPriority.getValue();
        
        lblPriority.setVisible(true);
        
        if(priority <= 1){
            lblPriority.setText("Prioridade Minima");
        }else if(priority <= 3){
            lblPriority.setText("Baixa Prioridade");
        }else if(priority < 7){
            lblPriority.setText("Prioridade Media");
        }else if(priority <10){
            lblPriority.setText("Alta Prioridade");
        }else{
            lblPriority.setText("Prioridade Máxima");
        }
        System.out.println(priority);
    }
    
    @FXML
    public void close() {

        if(MainTypeCreator.getWindow() != null){
        MainTypeCreator.getWindow().close();
        }
        
    }

    @FXML
     void create() {

        Type type = new Type();
        
        String primaryColor = Formater.getHexString(cpPrimaryColor.getValue());
        String secondaryColor = Formater.getHexString(cpPrimaryColor.getValue());
        
        type.setName(txtName.getText());
        type.setPrimaryColor(primaryColor);
        type.setSecondaryColor(secondaryColor);
        type.setUser(UserDAO.getUser());
        type.setImportance((int) sldPriority.getValue());
        
        if(dao.Insert(type) == false){
            JOptionPane.showMessageDialog(null, "Criado");
     }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
