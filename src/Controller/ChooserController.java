/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainChooser;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class ChooserController implements Initializable {

    @FXML
    private Pane ChooserPane;
    @FXML
    private ComboBox<String> cbType;
    private ArrayList<String> alTypes = new ArrayList<>();
    private ObservableList<String> obsTypes ;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        alTypes.add("Notficaçao");
        alTypes.add("PostIt");
        alTypes.add("Atividade");

        obsTypes = FXCollections.observableArrayList(alTypes);

        cbType.setItems(obsTypes);

               
    }    

    @FXML
    public void createThis(ActionEvent event) {
        
        HomeController.setIndex(cbType.getSelectionModel().getSelectedIndex());
        MainChooser.getWindow().close();
    }

    public Pane getChooserPane() {
        return ChooserPane;
    }

    public void setChooserPane(Pane ChooserPane) {
        this.ChooserPane = ChooserPane;
    }

    public ComboBox<String> getCbType() {
        return cbType;
    }

    public void setCbType(ComboBox<String> cbType) {
        this.cbType = cbType;
    }

    public ArrayList<String> getAlTypes() {
        return alTypes;
    }

    public void setAlTypes(ArrayList<String> alTypes) {
        this.alTypes = alTypes;
    }

    public ObservableList<String> getObsTypes() {
        return obsTypes;
    }

    public void setObsTypes(ObservableList<String> obsTypes) {
        this.obsTypes = obsTypes;
    }

    
    
}
